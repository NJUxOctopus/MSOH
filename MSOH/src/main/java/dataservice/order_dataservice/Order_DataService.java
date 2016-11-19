package dataservice.order_dataservice;

import po.OrderPO;
import util.OrderStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author zqh
 *
 */
public interface Order_DataService extends Remote {
	// 新增订单
	public boolean addOrder(OrderPO po) throws RemoteException;
	// 获取所有订单
	public List<OrderPO> getAllOrders() throws  RemoteException;
	// 根据订单ID获取订单
	public OrderPO getOrderByOrderID(String OrderID) throws RemoteException;
	// 根据客户ID查找订单
	public List<OrderPO> findOrderByCustomerID(String customerID) throws RemoteException;
	// 根据酒店ID查找订单
	public List<OrderPO> findOrderByHotelID(String hotelID) throws RemoteException;
	// 根据订单状态查找订单
	public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) throws RemoteException;
	// 更新订单信息
	public boolean updateOrder(OrderPO orderPO) throws RemoteException;
	// 删除订单
	public boolean deleteOrder(OrderPO orderPO) throws RemoteException;
}
