package businesslogic.order_bl;

import businesslogic.customer_bl.Customer;
import businesslogic.customer_bl.CustomerUtil;
import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogic.hotel_bl.MockHotel;
import businesslogic.promotion_bl.Promotion;
import businesslogic.promotion_bl.PromotionUtil;
import businesslogicservice.order_blservice.Order_BLService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import dataservice.order_dataservice.Order_DataService;
import dataservice.order_dataservice.Order_DataService_Stub;
import po.HotelPO;
import po.OrderPO;
import rmi.RemoteHelper;
import util.OrderStatus;
import util.ResultMessage;
import vo.*;

import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Result;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Order implements Order_BLService {
    private OrderPO orderPO;
    Order_DataService_Stub order_dataService_stub = new Order_DataService_Stub();

    /**
     * 该方法用于判断订单是否符合当日的营销策略，如果可以的话生成价格
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public List<OrderPriceVO> usePromotion(OrderVO orderVO) throws RemoteException, IOException, ClassNotFoundException {
        Promotion promotion = new Promotion();
        CustomerUtil customerUtil = new CustomerUtil();
        HotelUtil hotelUtil = new HotelUtil();
        List<OrderPriceVO> orderPriceVOList = new ArrayList<OrderPriceVO>();
        List<PromotionVO> promotionVOList = promotion.promotionRequirements(orderVO.hotelID, customerUtil.getSingle(orderVO.customerID).memberType
                , hotelUtil.getByID(orderVO.hotelID).area, orderVO.rooms.length);
        if (promotionVOList == null || promotionVOList.isEmpty())
            return null;
        for (int i = 0; i < promotionVOList.size(); i++) {
            PromotionVO promotionVO = promotionVOList.get(i);
            double initPrice = 0;
            for (int j = 0; j < orderVO.rooms.length; j++) {
                initPrice += hotelUtil.getRoomByName(orderVO.hotelID, orderVO.rooms[i]).price;
            }
            double finalPrice = initPrice * promotionVO.discount;
            orderPriceVOList.add(new OrderPriceVO(promotionVO.promotionName, initPrice, finalPrice));
        }
        return orderPriceVOList;
    }

    public double getTotal(OrderVO orderVO) throws RemoteException {

        return 0;
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
            orderPO = new OrderPO(orderVO.customerName, orderVO.phone, orderVO.customerID, null, orderVO.hotelName,
                    orderVO.estimatedCheckinTime, orderVO.actualCheckinTime, orderVO.estimatedCheckoutTime, orderVO.actualCheckoutTime, orderVO.latestExecutedTime,
                    rooms, orderVO.numOfCustomers, orderVO.haveChildren, orderVO.remarks, orderVO.promotionName, orderVO.initialPrice, orderVO.finalPrice, OrderStatus.UNEXECUTED);
            if (order_dataService_stub.addOrder(orderPO))
                return ResultMessage.Order_CreateOrderSuccess;
            else
                return ResultMessage.Fail;
        }
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
        if (orderPO.getOrderType().equals(OrderStatus.UNEXECUTED)) {
            orderPO.setOrderType(OrderStatus.REVOKED);
            if (order_dataService_stub.updateOrder(orderPO)) {
                orderVO.orderType = OrderStatus.EXECUTED;
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
            orderPO.setOrderType(OrderStatus.EXECUTED);
            orderPO.setEstimatedCheckoutTime(orderVO.estimatedCheckoutTime);
            orderPO.setActualCheckinTime(orderVO.actualCheckinTime);
            if (order_dataService_stub.updateOrder(orderPO)) {

                orderVO.orderType = OrderStatus.EXECUTED;
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
            orderPO.setOrderType(OrderStatus.FINISHED_UNEVALUATED);
            orderPO.setActualCheckoutTime(orderVO.actualCheckoutTime);
            if (order_dataService_stub.updateOrder(orderPO)) {
                orderVO.orderType = OrderStatus.FINISHED_UNEVALUATED;
                return ResultMessage.Order_EndOrderSuccess;
            } else
                return ResultMessage.Fail;
        } else {
            return ResultMessage.Fail;
        }
    }

    /**
     * 设为异常订单（暂时还未考虑透彻），定时器
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage setAbnormal(OrderVO orderVO) throws RemoteException {
        orderPO = order_dataService_stub.getOrderByOrderID(orderVO.orderID);
        orderPO.setOrderType(OrderStatus.ABNORMAL);
        orderVO.orderType = OrderStatus.ABNORMAL;
        if (order_dataService_stub.updateOrder(orderPO))
            return ResultMessage.Order_SetAbnormalSuccess;
        else
            return ResultMessage.Fail;
    }

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
            orderPO.setOrderType(OrderStatus.REVOKED);
            if (order_dataService_stub.updateOrder(orderPO)) {
                orderVO.orderType = OrderStatus.REVOKED;
                return ResultMessage.Order_RenewOrderSuccess;
            } else
                return ResultMessage.Fail;
        } else {
            return ResultMessage.Fail;
        }
    }

}
