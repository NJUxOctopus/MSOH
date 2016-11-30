package businesslogic.order_bl;

import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogic.hotel_bl.MockHotel;
import businesslogicservice.order_blservice.Order_BLService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import dataservice.order_dataservice.Order_DataService;
import dataservice.order_dataservice.Order_DataService_Stub;
import po.HotelPO;
import po.OrderPO;
import rmi.RemoteHelper;
import util.OrderStatus;
import util.ResultMessage;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;

import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Result;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Order implements Order_BLService {
    private Hotel hotel;
    private OrderPO orderPO;
    private MockHotel mockHotel;
    Order_DataService_Stub order_dataService_stub = new Order_DataService_Stub();

    public double getTotal(OrderVO orderVO) throws RemoteException {

        return 0;
    }

    /**
     * 新建订单
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage createOrder(OrderVO orderVO) throws RemoteException{
        if (orderVO.rooms==null||orderVO.estimatedCheckoutTime == null || orderVO.estimatedCheckinTime == null)
            //若未填写房间，预计离开时间和预计到达时间
            return ResultMessage.Blank;
        else {
            orderPO = new OrderPO(orderVO.customerName, orderVO.phone, orderVO.customerID, orderVO.hotelID, orderVO.hotelName, orderVO.orderID,
                    orderVO.estimatedCheckinTime, orderVO.actualCheckinTime, orderVO.estimatedCheckoutTime, orderVO.actualCheckoutTime,
                    orderVO.latestExecutedTime, orderVO.rooms, orderVO.numOfCustomers, orderVO.haveChildren, orderVO.remarks,
                    orderVO.promotionName, orderVO.initialPrice, orderVO.finalPrice, orderVO.orderType);
            order_dataService_stub.addOrder(orderPO);
            return ResultMessage.Order_CreateOrderSuccess;
        }
    }

    /**
     * 取消订单
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage cancelOrder(OrderVO orderVO)throws RemoteException {
        //根据orderVO的订单号得到该订单的po,然后在获取订单状态
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        //如果订单状态为未执行，更改为已撤销，并返回撤销订单成功
        if (orderPO.getOrderType().equals(OrderStatus.UNEXECUTED)) {
            orderPO.setOrderType(OrderStatus.REVOKED);
            order_dataService_stub.updateOrder(orderPO);
            orderVO.orderType = OrderStatus.EXECUTED;
            return ResultMessage.Order_CancelOrderSuccess;
        } else
            //这种else应该返回什么呢
            return null;

    }

    /**
     * 执行订单
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage executeOrder(OrderVO orderVO)throws RemoteException {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (orderVO.orderType.equals(OrderStatus.UNEXECUTED)) {
            //若订单状态为未执行
            if(orderVO.actualCheckinTime==null||orderVO.estimatedCheckoutTime==null)
                //若实际到达时间为空
                return ResultMessage.Blank;
            orderPO.setOrderType(OrderStatus.EXECUTED);
            orderPO.setEstimatedCheckoutTime(orderVO.estimatedCheckoutTime);
            orderPO.setActualCheckinTime(orderVO.actualCheckinTime);
           order_dataService_stub.updateOrder(orderPO);

            orderVO.orderType = OrderStatus.EXECUTED;
            return ResultMessage.Order_ExecuteOrderSuccess;
        } else {
            //同上
            return null;
        }
    }

    /**
     * 结束订单
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage endOrder(OrderVO orderVO)throws RemoteException {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (orderVO.orderType.equals(OrderStatus.EXECUTED)) {
            //若订单状态为已执行
            if(orderVO.actualCheckoutTime==null)
                //若实际离开时间为空
                return ResultMessage.Blank;
            orderPO.setOrderType(OrderStatus.FINISHED_UNEVALUATED);
            orderVO.orderType = OrderStatus.FINISHED_UNEVALUATED;
            orderPO.setActualCheckoutTime(orderVO.actualCheckoutTime);
            order_dataService_stub.updateOrder(orderPO);
            return ResultMessage.Order_EndOrderSuccess;
        } else {
            //同上
            return null;
        }
    }

    /**
     * 设为异常订单（暂时还未考虑透彻）
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage setAbnormal(OrderVO orderVO) throws RemoteException{
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        orderPO.setOrderType(OrderStatus.ABNORMAL);
        orderVO.orderType = OrderStatus.ABNORMAL;
        order_dataService_stub.updateOrder(orderPO);
        return ResultMessage.Order_SetAbnormalSuccess;
    }

    /**
     * 恢复异常订单
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage renewOrder(OrderVO orderVO) throws RemoteException{
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (orderVO.orderType.equals(OrderStatus.ABNORMAL)) {
            //若订单状态为异常
            orderPO.setOrderType(OrderStatus.REVOKED);
            orderVO.orderType = OrderStatus.REVOKED;
            order_dataService_stub.updateOrder(orderPO);
            return ResultMessage.Order_RenewOrderSuccess;
        } else {
            //同上
            return null;
        }
    }

}
