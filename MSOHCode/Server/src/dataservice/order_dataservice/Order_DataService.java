package dataservice.order_dataservice;

import java.util.Date;
import java.util.List;

import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;
/**
 * 
 * @author ßLÇßº­
 *
 */
public interface Order_DataService {
	
	public ResultMessage add(OrderPO po);
	
	public List<OrderPO> findByCustomerID(String customerID);
	
	public List<OrderPO> findByCustomerIDAndOrderStatus(String customerID, OrderStatus orderStatus);
	
	public List<OrderPO> findByHotelID(String hotelID);
	
	public List<OrderPO> findByOrderStatus(OrderStatus orderStatus);
	
	public int getPrice(OrderPO po);
	
	public ResultMessage changOrderStatus(OrderPO po, OrderStatus condition);
	
	public OrderStatus getOrderStatus(OrderPO po);
	
	public ResultMessage setActualCheckinTime(OrderPO po, Date actualCheckinTime);
	
	public ResultMessage setActualCheckoutTime(OrderPO po, Date actualCheckoutTime);
	
	public Date getLatestExecutedTime(OrderPO po);
	
	
}
