package DataHelper;

import po.OrderPO;
import util.OrderStatus;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface OrderDataHelper {
    public void addOrder(OrderPO po);

    public void updateOrder(OrderPO orderPO);

    public void deleteOrder(OrderPO orderPO);

    public List<OrderPO> getAllOrders();

    public OrderPO getOrderByOrderID(String orderID);

    public List<OrderPO> findOrderByCustomerID(String customerID);

    public List<OrderPO> findOrderByHotelID(String hotelID);

    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus);


}
