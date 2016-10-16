package dataservice.customer_dataservice;

import java.util.List;

import po.CustomerPO;
import po.HotelPO;
import util.ResultMessage;
/**
 * 
 * @author ÷‹«ﬂ∫≠
 *
 */
public class Customer_DataService_Driver {
	public void drive(Customer_DataService customer_DataService){
		CustomerPO customerPO=new CustomerPO();
		customerPO.setID("320581199701012016");
		
		ResultMessage result1=customer_DataService.add(customerPO);
		if(result1==ResultMessage.Manager_AddCustomerAlreadyExist){
			System.out.println("Add Customer Failure!");
		}
		
		result1=customer_DataService.modify(customerPO);
		if(result1==ResultMessage.Manager_ChangeCustomerInfoSuccess){
			System.out.println("Modify CustomerInfo Successfully!");
		}
		
		String customerID=customer_DataService.getID(customerPO);
		if(customerID.equals("320581190001012016")){
			System.out.println("Get CustomerID Successfully!");
		}else{
			System.out.println("Get CustomerID Failure!");
		}
		
		int currentCredit=customer_DataService.getCurrentCredit("320581190001012016");
		if(currentCredit==1000){
			System.out.println("Get Current Credit Successfully!");
		}else{
			System.out.println("Get Current Credit Failure!");
		}
		
		List<CustomerPO> customerList=customer_DataService.find("320581190001012016");
		if(!customerList.isEmpty()){
			System.out.println("Find Customer Successfully!");
		}else{
			System.out.println("No Customer Exist!");
		}
		
		List<HotelPO> hotelList=customer_DataService.getReservedHotel(customerPO);
		if(!hotelList.isEmpty()){
			System.out.println("Get ReservedHotel Successfully!");
		}else{
			System.out.println("No ReservedHotel!");
		}
		
		customerPO.setID("012345678901234567");
		ResultMessage result2=customer_DataService.add(customerPO);
		if(result2==ResultMessage.Manager_AddCustomerSuccess){
			System.out.println("Add Customer Successfully!");
		}
		
		result2=customer_DataService.modify(customerPO);
		if(result2==ResultMessage.Customer_CustomerNotExist){
			System.out.println("Modify Customer Failure!");
		}
		
		
		
		
		
	}
}
