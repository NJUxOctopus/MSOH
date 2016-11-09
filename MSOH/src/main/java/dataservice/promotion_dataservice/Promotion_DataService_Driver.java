package dataservice.promotion_dataservice;

import java.util.Date;
import java.util.List;

import po.PromotionPO;
import util.MemberType;
import util.ResultMessage;
/**
 * 
 * @author Ç®¿ÂÓî
 *
 */
public class Promotion_DataService_Driver {
	public void drive(Promotion_DataService promotion_DataService){
		PromotionPO promotionPO = new PromotionPO();
		promotionPO.setPromotionID("2015101501");
		
		ResultMessage result1 = promotion_DataService.add(promotionPO);
			System.out.println("Add Promotion Successfully");
			
			
		String result2 = promotion_DataService.getTargetAera(promotionPO);
		if(result2 != null)
			System.out.println("Get Target Aera Successfully");
		else
			System.out.println("Get Target Aera Failure");

		
		List<String> result3 = promotion_DataService.getTargetHotel(promotionPO);
		if(!result3.isEmpty())
			System.out.println("Get Target Hote Successfully");
		else
			System.out.println("Get Target Hote Failure");

		
		MemberType result4 = promotion_DataService.getTargetUser(promotionPO);
		if(result4 != null)
			System.out.println("Get Target User Successfully");
		else
			System.out.println("Get Target User Failure");


		
		Date result5 = promotion_DataService.getStartTime(promotionPO);
		if(result5 != null)
			System.out.println("Get Start Time Successfully");
		else
			System.out.println("Get Start Time Failure");


		result5 = promotion_DataService.getEndTime(promotionPO);
		if(result5 != null)
			System.out.println("Get Start Time Successfully");
		else
			System.out.println("Get Start Time Failure");
		
		double result6 = promotion_DataService.getDiscount(promotionPO);
		if(result6 != -1)
			System.out.println("Get Discount Successfully");
		else
			System.out.println("Get Discount Failure");


		
		int result7 = promotion_DataService.getMinRoom(promotionPO);
		if(result7 != -1)
			System.out.println("Get MinRoom Successfully");
		else
			System.out.println("Get MinRoom Failure");

	}
}
