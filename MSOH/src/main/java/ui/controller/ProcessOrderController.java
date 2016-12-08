package ui.controller;

import businesslogic.order_bl.Order;
import businesslogic.order_bl.OrderUtil;
import businesslogicservice.order_blservice.OrderUtil_BLService;
import businesslogicservice.order_blservice.Order_BLService;
import ui.view.controllerservice.ProcessOrder;
import util.OrderStatus;
import util.ResultMessage;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class ProcessOrderController implements ProcessOrder {
    private OrderUtil_BLService orderUtil_blService;

    private Order_BLService order_blService;

    public ProcessOrderController(){
        orderUtil_blService = new OrderUtil();
        order_blService = new Order();
    }

    /**
     * 撤销订单
     * @param orderVO
     * @return
     */
    public ResultMessage cancelOrder(OrderVO orderVO) throws RemoteException {
        return order_blService.cancelOrder(orderVO);
    }

    public ResultMessage setAbnormal(OrderVO orderVO) {
        return null;
    }

    public ResultMessage renewOrder(OrderVO orderVO) {
        return null;
    }

    public List<OrderVO> getOrderByStatus(OrderStatus status) {
        return null;
    }

    public List<OrderVO> getOrderByCustomerName(String customerName) {
        return null;
    }

    public List<OrderVO> getOrderByHotelName(String hotelID) {
        return null;
    }

    public List<OrderVO> getOrderByIDAndStatus(String customerID,OrderStatus orderStatus)throws RemoteException{
        return null;
    }

    /**
     * 凭订单id获得单个订单
     * @param ID
     * @return
     * @throws RemoteException
     */
    public OrderVO getSingle(String ID)throws RemoteException{
        return orderUtil_blService.getSingle(ID);
    }

    /**
     * 获得客户在该酒店对应订单状态的订单
     * @param ID
     * @param hotelID
     * @param orderStatus
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByIDAndHotelIDAndStatus(String ID, String hotelID, OrderStatus orderStatus) throws RemoteException {
        return orderUtil_blService.getOrderByIDAndHotelIDAndStatus(ID, hotelID, orderStatus);
    }
}
