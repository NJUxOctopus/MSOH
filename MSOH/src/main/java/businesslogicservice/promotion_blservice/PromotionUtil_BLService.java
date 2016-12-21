package businesslogicservice.promotion_blservice;

import util.PromotionType;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface PromotionUtil_BLService {

    public PromotionVO getSingle(String promotionID) throws RemoteException;

    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp timestamp) throws ClassNotFoundException, IOException;

    public List<PromotionVO> getAllWebPromotions(Timestamp timestamp) throws IOException, ClassNotFoundException;

    public List<PromotionVO> getPromotionByTypeAndHotelID(PromotionType promotionType,String hotelID,Timestamp timestamp)throws IOException, ClassNotFoundException;

    public List<PromotionVO> getHotelPromotionBetweenTwoDate(String hotelID,Timestamp timestamp1,Timestamp timestamp2)throws IOException, ClassNotFoundException;

    public List<PromotionVO> mergePromotionList(List<PromotionVO> list1,List<PromotionVO> list2)throws RemoteException;

    public List<PromotionVO> getPromotionByType(PromotionType promotionType,Timestamp timestamp)throws IOException, ClassNotFoundException;

    public List<PromotionVO> getHotelPromotion(String hotelID, Timestamp timestamp) throws ClassNotFoundException, IOException;
}
