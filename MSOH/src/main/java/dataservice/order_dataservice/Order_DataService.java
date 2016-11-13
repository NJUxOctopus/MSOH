package dataservice.order_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;
/**
 * 
 * @author zqh
 *
 */
public interface Order_DataService extends Remote{
	
	public ResultMessage add(OrderPO po) throws RemoteException;
	
	public List<OrderPO> findByCustomerID(String customerID) throws RemoteException;
	
	public List<OrderPO> findByCustomerIDAndOrderStatus(String customerID, OrderStatus orderStatus) throws RemoteException;
	
	public List<OrderPO> findByHotelID(String hotelID)  throws RemoteException;
	
	public List<OrderPO> findByOrderStatus(OrderStatus orderStatus) throws RemoteException;
	
	public double getPrice(OrderPO po) throws RemoteException;
	
	public ResultMessage changeOrderStatus(OrderPO po, OrderStatus condition) throws RemoteException;
	
	public OrderStatus getOrderStatus(OrderPO po) throws RemoteException;
	
	public ResultMessage setActualCheckinTime(OrderPO po, Date actualCheckinTime) throws RemoteException;
	
	public ResultMessage setActualCheckoutTime(OrderPO po, Date actualCheckoutTime) throws RemoteException;
	
	public Date getLatestExecutedTime(OrderPO po) throws RemoteException;
	
	
}
