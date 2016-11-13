package ui.view.controllerservice;

import util.OrderStatus;
import util.ResultMessage;
import vo.OrderVO;

import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface ProcessOrder {
    public ResultMessage cancelOrder(OrderVO orderVO);

    public ResultMessage setAbnormal(OrderVO orderVO);

    public ResultMessage renewOrder(OrderVO orderVO);

    public List<OrderVO> getOrderByStatus(OrderStatus status);

    public List<OrderVO> getOrderByCustomerName (String customerName);

    public List<OrderVO> getOrderByHotelName(String hotelID);

}
