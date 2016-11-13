package businesslogicservice.promotion_blservice;

import java.util.Date;
import java.util.List;

import util.ResultMessage;
import vo.PromotionVO;

public interface Promotion_BLService {
	public ResultMessage addPromotion(PromotionVO promotionVO);

	public ResultMessage modifyPromotion(PromotionVO promotionVO);

	public ResultMessage deletePromotion(String promotionID);
}
