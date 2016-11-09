package dataservice.clerk_dataservice;

import java.util.List;

import po.ClerkPO;
import util.ResultMessage;
/**
 * 
 * @ߺ�author ���
 *
 */
public class Clerk_DataService_Driver {
	public void drive(Clerk_DataService clerk_DataService){
		ClerkPO clerkPO=new ClerkPO();
		clerkPO.setID("320581190001012016");
		ResultMessage result1=clerk_DataService.add(clerkPO);
		if(result1==ResultMessage.Manager_AddClerkAlreadyExist){
			System.out.println("Add Clerk Successfully!");
		}
		
		result1=clerk_DataService.modify(clerkPO);
		if(result1==ResultMessage.Manager_ChangeClerkInfoSuccess){
			System.out.println("Modify ClerkInfo Successfully!");
		}
		
		result1=clerk_DataService.delete(clerkPO);
		if(result1==ResultMessage.Manager_DeleteClerkSuccess){
			System.out.println("Delete Clerk Successfully!");
		}
		
		clerkPO.setID("012345678901234567");
		ResultMessage result2=clerk_DataService.add(clerkPO);
		if(result2==ResultMessage.Manager_AddClerkSuccess){
			System.out.println("Add Clerk Failure!");
		}
		
		result2=clerk_DataService.modify(clerkPO);
		if(result2==ResultMessage.Clerk_ClerkNotExist){
			System.out.println("Modify ClerkInfo Failure!");
		}
		
		result2=clerk_DataService.delete(clerkPO);
		if(result2==ResultMessage.Clerk_ClerkNotExist){
			System.out.println("Delete Clerk Failure!");
		}
		
		List<ClerkPO> listResult=clerk_DataService.findByClerkID("320581190001012016");
		if(!listResult.isEmpty()){
			System.out.println("Find Clerk Successfully!");
		}else{
			System.out.println("No Clerk Exist!");
		}
		
		listResult=clerk_DataService.findByClerkName("zqh");
		if(!listResult.isEmpty()){
			System.out.println("Find Clerk Successfully!");
		}else{
			System.out.println("No Clerk Exist!");
		}
	}
	
	
}
