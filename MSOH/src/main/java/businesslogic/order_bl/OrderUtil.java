package businesslogic.order_bl;

import businesslogicservice.orderUtil_blservice.OrderUtil_BLService;
import dataservice.order_dataservice.Order_DataService_Stub;
import util.OrderStatus;
import vo.OrderVO;
import po.OrderPO;

import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class OrderUtil implements OrderUtil_BLService {
    private OrderVO orderVO;
    private OrderPO orderPO;
    private Order_DataService_Stub order_dataService_stub = new Order_DataService_Stub();
    public OrderVO getSingle(String orderID) {
        orderPO = order_dataService_stub.getOrderByHotelID(orderID);
    }

    public List<OrderVO> getOrdersByCustomerID(String customerID) {
        return null;
    }

    public List<OrderVO> getOrdersByCustomerName(String customerName) {
        return null;
    }

    public List<OrderVO> getOrdersByHotelID(String customerID) {
        return null;
    }

    public List<OrderVO> sortByTime(List<OrderVO> list) {
        return null;
    }

    public List<OrderVO> getOrderByStatus(OrderStatus status) {
        return null;
    }
}
