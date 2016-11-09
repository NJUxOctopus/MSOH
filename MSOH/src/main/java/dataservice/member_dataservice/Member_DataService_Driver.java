package dataservice.member_dataservice;

import po.MemberLevelPO;
import po.MemberPO;
import util.ResultMessage;
/**
 * 
 * @author Ç®¿ÂÓî
 *
 */
public class Member_DataService_Driver {
	public void drive(Member_DataService member_DataService){
		MemberPO memberPO = new MemberPO();
		MemberLevelPO memberlevelPO = new MemberLevelPO();
		memberPO.setID("320581199810101111");
		
		ResultMessage result1 = member_DataService.add(memberPO);
		if(result1 == ResultMessage.Member_AddMemberSuccess){
			System.out.println("Add Member Successfully!");
		}
		
		result1 = member_DataService.upgrade(1);
		if(result1 == ResultMessage.Member_Upgrade){
			System.out.println("Member Updates Successfully!");
		}
		
		result1 = member_DataService.degrade(1);
		if(result1 == ResultMessage.Member_Degrade){
			System.out.println("Member Dedates Successfully!");
		}
		
		result1 = member_DataService.delete(memberPO);
		if(result1 == ResultMessage.Manager_DeleteMemberSuccess){
			System.out.println("Delete Member Successfully!");
		}
		
		
		
		ResultMessage result2 = member_DataService.add(memberPO);
		if(result1 == ResultMessage.Member_AddMemberAlreadyExist){
			System.out.println("Add Member Failure!");
		}
		
		result2 = member_DataService.delete(memberPO);
		if(result1 == ResultMessage.Member_MemberNotExist){
			System.out.println("Delete Member Failure!");
		}
		
		int result3 = member_DataService.getGrade(memberPO);
		if(result3 != -1){
			System.out.println("Get Member Level Successfully!");
		}
		else
			System.out.println("Get Member Level Failure!");
		
		result3 = member_DataService.getNumberOfMemberLevel(memberlevelPO);
		if(result3 != -1){
			System.out.println("Get Member Level Successfully!");
		}
		else
			System.out.println("Get Member Level Failure!");
		
		MemberPO result4 = member_DataService.getMember("320581199810101111");
		if(result4 == null) 
			System.out.println("Get Member Failure!");
		else
			System.out.println("Get Member Successfully!");
	}
}
