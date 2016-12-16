package dataservice.order_dataservice;

import po.OrderPO;
import util.OrderStatus;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author zqh
 */
public interface Order_DataService extends Remote {
    // 新增订单
    public boolean addOrder(OrderPO po) throws IOException, ClassNotFoundException;

    // 获取所有订单
    public List<OrderPO> getAllOrders() throws IOException, ClassNotFoundException;

    // 根据订单ID获取订单
    public OrderPO getOrderByOrderID(String orderID) throws RemoteException;

    // 根据客户ID查找订单
    public List<OrderPO> findOrderByCustomerID(String customerID) throws IOException, ClassNotFoundException;

    // 根据酒店ID查找订单
    public List<OrderPO> findOrderByHotelID(String hotelID) throws IOException, ClassNotFoundException;

    // 根据订单状态查找订单
    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) throws IOException, ClassNotFoundException;

    // 更新订单信息
    public boolean updateOrder(OrderPO orderPO) throws RemoteException;

    // 删除订单
    public boolean deleteOrder(OrderPO orderPO) throws RemoteException;
}
