package dataservice.marketer_dataservice;

import java.util.List;

import po.MarketerPO;
import util.ResultMessage;
import util.WorkerPosition;


public class Marketer_DataService_Driver {
	public void drive(Marketer_DataService marketer_DataService){
		MarketerPO marketerPO = new MarketerPO();
		marketerPO.setID("320581199810101111");
		
		ResultMessage result1 = marketer_DataService.add(marketerPO);
		if(result1 == ResultMessage.Manager_AddMarketerSuccess)
			System.out.println("Add Marketer Successfully");
		
		result1 = marketer_DataService.modify(marketerPO);
		if(result1 == ResultMessage.Manager_ChangeMarketerInfoSuccess)
			System.out.println("Modify Marketer Successfully");
		
		result1 = marketer_DataService.delete(marketerPO);
		if(result1 == ResultMessage.Manager_DeleteMarketerSuccess)
			System.out.println("Delete Marketer Successfully");
		
		marketerPO.setID("320581199810102222");
		
		ResultMessage result2 = marketer_DataService.add(marketerPO);
		if(result1 == ResultMessage.Manager_AddMarketerAlreadyExist)
			System.out.println("Add Marketer Failure");
		
		result1 = marketer_DataService.modify(marketerPO);
		if(result1 == ResultMessage.Marketer_MarketerNotExist)
			System.out.println("Modify Marketer Failure");
		
		result1 = marketer_DataService.delete(marketerPO);
		if(result1 == ResultMessage.Marketer_MarketerNotExist)
			System.out.println("Delete Marketer Failure");
		
		List<MarketerPO> listResult = marketer_DataService.findByMarketerName("admin");
		if(!listResult.isEmpty()){
			System.out.println("Find Marketer Successfully!");
		}else{
			System.out.println("Find Marketer Failure!");
		}
		
		listResult = marketer_DataService.findByMarketerID("320581199810101111");
		if(!listResult.isEmpty()){
			System.out.println("Find Marketer Successfully!");
		}else{
			System.out.println("Find Marketer Failure!");
		}
		
		listResult = marketer_DataService.findByPosition(WorkerPosition.Marketer);
		if(!listResult.isEmpty()){
			System.out.println("Find Marketer Successfully!");
		}else{
			System.out.println("Find Marketer Failure!");
		}

	}
}
