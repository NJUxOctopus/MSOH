package businesslogic.promotion_bl;

import businesslogicservice.promotionUtil_blservice.PromotionUtil_BLService;
import businesslogicservice.promotion_blservice.Promotion_BLService;
import dataservice.promotion_dataservice.Promotion_DataService_Stub;
import po.PromotionPO;
import vo.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class PromotionUtil implements PromotionUtil_BLService {
    Promotion_DataService_Stub promotion_dataService_stub = new Promotion_DataService_Stub();

    public List<PromotionVO> getAll(Date date) throws RemoteException {
        if(date==null)
            return null;
        List<PromotionPO> allPromotion = promotion_dataService_stub.getAllPromotions();
        if (allPromotion == null)
            return null;
        List<PromotionVO> datePromotion = new ArrayList<PromotionVO>();
        Iterator iterator = allPromotion.iterator();
        while (iterator.hasNext()) {
            PromotionPO promotionPO = (PromotionPO) iterator.next();
            if (promotionPO.getStartTime().before(date) && promotionPO.getEndTime().after(date)) {
                datePromotion.add(new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                        promotionPO.getTargetUser(), promotionPO.getTargetArea(), promotionPO.getTargetHotel(), promotionPO.
                        getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                        promotionPO.getPromotionID()));
            }
        }
        return datePromotion;
    }

    public PromotionVO getSingle(String promotionID) throws RemoteException {
        if(promotion_dataService_stub.getPromotion(promotionID)==null)
            return null;
        PromotionPO promotionPO = promotion_dataService_stub.getPromotion(promotionID);
        return new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                promotionPO.getTargetUser(), promotionPO.getTargetArea(), promotionPO.getTargetHotel(), promotionPO.
                getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                promotionPO.getPromotionID());
    }
}
