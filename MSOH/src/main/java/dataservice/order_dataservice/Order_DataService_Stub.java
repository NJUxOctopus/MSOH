package dataservice.order_dataservice;

import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Ǯ����
 *
 */
public class Order_DataService_Stub implements Order_DataService{

	public OrderPO getOrderByOrderID(String OrderID) {
		return new OrderPO();
	}

	public ResultMessage setEstimatedCheckoutTime(OrderPO orderPO,Date estimatedCheckoutTime) {
		orderPO.setEstimatedCheckoutTime(estimatedCheckoutTime);
		return ResultMessage.Order_SetEstimatedCheckoutTimeSuccess;
	}

	public ResultMessage add(OrderPO po) {
		return ResultMessage.Order_AddOrderSuccess;
	}

	public List<OrderPO> findByCustomerID(String customerID) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	public List<OrderPO> findByCustomerIDAndOrderStatus(String customerID, OrderStatus orderStatus) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	public List<OrderPO> findByHotelID(String hotelID) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	public List<OrderPO> findByOrderStatus(OrderStatus orderStatus) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	public double getPrice(OrderPO po) {
		return po.getFinalPrice();
	}

	public ResultMessage changeOrderStatus(OrderPO po, OrderStatus condition) {
		if(po.getOrderID().equals("111111111"))
			return ResultMessage.Order_ChangeOrderStatusSuccess;
		else
			return ResultMessage.Order_ChangeOrderStatusFailure;

	}

	public OrderStatus getOrderStatus(OrderPO po) {
		if(po.getOrderID().equals("111111111"))
			return po.getOrderType();
		else
			return null;
	}

	public ResultMessage setActualCheckinTime(OrderPO po, Date actualCheckinTime) {
		if(po.getOrderID().equals("111111111"))
			return ResultMessage.Order_SetActualCheckinTimeSuccess;
		else
			return ResultMessage.Order_SetActualCheckinTimeFailure;
	}

	public ResultMessage setActualCheckoutTime(OrderPO po, Date actualCheckoutTime) {
		if(po.getOrderID().equals("111111111"))
			return ResultMessage.Order_SetActualCheckoutTimeSuccess;
		else
			return ResultMessage.Order_SetActualCheckoutTimeFailure;
	}

	public OrderPO getOrderByHotelID(String hotelID) {
		return null;
	}

	public Date getLatestExecutedTime(OrderPO po) {
		if(po.getOrderID().equals("111111111"))
			return po.getLatestExecutedTime();
		else
			return null;	
	}
	
}
