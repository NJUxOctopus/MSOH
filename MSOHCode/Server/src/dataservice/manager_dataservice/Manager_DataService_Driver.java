package dataservice.manager_dataservice;

import po.ManagerPO;
import util.ResultMessage;

/**
 * 
 * @author Ç®¿ÂÓî
 * 
 */
public class Manager_DataService_Driver {
	public void drive(Manager_DataService manager_DataService){
		ManagerPO managerPO = new ManagerPO();
		
		managerPO.setID("320581190001012019");
		ResultMessage result1 = manager_DataService.modify(managerPO);
		if(result1 == ResultMessage.Manager_ChangeManagerInfoSuccess)
			System.out.println("Modify ManagerInfo Successfully!");
		
		managerPO.setID("320581190001012020");
		ResultMessage result2 = manager_DataService.modify(managerPO);
		if(result2 == ResultMessage.Manager_ManagerNotExist)
			System.out.println("Modify ManagerInfo Failure!");
	}
}
