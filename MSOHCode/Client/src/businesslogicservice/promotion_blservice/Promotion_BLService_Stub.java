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
	 * ���Ӫ������
	 */
	@Override
	public ResultMessage addPromotion(PromotionVO promotionVO) {
		//��������Ϊ�գ���������Ϣ�հ�
		if (promotionVO.promotionName.equals("")) {
			return ResultMessage.Blank;
		} else if (promotionVO.promotionName.equals("Ԥ���ػ�") && promotionVO.startTime.equals(11.25)
				&& promotionVO.endTime.equals(11.27) && promotionVO.targetUser.equals(MemberType.NONMEMBER)
				&& promotionVO.discount == 9) {
			//��ȷ��д������Ϣ��������ӳɹ�
			return ResultMessage.Promotion_AddPromotionSuccess;
		} else {
			return null;
		}
	}
	
	/**
	 * �������֡����ڻ�ȡӪ������
	 */
	@Override
	public PromotionVO getSingle(Date date, String name) {
		return promotionVO;
	}

}
