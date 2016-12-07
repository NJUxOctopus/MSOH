package businesslogicservice.promotion_blservice;

import po.PromotionPO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface PromotionUtil_BLService {

    public PromotionVO getSingle(String promotionID)throws RemoteException;

    public List<PromotionVO> getPromotionByHotelID(String hotelID)throws RemoteException,ClassNotFoundException,IOException;

    public List<PromotionVO> getAllWebPromotions() throws IOException, ClassNotFoundException;
}
