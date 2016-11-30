package businesslogicservice.promotion_blservice;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import util.MemberType;
import util.ResultMessage;
import vo.PromotionVO;

public interface Promotion_BLService {
    public ResultMessage addPromotion(PromotionVO promotionVO) throws RemoteException;

    public ResultMessage modifyPromotion(PromotionVO promotionVO) throws RemoteException;

    public ResultMessage deletePromotion(String promotionID) throws RemoteException;

    public boolean promotionRequirements(String promotionID, MemberType memberType, String hotelID, String area, int minRoom) throws RemoteException;
}
