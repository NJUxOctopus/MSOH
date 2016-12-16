package init.DailyUpdater;

import DataHelper.customerDataHelper.CustomerDataHelper;
import DataHelper.orderDataHelper.OrderDataHelper;
import DataHelperImpl.customerDataHelperImpl.CustomerDataHelperSQLImpl;
import DataHelperImpl.orderDataHelperImpl.OrderDataHelperSQLImpl;
import po.CustomerPO;
import po.OrderPO;
import util.OrderStatus;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zqh on 2016/12/12.
 */
public class DailyOrderUpdater {

    private OrderDataHelper orderDataHelper = new OrderDataHelperSQLImpl();

    private CustomerDataHelper customerDataHelper = new CustomerDataHelperSQLImpl();

    public DailyOrderUpdater(){
        boolean status=setAbnomral_auto(new Timestamp(System.currentTimeMillis()));

        if(status){
            System.out.println("Octopus: 订单已设为异常");
        }else{
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
        boolean status=false;
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
                    decreaseCustomerCredit_auto(order.getCustomerID(), order.getFinalPrice());
                    // 更新数据库
                    orderDataHelper.updateOrder(order);
                    status=true;
                }
            }
        }

        return status;
    }

    /**
     * 置为异常的同时扣除用户等于订单的总价值的信用值
     *
     * @param customerID
     * @param price
     */

    //TODO 添加信用记录
    private void decreaseCustomerCredit_auto(String customerID, double price) {
        CustomerPO customer = customerDataHelper.findCustomerByID(customerID);

        int adjustedCredit = customer.getCredit() - (int) price;
        customer.setCredit((adjustedCredit <= 0) ? 0 : adjustedCredit);

        customerDataHelper.modifyCustomer(customer);
    }
}
