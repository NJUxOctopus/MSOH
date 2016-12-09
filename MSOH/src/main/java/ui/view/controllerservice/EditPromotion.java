package ui.view.controllerservice;

import util.ResultMessage;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface EditPromotion {
    public ResultMessage addHotelPromotion(PromotionVO promotionVO);

    public ResultMessage addWebPromotion(PromotionVO promotionVO);

    public ResultMessage modifyPromotion(PromotionVO promotionVO);

    public ResultMessage deletePromotion(String promotionID);

    public PromotionVO getSingle(String promotionID) throws RemoteException;

    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp time) throws RemoteException, ClassNotFoundException, IOException ;

}
