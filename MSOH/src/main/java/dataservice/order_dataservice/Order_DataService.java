package dataservice.order_dataservice;

import java.util.Date;
import java.util.List;

import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;
/**
 * 
 * @author Ǯ����
 *
 */
public interface Order_DataService {

	public OrderPO getOrderByOrderID(String OrderID);

	public OrderPO getOrderByHotelID(String hotelID);
	
	public ResultMessage add(OrderPO po);
	
	public List<OrderPO> findByCustomerID(String customerID);
	
	public List<OrderPO> findByCustomerIDAndOrderStatus(String customerID, OrderStatus orderStatus);
	
	public List<OrderPO> findByHotelID(String hotelID);
	
	public List<OrderPO> findByOrderStatus(OrderStatus orderStatus);
	
	public double getPrice(OrderPO po);
	
	public ResultMessage changeOrderStatus(OrderPO po, OrderStatus condition);
	
	public OrderStatus getOrderStatus(OrderPO po);
	
	public ResultMessage setActualCheckinTime(OrderPO po, Date actualCheckinTime);
	
	public ResultMessage setActualCheckoutTime(OrderPO po, Date actualCheckoutTime);

	public OrderPO setEstimatedCheckoutTime(OrderPO orderPO,Date estimatedCheckoutTime);
	
	public Date getLatestExecutedTime(OrderPO po);
	
	
}
