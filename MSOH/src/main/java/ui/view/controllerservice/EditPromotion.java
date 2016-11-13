package ui.view.controllerservice;

import util.ResultMessage;
import vo.PromotionVO;

/**
 * Created by apple on 16/11/10.
 */
public interface EditPromotion {
    public ResultMessage addHotelPromotion (PromotionVO promotionVO);

    public ResultMessage addWebPromotion (PromotionVO promotionVO);

    public ResultMessage modifyPromotion (PromotionVO promotionVO);

    public ResultMessage deletePromotion (String promotionID);
}
