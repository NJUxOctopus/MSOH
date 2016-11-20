package dataservice.order_dataservice;

import businesslogic.order_bl.Order;
import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Ǯ����
 *
 */
public class Order_DataService_Stub implements Order_DataService{

	public boolean addOrder(OrderPO po) throws RemoteException {
		return false;
	}

	public List<OrderPO> getAllOrders() throws RemoteException {
		return null;
	}

	public OrderPO getOrderByOrderID(String OrderID) throws RemoteException {
		if(OrderID.equals("12138"))
			return new OrderPO("pxr", "12345678910", "320200000000000000", "123",
					"RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
					new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED);
		else
			return null;
	}

	public List<OrderPO> findOrderByCustomerID(String customerID) throws RemoteException {
		if(customerID.equals("320200000000000000")){
			List<OrderPO> orderPOList = new ArrayList<OrderPO>();
			orderPOList.add(new OrderPO("pxr", "12345678910", "320200000000000000", "123",
					"RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
					new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
			return orderPOList;
		}else
			return null;
	}

	public List<OrderPO> findOrderByHotelID(String hotelID) throws RemoteException {
		if(hotelID.equals("123")){
			List<OrderPO> orderPOList = new ArrayList<OrderPO>();
			orderPOList.add(new OrderPO("pxr", "12345678910", "320200000000000000", "123",
					"RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
					new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
			return orderPOList;
		}else
			return null;
	}

	public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) throws RemoteException {
		if(orderStatus.equals(OrderStatus.UNEXECUTED)){
			List<OrderPO> orderPOList = new ArrayList<OrderPO>();
			orderPOList.add(new OrderPO("pxr", "12345678910", "320200000000000000", "123",
					"RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
					new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
			return orderPOList;
		}else
			return null;
	}

	public boolean updateOrder(OrderPO orderPO) throws RemoteException {
		return false;
	}

	public boolean deleteOrder(OrderPO orderPO) throws RemoteException {
		return false;
	}
}
