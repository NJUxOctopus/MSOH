package dataservice.promotion_dataservice;

import java.util.Date;
import java.util.List;

import po.HotelPO;
import po.PromotionPO;
import util.MemberType;
import util.ResultMessage;
/**
 * 
 * @author Ç®¿ÂÓî
 *
 */
public class Promotion_DataService_Stub implements Promotion_DataService{

	@Override
	public ResultMessage add(PromotionPO po) {
			return ResultMessage.Promotion_AddPromotionSuccess;
	}

	@Override
	public String getTargetAera(PromotionPO po) {
		if(po.getPromotionID().equals("2016101501"))
			return po.getTargetArea();
		else
			return null;
	}

	@Override
	public List<String> getTargetHotel(PromotionPO po) {
		if(po.getPromotionID().equals("2016101501"))
			return po.getTargetHotel();
		else
			return null;
	}

	@Override
	public MemberType getTargetUser(PromotionPO po) {
		if(po.getPromotionID().equals("2016101501"))
			return po.getTargetUser();
		else
			return null;		
	}

	@Override
	public Date getStartTime(PromotionPO po) {
		if(po.getPromotionID().equals("2016101501"))
			return po.getStartTime();
		else
			return null;	
	
	}

	@Override
	public Date getEndTime(PromotionPO po) {
		if(po.getPromotionID().equals("2016101501"))
			return po.getEndTime();
		else
			return null;	

	}

	@Override
	public double getDiscount(PromotionPO po) {
		if(po.getPromotionID().equals("2016101501"))
			return po.getDiscount();
		else
			return -1;	

	}

	@Override
	public int getMinRoom(PromotionPO po) {
		if(po.getPromotionID().equals("2016101501"))
			return po.getMinRoom();
		else
			return -1;	
	}

}
