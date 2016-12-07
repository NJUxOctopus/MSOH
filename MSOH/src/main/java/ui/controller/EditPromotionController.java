package ui.controller;

import businesslogic.promotion_bl.PromotionUtil;
import businesslogicservice.promotionUtil_blservice.PromotionUtil_BLService;
import ui.view.controllerservice.EditPromotion;
import util.ResultMessage;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class EditPromotionController implements EditPromotion {
    private PromotionUtil_BLService promotionUtil_blService;

    public EditPromotionController(){
        promotionUtil_blService = new PromotionUtil();
    }
    public ResultMessage addHotelPromotion(PromotionVO promotionVO) {
        return null;
    }

    public ResultMessage addWebPromotion(PromotionVO promotionVO) {
        return null;
    }

    public ResultMessage modifyPromotion(PromotionVO promotionVO) {
        return null;
    }

    public ResultMessage deletePromotion(String promotionID) {
        return null;
    }

    public PromotionVO getSingle(String promotionID) throws RemoteException {
        return  promotionUtil_blService.getSingle(promotionID);
    }

    public List<PromotionVO> getPromotionByHotelID(String hotelID) throws RemoteException, ClassNotFoundException, IOException {
        return promotionUtil_blService.getPromotionByHotelID(hotelID);
    }
}
