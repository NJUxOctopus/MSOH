package businesslogicservice.promotion_blservice;

import po.PromotionPO;
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

    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp timestamp) throws RemoteException, ClassNotFoundException, IOException;

    public List<PromotionVO> getAllWebPromotions(Timestamp timestamp) throws IOException, ClassNotFoundException;

    public List<PromotionVO> getPromotionByTypeAndHotelID(PromotionType promotionType,String hotelID,Timestamp timestamp)throws IOException, ClassNotFoundException;
}
