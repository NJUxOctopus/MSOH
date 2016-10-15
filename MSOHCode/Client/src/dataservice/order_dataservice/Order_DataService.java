package dataservice.order_dataservice;

import java.util.Date;
import java.util.List;

import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;

public interface Order_DataService {
	
	public ResultMessage add(OrderPO po);
	
	public List<OrderPO> findByCustomerID(String customerID);
	
	public List<OrderPO> findByCustomerIDAndStatus(String customerID, OrderStatus status);
	
	public List<OrderPO> findByHotelID(String hotelID);
	
	public List<OrderPO> findByStatus(OrderStatus Status);
	
	public int getPrice(OrderPO po);
	
	public ResultMessage changeStatus(OrderPO po, OrderStatus condition);
	
	public OrderStatus getStatus(OrderPO po);
	
	public ResultMessage setActualCheckinTime(OrderPO po, Date actualCheckinTime);
	
	public ResultMessage setActualCheckoutTime(OrderPO po, Date actualCheckoutTime);
	
	public Date getLatestExecutedTime(OrderPO po);
	
	
}
