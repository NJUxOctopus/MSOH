package ui.controller;

import ui.view.controllerservice.ProcessOrder;
import util.OrderStatus;
import util.ResultMessage;
import vo.OrderVO;

import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class ProcessOrderController implements ProcessOrder {
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
}
