package ui.controller;

import businesslogic.order_bl.OrderUtil;
import businesslogicservice.orderUtil_blservice.OrderUtil_BLService;
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

    public ProcessOrderController(){
        orderUtil_blService = new OrderUtil();
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

    public List<OrderVO> getOrderByCustomerName(String customerName) {
        return null;
    }

    public List<OrderVO> getOrderByHotelName(String hotelID) {
        return null;
    }

    public List<OrderVO> getOrderByIDAndStatus(String customerID,OrderStatus orderStatus)throws RemoteException{
        return null;
    }

    public OrderVO getSingle(String ID)throws RemoteException{
        return orderUtil_blService.getSingle(ID);
    }


}
