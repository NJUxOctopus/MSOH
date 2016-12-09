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
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class ProcessOrderController implements ProcessOrder {
    private OrderUtil_BLService orderUtil_blService;
    private Order_BLService order_blService;

    public ProcessOrderController() {
        orderUtil_blService = new OrderUtil();
        order_blService = new Order();
    }

    /**
     * 新建订单
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage createOrder(OrderVO orderVO) throws RemoteException{
        return order_blService.createOrder(orderVO);
    }

    public ResultMessage cancelOrder(OrderVO orderVO) {
        return null;
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

    public List<OrderVO> getOrderByCustomerID(String customerID) throws RemoteException {
        return orderUtil_blService.getOrdersByCustomerID(customerID);
    }

    public List<OrderVO> getOrderByHotelName(String hotelID) {
        return null;
    }

    public List<OrderVO> getOrderByIDAndStatus(String customerID, OrderStatus orderStatus) throws RemoteException {
        return null;
    }

    /**
     * 根据酒店ID得到该酒店不同状态的订单
     *
     * @param hotelID
     * @param status
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByHotelAndStatus(String hotelID, OrderStatus status) throws RemoteException {
        return orderUtil_blService.getOrderByHotelAndStatus(hotelID, status);
    }

    /**
     * 将订单按照时间排序
     *
     * @param list
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> sortByTime(List<OrderVO> list) throws RemoteException {
        return orderUtil_blService.sortByTime(list);
    }

    /**
     * 根据订单号得到一个订单
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public OrderVO getSingle(String ID) throws RemoteException {
        return orderUtil_blService.getSingle(ID);
    }


}
