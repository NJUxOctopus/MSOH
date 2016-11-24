package dataservice.customer_dataservice;

import java.rmi.RemoteException;
import java.util.List;

import po.CustomerPO;
import po.HotelPO;
import util.ResultMessage;
/**
 * 
 * @author zqh
 *
 */
public class Customer_DataService_Driver {
	public void drive(Customer_DataService customer_DataService)throws RemoteException{
		CustomerPO customerPO=new CustomerPO();
		customerPO.setID("320581199701012016");
		
		boolean result1=customer_DataService.addCustomer(customerPO);

		
		
		
		
		
	}
}
