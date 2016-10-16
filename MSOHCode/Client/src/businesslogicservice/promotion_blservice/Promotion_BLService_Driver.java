package businesslogicservice.promotion_blservice;

import java.util.Date;

import util.ResultMessage;
import vo.PromotionVO;

public class Promotion_BLService_Driver {
	public void drive(Promotion_BLService promotion_BLService){
		PromotionVO promotionVO = new PromotionVO();
		ResultMessage result = promotion_BLService.addPromotion(promotionVO);
		if(result.equals(ResultMessage.Blank))
			System.out.println("Promotion's information is blank");
		if(result.equals(ResultMessage.Promotion_AddPromotionSuccess))
			System.out.println("Promotion adds successfully");
		Date date = new Date();
		promotion_BLService.getSingle(date, "˫11");
	}
}
