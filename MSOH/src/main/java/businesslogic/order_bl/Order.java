package businesslogic.order_bl;

import businesslogic.hotel_bl.HotelUtil;
import businesslogic.promotion_bl.Promotion;
import businesslogicservice.order_blservice.Order_BLService;
import dataservice.order_dataservice.Order_DataService;
import po.OrderPO;
import po.PromotionPO;
import rmi.RemoteHelper;
import util.DataFormat;
import util.OrderStatus;
import util.ResultMessage;
import vo.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by apple on 16/11/10.
 */
public class Order implements Order_BLService {
    private OrderPO orderPO;
    private Order_DataService order_dataService_stub = RemoteHelper.getInstance().getOrderDataService();

    /**
     * 在订单的所有促销策略中返回价格最低的策略
     *
     * @param orderPriceVOs
     * @return
     * @throws RemoteException
     */
    public OrderPriceVO getLowestPrice(List<OrderPriceVO> orderPriceVOs) throws RemoteException {
        return null;
    }

    /**
     * 该方法用于判断订单是否符合当日的营销策略，如果可以的话生成价格，如果不能用的话仅返回初始价格
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public List<OrderPriceVO> usePromotion(OrderVO orderVO) throws IOException, ClassNotFoundException {
        Promotion promotion = new Promotion();
        List<OrderPriceVO> orderPriceVOList = new ArrayList<OrderPriceVO>();
        List<PromotionVO> promotionVOList = promotion.promotionRequirements(orderVO);//先调促销策略的方法判断订单信息是否符合该酒店的所有策略

        double initPrice = getTotal(orderVO);//初始价格
        if (promotionVOList == null || promotionVOList.isEmpty()) {
            orderPriceVOList.add(new OrderPriceVO(null, initPrice, initPrice));//若无促销策略可使用
            return orderPriceVOList;
        }

        for (PromotionVO promotionVO:promotionVOList){
            double finalPrice = initPrice * promotionVO.discount;//折后价格
            orderPriceVOList.add(new OrderPriceVO(promotionVO.promotionName, initPrice, finalPrice));
        }
        return orderPriceVOList;
    }

    public double getTotal(OrderVO orderVO) throws RemoteException {
        HotelUtil hotelUtil = new HotelUtil();
        long oneDay = 1000 * 60 * 60 * 24;
        double initPrice = 0;//初始价格
        for (int j = 0; j < orderVO.rooms.length; j++) {
            initPrice += hotelUtil.getRoomByName(orderVO.hotelID, orderVO.rooms[j], orderVO.estimatedCheckinTime).price;
            //得到所有房间的类型与价格
        }
        return initPrice*(orderVO.estimatedCheckoutTime.getTime()-orderVO.estimatedCheckinTime.getTime())/oneDay;
    }

    /**
     * 新建订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage createOrder(OrderVO orderVO) throws RemoteException {
        if (orderVO.rooms == null || orderVO.estimatedCheckoutTime == null || orderVO.estimatedCheckinTime == null)
            //若未填写房间，预计离开时间和预计到达时间
            return ResultMessage.Blank;
        else {
            String rooms = "";
            for (int i = 0; i < orderVO.rooms.length; i++) {
                if (i != orderVO.rooms.length - 1)
                    rooms += orderVO.rooms[i] + ";";
                else
                    rooms += orderVO.rooms[i];
            }
            orderPO = new OrderPO(orderVO.customerName, orderVO.phone, orderVO.customerID, orderVO.hotelID, orderVO.hotelName,
                    orderVO.estimatedCheckinTime, orderVO.actualCheckinTime, orderVO.estimatedCheckoutTime, orderVO.actualCheckoutTime, null,
                    rooms, orderVO.numOfCustomers, orderVO.haveChildren, orderVO.remarks, orderVO.promotionName, orderVO.initialPrice, orderVO.finalPrice, OrderStatus.UNEXECUTED);
            if (order_dataService_stub.addOrder(orderPO))
                return ResultMessage.Order_CreateOrderSuccess;
            else
                return ResultMessage.Fail;
        }
    }

    /**
     * 线下创建订单
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage createOrderOffline(OrderVO orderVO) throws RemoteException {
        if(orderVO.customerName.equals("")||orderVO.phone.equals("")||orderVO.customerID.equals(""))
            return ResultMessage.Blank;
        if(!orderVO.customerID.matches(DataFormat.ID_Format))
            return ResultMessage.DataFormatWrong;
        if(!orderVO.phone.matches(DataFormat.Phone_Format))
            return ResultMessage.phoneFormatWrong;
        String rooms = "";
        for (int i = 0; i < orderVO.rooms.length; i++) {
            if (i != orderVO.rooms.length - 1)
                rooms += orderVO.rooms[i] + ";";
            else
                rooms += orderVO.rooms[i];
        }
        orderPO = new OrderPO(orderVO.customerName, orderVO.phone, orderVO.customerID, orderVO.hotelID, orderVO.hotelName,
                orderVO.estimatedCheckinTime, orderVO.actualCheckinTime, orderVO.estimatedCheckoutTime, orderVO.actualCheckinTime, orderVO.actualCheckinTime,
                rooms, orderVO.numOfCustomers, orderVO.haveChildren, "", "", orderVO.initialPrice, orderVO.finalPrice, orderVO.orderType);
        if (order_dataService_stub.addOrder(orderPO))
            return ResultMessage.Order_CreateOrderSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 取消订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage cancelOrder(OrderVO orderVO) throws RemoteException {
        //根据orderVO的订单号得到该订单的po,然后在获取订单状态
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        //如果订单状态为未执行，更改为已撤销，并返回撤销订单成功
        if (orderPO.getOrderStatus().equals(OrderStatus.UNEXECUTED)) {
            orderPO.setOrderStatus(OrderStatus.REVOKED);
            if (order_dataService_stub.updateOrder(orderPO)) {
                return ResultMessage.Order_CancelOrderSuccess;
            } else
                return ResultMessage.Fail;
        } else
            return ResultMessage.Fail;

    }

    /**
     * 执行订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage executeOrder(OrderVO orderVO) throws RemoteException {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (orderVO.orderType.equals(OrderStatus.UNEXECUTED)) {
            //若订单状态为未执行
            if (orderVO.actualCheckinTime == null || orderVO.estimatedCheckoutTime == null)
                //若实际到达时间为空
                return ResultMessage.Blank;
            orderPO.setOrderStatus(OrderStatus.EXECUTED);
            orderPO.setEstimatedCheckOutTime(orderVO.estimatedCheckoutTime);
            orderPO.setActualCheckInTime(orderVO.actualCheckinTime);
            if (order_dataService_stub.updateOrder(orderPO)) {
                return ResultMessage.Order_ExecuteOrderSuccess;
            } else
                return ResultMessage.Fail;
        } else {
            return ResultMessage.Fail;
        }
    }

    /**
     * 结束订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage endOrder(OrderVO orderVO) throws RemoteException {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (orderVO.orderType.equals(OrderStatus.EXECUTED)) {
            //若订单状态为已执行
            if (orderVO.actualCheckoutTime == null)
                //若实际离开时间为空
                return ResultMessage.Blank;
            orderPO.setOrderStatus(OrderStatus.FINISHED_UNEVALUATED);
            orderPO.setActualCheckOutTime(orderVO.actualCheckoutTime);
            if (order_dataService_stub.updateOrder(orderPO)) {
                orderVO.orderType = OrderStatus.FINISHED_UNEVALUATED;
                return ResultMessage.Order_EndOrderSuccess;
            } else
                return ResultMessage.Fail;
        } else {
            return ResultMessage.Fail;
        }
    }

//    /**
//     * 设为异常订单
//     *
//     * @param orderID
//     * @return
//     * @throws RemoteException
//     */
//    public ResultMessage setAbnormal(String orderID) throws RemoteException {
//        orderPO = order_dataService_stub.getOrderByOrderID(orderID);
//        if (orderPO.getOrderStatus().equals(OrderStatus.UNEXECUTED)) {
//            orderPO.setOrderStatus(OrderStatus.ABNORMAL);
//            if (order_dataService_stub.updateOrder(orderPO))
//                return ResultMessage.Order_SetAbnormalSuccess;
//            else
//                return ResultMessage.Fail;
//        } else
//            return ResultMessage.Fail;
//    }

//    /**
//     * 在订单生成后，判断订单预计到达那天23点前是否入住
//     *
//     * @param timestamp
//     * @throws RemoteException
//     */
//    public void examineAbnormal(String orderID, Timestamp timestamp) throws RemoteException {
//        Calendar calendar = Calendar.getInstance();
//
//        /**
//         * 指定触发的时间现在指定时间为预计到达那天的23点前
//         */
//        calendar.set(Calendar.DAY_OF_MONTH, timestamp.getDay());
//        calendar.set(Calendar.MONTH, timestamp.getMonth());
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        Date time = calendar.getTime();
//        Timer timer = new Timer();
//        timer.schedule(new MyTask(orderID), time);
//    }

    /**
     * 恢复异常订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage renewOrder(OrderVO orderVO) throws RemoteException {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (orderVO.orderType.equals(OrderStatus.ABNORMAL)) {
            //若订单状态为异常
            orderPO.setOrderStatus(OrderStatus.REVOKED);
            if (order_dataService_stub.updateOrder(orderPO)) {
                return ResultMessage.Order_RenewOrderSuccess;
            } else
                return ResultMessage.Fail;
        } else {
            return ResultMessage.Fail;
        }
    }

}

//class MyTask extends TimerTask {
//    String orderID;
//
//    public MyTask(String orderID) {
//        this.orderID = orderID;
//    }
//
//    @Override
//    public void run() {
//        Order order = new Order();
//        try {
//            order.setAbnormal(orderID);
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        }
//    }
//}

