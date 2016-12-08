package businesslogicservice.promotion_blservice;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import util.MemberType;
import util.ResultMessage;
import vo.OrderVO;
import vo.PromotionVO;

public interface Promotion_BLService {
    public ResultMessage addWebPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException;

    public ResultMessage addHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException;

    public ResultMessage modifyWebPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException;

    public ResultMessage modifyHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException;

    public ResultMessage deletePromotion(String promotionID) throws RemoteException;

    public List<PromotionVO> promotionRequirements(OrderVO orderVO)
            throws IOException, ClassNotFoundException;
}
