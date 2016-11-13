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
import util.OrderStatus;
import util.ResultMessage;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;

import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Order implements Order_BLService {
    private Order_DataService_Stub order_dataService_stub = new Order_DataService_Stub();
    private Hotel hotel;
    private OrderPO orderPO;
    private MockHotel mockHotel;

    public double getTotal(OrderVO orderVO) {

        return mockHotel.getRoomPrice(orderVO.finalPrice,orderVO.rooms.size());
    }

    public ResultMessage createOrder(OrderVO orderVO) {
        //有哪些信息是本来就有不用填的呢
        orderPO = new OrderPO(orderVO.customerName, orderVO.phone, orderVO.customerID, orderVO.hotelID, orderVO.hotelName, orderVO.orderID,
                orderVO.estimatedCheckinTime, orderVO.actualCheckinTime, orderVO.estimatedCheckoutTime, orderVO.actualCheckoutTime,
                orderVO.latestExecutedTime, orderVO.rooms, orderVO.numOfCustomers, orderVO.haveChildren, orderVO.remarks,
                orderVO.promotionName, orderVO.initialPrice, orderVO.finalPrice, orderVO.orderType);
        order_dataService_stub.add(orderPO);
        return null;
    }

    public ResultMessage cancelOrder(OrderVO orderVO) {
        //根据orderVO的订单号得到该订单的po,然后在获取订单状态
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        //如果订单状态为未执行，更改为已撤销，并返回撤销订单成功
        if (order_dataService_stub.getOrderStatus(orderPO).equals(OrderStatus.UNEXECUTED)) {
            order_dataService_stub.changeOrderStatus(orderPO, OrderStatus.EXECUTED);
            orderVO.orderType = OrderStatus.EXECUTED;
            return ResultMessage.Order_CancelOrderSuccess;
        } else
            //这种else应该返回什么呢
            return null;

    }

    public ResultMessage executeOrder(OrderVO orderVO) {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (order_dataService_stub.getOrderStatus(orderPO).equals(OrderStatus.UNEXECUTED)) {
            order_dataService_stub.changeOrderStatus(orderPO, OrderStatus.EXECUTED);
            orderVO.orderType = OrderStatus.EXECUTED;
            order_dataService_stub.setActualCheckinTime(orderPO, orderVO.actualCheckinTime);
            order_dataService_stub.setEstimatedCheckoutTime(orderPO, orderVO.estimatedCheckoutTime);
            return ResultMessage.Order_ExecuteOrderSuccess;
        } else {
            //同上
            return null;
        }
    }

    public ResultMessage endOrder(OrderVO orderVO) {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (order_dataService_stub.getOrderStatus(orderPO).equals(OrderStatus.EXECUTED)) {
            order_dataService_stub.changeOrderStatus(orderPO, OrderStatus.FINISHED_UNEVALUATED);
            orderVO.orderType = OrderStatus.FINISHED_UNEVALUATED;
            order_dataService_stub.setActualCheckoutTime(orderPO, orderVO.actualCheckinTime);
            return ResultMessage.Order_EndOrderSuccess;
        } else {
            //同上
            return null;
        }
    }

    public ResultMessage setAbnormal(OrderVO orderVO) {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        order_dataService_stub.changeOrderStatus(orderPO, OrderStatus.ABNORMAL);
        orderVO.orderType = OrderStatus.ABNORMAL;
        return ResultMessage.Order_SetAbormalSuccess;
    }

    public ResultMessage renewOrder(OrderVO orderVO) {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        if (order_dataService_stub.getOrderStatus(orderPO).equals(OrderStatus.ABNORMAL)) {
            order_dataService_stub.changeOrderStatus(orderPO, OrderStatus.REVOKED);
            orderVO.orderType = OrderStatus.REVOKED;
            return ResultMessage.Order_RenewOrderSuccess;
        } else {
            //同上
            return null;
        }
    }

}
