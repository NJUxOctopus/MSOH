package dataservice.order_dataservice;

import java.util.Date;
import java.util.List;

import po.OrderPO;
import util.OrderStatus;
import util.ResultMessage;

public class Order_DataService_Driver {
	public void drive(Order_DataService order_DataService){
		OrderPO orderPO = new OrderPO();
		Date date1 = new Date();
		ResultMessage result1 = order_DataService.add(orderPO);
		if(result1 == ResultMessage.Order_AddOrderSuccess)
			System.out.println("Add Order Successfully");
		
		result1 = order_DataService.changeOrderStatus(orderPO, OrderStatus.ABNORMAL);
		if(result1 == ResultMessage.Order_ChangeOrderStatusSuccess)
			System.out.println("Change Order Status Successfully");
		
		result1 = order_DataService.setActualCheckinTime(orderPO, date1);;
		if(result1 == ResultMessage.Order_SetActualCheckinTimeSuccess)
			System.out.println("Set Actual Checkin Time Successfully");
		
		result1 = order_DataService.setActualCheckoutTime(orderPO, date1);;
		if(result1 == ResultMessage.Order_SetActualCheckoutTimeSuccess)
			System.out.println("Set Actual Checkout Time Successfully");
		
		
		ResultMessage result2 = order_DataService.changeOrderStatus(orderPO, OrderStatus.ABNORMAL);
		if(result1 == ResultMessage.Order_ChangeOrderStatusFailure)
			System.out.println("Change Order Status Failure");
		
		result2 = order_DataService.setActualCheckinTime(orderPO, date1);;
		if(result1 == ResultMessage.Order_SetActualCheckinTimeFailure)
			System.out.println("Set Actual Checkin Time Failure");
		
		result2 = order_DataService.setActualCheckoutTime(orderPO, date1);;
		if(result1 == ResultMessage.Order_SetActualCheckoutTimeFailure)
			System.out.println("Set Actual Checkout Time Failure");
		
		
		List<OrderPO> listResult = order_DataService.findByCustomerID("320581190001012016");
		if(!listResult.isEmpty()){
			System.out.println("Find OrderList Successfully!");
		}else{
			System.out.println("Find OrderList Failure!");
		}
		
		listResult = order_DataService.findByCustomerIDAndOrderStatus("320581190001012016", OrderStatus.ABNORMAL);
		if(!listResult.isEmpty()){
			System.out.println("Find OrderList Successfully!");
		}else{
			System.out.println("Find OrderList Failure!");
		}
		
		listResult = order_DataService.findByHotelID("111111");
		if(!listResult.isEmpty()){
			System.out.println("Find OrderList Successfully!");
		}else{
			System.out.println("Find OrderList Failure!");
		}
		
		listResult = order_DataService.findByOrderStatus(OrderStatus.ABNORMAL);
		if(!listResult.isEmpty()){
			System.out.println("Find OrderList Successfully!");
		}else{
			System.out.println("Find OrderList Failure!");
		}
		
		double result3 = order_DataService.getPrice(orderPO);
		if(result3 != 0)
			System.out.println("Get Order Price Successfully!");
		else
			System.out.println("Get Order Price Failure!");

		Date result4 = order_DataService.getLatestExecutedTime(orderPO);
		if(result4 != null)
			System.out.println("Get Latest Executed Time Successfully!");
		else
			System.out.println("Get Latest Executed Time Failure!");

			
		OrderStatus result5 = order_DataService.getOrderStatus(orderPO);
		if(result5 != null)
			System.out.println("Get Order Status Successfully!");
		else
			System.out.println("Get Order Status Failure!");

		

	}
}
