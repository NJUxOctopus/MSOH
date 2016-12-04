package businesslogicservice.promotionUtil_blservice;

import vo.PromotionVO;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface PromotionUtil_BLService {
    public List<PromotionVO> getAll (Date date)throws RemoteException;

    public PromotionVO getSingle(String promotionID)throws RemoteException;

    public List<PromotionVO> getPromotionByHotelID(String hotelID)throws RemoteException;
}
