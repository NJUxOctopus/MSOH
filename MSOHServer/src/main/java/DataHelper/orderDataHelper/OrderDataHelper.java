package DataHelper.orderDataHelper;

import po.OrderPO;
import util.OrderStatus;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface OrderDataHelper {
    public boolean addOrder(OrderPO po);

    public boolean updateOrder(OrderPO orderPO);

    public boolean deleteOrder(OrderPO orderPO);

    public List<OrderPO> getAllOrders();

    public OrderPO getOrderByOrderID(String orderID);

    public List<OrderPO> findOrderByCustomerID(String customerID);

    public List<OrderPO> findOrderByHotelID(String hotelID);

    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus);


}
