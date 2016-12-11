package ui.controller;

import businesslogic.promotion_bl.PromotionUtil;
import businesslogicservice.promotion_blservice.PromotionUtil_BLService;
import ui.view.controllerservice.EditPromotion;
import util.ResultMessage;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
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

    /**
     * 根据促销策略id获得策略
     * @param promotionID
     * @return
     * @throws RemoteException
     */
    public PromotionVO getSingle(String promotionID) throws RemoteException {
        return  promotionUtil_blService.getSingle(promotionID);
    }

    /**
     * 根据酒店id获得促销策略
     * @param hotelID
     * @param time
     * @return
     * @throws RemoteException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp time) throws RemoteException, ClassNotFoundException, IOException {
        return promotionUtil_blService.getPromotionByHotelID(hotelID, time);
    }
}
