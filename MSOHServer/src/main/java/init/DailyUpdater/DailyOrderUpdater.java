package init.DailyUpdater;

import DataHelper.customerDataHelper.CreditRecordDataHelper;
import DataHelper.customerDataHelper.CustomerDataHelper;
import DataHelper.hotelDataHelper.RoomDataHelper;
import DataHelper.orderDataHelper.OrderDataHelper;
import DataHelperImpl.customerDataHelperImpl.CreditRecordDataHelperSQLImpl;
import DataHelperImpl.customerDataHelperImpl.CustomerDataHelperSQLImpl;
import DataHelperImpl.hotelDataHelperImpl.RoomDataHelperSQLImpl;
import DataHelperImpl.orderDataHelperImpl.OrderDataHelperSQLImpl;
import po.CreditRecordPO;
import po.CustomerPO;
import po.OrderPO;
import po.RoomPO;
import util.CreditChangeReason;
import util.OrderStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/12.
 */
public class DailyOrderUpdater {

    private OrderDataHelper orderDataHelper = new OrderDataHelperSQLImpl();

    private CustomerDataHelper customerDataHelper = new CustomerDataHelperSQLImpl();

    private CreditRecordDataHelper creditRecordDataHelper = new CreditRecordDataHelperSQLImpl();

    private RoomDataHelper roomDataHelper = new RoomDataHelperSQLImpl();

    public DailyOrderUpdater() {
        boolean status = setAbnomral_auto(new Timestamp(System.currentTimeMillis()));

        if (status) {
            System.out.println("Octopus: 订单已设为异常");
        } else {
            System.out.println("Octopus: 无订单需更新状态");
        }
    }

    /**
     * 如果时间在超过最晚订单执行时间后还没有办理入住
     * 系统自动将其置为异常订单
     *
     * @param now 当前时间
     * @return 若有订单需要更新，返回true；若无，返回false
     */
    public boolean setAbnomral_auto(Timestamp now) {
        boolean status = false;
        // 获得所有订单
        List<OrderPO> orderList = orderDataHelper.getAllOrders();

        if (orderList == null || orderList.isEmpty()) {
            return status;
        }

        // 将订单状态为 未入住的 、最晚订单执行时间在 当前时间之前的 ，订单状态设为 异常
        for (OrderPO order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.UNEXECUTED)) {
                if (order.getLatestExecutedTime().before(now)) {
                    // 设为异常
                    order.setOrderStatus(OrderStatus.ABNORMAL);
                    // 扣除对应客户的信用值
                    // 扣除额度等于订单价值（取整）
                    decreaseCustomerCredit_auto(order.getCustomerID(), order.getFinalPrice(), order.getOrderID());
                    updateRoomInfo_auto(order.getHotelID(), order.getEstimatedCheckInTime(), order.getEstimatedCheckOutTime(), order.getRooms());
                    // 更新数据库
                    orderDataHelper.updateOrder(order);
                    status = true;
                }
            }
        }

        return status;
    }

    /**
     * 置为异常的同时扣除用户等于订单的总价值的信用值，并增加信用记录
     *
     * @param customerID
     * @param price
     */

    private void decreaseCustomerCredit_auto(String customerID, double price, String orderID) {
        CustomerPO customer = customerDataHelper.findCustomerByID(customerID);

        // 改变后的信用值
        int adjustedCredit = customer.getCredit() - (int) price;

        // 新增信用记录
        CreditRecordPO creditRecordPO = new CreditRecordPO((int) price, new Timestamp(System.currentTimeMillis()), customer.getUserName(), customerID, (adjustedCredit <= 0) ? 0 : adjustedCredit, orderID, "", CreditChangeReason.Order_Abnormal);

        customer.setCredit((adjustedCredit <= 0) ? 0 : adjustedCredit);

        customerDataHelper.modifyCustomer(customer);
        creditRecordDataHelper.addCreditRecord(creditRecordPO);
    }

    public void updateRoomInfo_auto(String hotelID, Timestamp estimatedCheckInTime, Timestamp estimatedCheckOutTime, String rooms) {
        String[] orderRoomArray = rooms.split(";");

        List<RoomPO> hotelRoomList = roomDataHelper.getRoomsByHotel(hotelID);
        for (RoomPO room : hotelRoomList) {
            // 得到在预定范围的房间
            if (room.getDate().equals(estimatedCheckInTime) || (room.getDate().after(estimatedCheckInTime) && room.getDate().before(estimatedCheckOutTime))) {
                // 判断该房间是否是被客户预定的
                for (int i = 0; i < orderRoomArray.length; i++) {
                    if (room.getRoomType().equals(orderRoomArray[i])) {
                        room.setReservedRooms(room.getReservedRooms() - 1);
                        room.setLeftRooms(room.getLeftRooms() + 1);
                        // 更改数据库中的房间信息
                        roomDataHelper.modifyRoom(room);
                    }
                }
            }
        }
    }
}
