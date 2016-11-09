package dataservice.datafactory_dataservice;

import po.CreditRecordPO;
import po.CustomerPO;
import util.ResultMessage;

public class Datafactory_DataService_Driver {
	public void drive(Datafactory_DataService datafactory_DataService){
		CustomerPO customerPO=new CustomerPO();
		customerPO.setCredit(2000);
		CreditRecordPO crPO=new CreditRecordPO();
		
		
		ResultMessage result=datafactory_DataService.addCredit(customerPO, 100);
		if(result==ResultMessage.Datafactory_CreditChangeSuccess)
			System.out.println("Add Credit Successfully!");
		else
			System.out.println("Add Credit Failure!");
		
		
		result=datafactory_DataService.decreaseCredit(customerPO, 200);
		if(result==ResultMessage.Datafactory_CreditChangeSuccess)
			System.out.println("Decrease Credit Successfully!");
		else
			System.out.println("Decrease Credit Failure!");
		
		result=datafactory_DataService.addCreditRecord(crPO);
		if(result==ResultMessage.Datafactory_AddCreditRecordSuccess)
			System.out.println("Add CreditRecord Successfully!");
		else
			System.out.println("Add CreditRecord Failure!");
	}
}
