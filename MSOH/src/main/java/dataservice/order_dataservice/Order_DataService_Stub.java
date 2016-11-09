package dataservice.order_dataservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;

/**
 * 
 * @author Ç®¿ÂÓî
 *
 */
public class Order_DataService_Stub implements Order_DataService{

	@Override
	public ResultMessage add(OrderPO po) {
		return ResultMessage.Order_AddOrderSuccess;
	}

	@Override
	public List<OrderPO> findByCustomerID(String customerID) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	@Override
	public List<OrderPO> findByCustomerIDAndOrderStatus(String customerID, OrderStatus orderStatus) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	@Override
	public List<OrderPO> findByHotelID(String hotelID) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	@Override
	public List<OrderPO> findByOrderStatus(OrderStatus orderStatus) {
		ArrayList<OrderPO> order=new ArrayList<OrderPO>();
		order.add(new OrderPO());
		return order;
	}

	@Override
	public double getPrice(OrderPO po) {
		return po.getFinalPrice();
	}

	@Override
	public ResultMessage changeOrderStatus(OrderPO po, OrderStatus condition) {
		if(po.getOrderID().equals("111111111"))
			return ResultMessage.Order_ChangeOrderStatusSuccess;
		else
			return ResultMessage.Order_ChangeOrderStatusFailure;

	}

	@Override
	public OrderStatus getOrderStatus(OrderPO po) {
		if(po.getOrderID().equals("111111111"))
			return po.getOrderType();
		else
			return null;
	}

	@Override
	public ResultMessage setActualCheckinTime(OrderPO po, Date actualCheckinTime) {
		if(po.getOrderID().equals("111111111"))
			return ResultMessage.Order_SetActualCheckinTimeSuccess;
		else
			return ResultMessage.Order_SetActualCheckinTimeFailure;
	}

	@Override
	public ResultMessage setActualCheckoutTime(OrderPO po, Date actualCheckoutTime) {
		if(po.getOrderID().equals("111111111"))
			return ResultMessage.Order_SetActualCheckoutTimeSuccess;
		else
			return ResultMessage.Order_SetActualCheckoutTimeFailure;
	}

	@Override
	public Date getLatestExecutedTime(OrderPO po) {
		if(po.getOrderID().equals("111111111"))
			return po.getLatestExecutedTime();
		else
			return null;	
	}
	
}
