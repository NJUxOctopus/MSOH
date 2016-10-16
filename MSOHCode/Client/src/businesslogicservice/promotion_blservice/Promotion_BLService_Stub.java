package businesslogicservice.promotion_blservice;

import java.util.Date;
import java.util.List;

import util.MemberType;
import util.ResultMessage;
import vo.PromotionVO;

/**
 * 
 * @author Pxr create:10.16 latest modify:10.16
 *
 */
public class Promotion_BLService_Stub implements Promotion_BLService {

	PromotionVO promotionVO;
	
	/**
	 * 添加营销策略
	 */
	@Override
	public ResultMessage addPromotion(PromotionVO promotionVO) {
		//若策略名为空，返回有信息空白
		if (promotionVO.promotionName.equals("")) {
			return ResultMessage.Blank;
		} else if (promotionVO.promotionName.equals("预订特惠") && promotionVO.startTime.equals(11.25)
				&& promotionVO.endTime.equals(11.27) && promotionVO.targetUser.equals(MemberType.NONMEMBER)
				&& promotionVO.discount == 9) {
			//正确填写策略信息，返回添加成功
			return ResultMessage.Promotion_AddPromotionSuccess;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据名字、日期获取营销策略
	 */
	@Override
	public PromotionVO getSingle(String hotelID) {
		PromotionVO promotionVO = new PromotionVO();
		return promotionVO;
	}

}
