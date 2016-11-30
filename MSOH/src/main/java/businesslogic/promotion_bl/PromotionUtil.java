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

    /**
     * 得到某一天所有的营销策略
     * @param date
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> getAll(Date date) throws RemoteException {
        if(date==null)
            //若日期为空
            return null;
        List<PromotionPO> allPromotion = promotion_dataService_stub.getAllPromotions();
        if (allPromotion == null||allPromotion.isEmpty())
            //若列表为空
            return null;
        List<PromotionVO> datePromotion = new ArrayList<PromotionVO>();
        Iterator iterator = allPromotion.iterator();
        while (iterator.hasNext()) {
            PromotionPO promotionPO = (PromotionPO) iterator.next();
            if (promotionPO.getStartTime().before(date) && promotionPO.getEndTime().after(date)) {
                String[] targetHotel = promotionPO.getTargetHotel().split(";");
                datePromotion.add(new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                        promotionPO.getTargetUser(), promotionPO.getTargetArea(), targetHotel, promotionPO.
                        getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                        promotionPO.getPromotionID()));
            }
        }
        return datePromotion;
    }

    /**
     * 根据ID得到一个营销策略
     * @param promotionID
     * @return
     * @throws RemoteException
     */
    public PromotionVO getSingle(String promotionID) throws RemoteException {
        if(promotionID.equals(""))
            //若ID为空
            return null;
        if(promotion_dataService_stub.getPromotion(promotionID)==null)
            //若不存在该营销策略
            return null;
        PromotionPO promotionPO = promotion_dataService_stub.getPromotion(promotionID);
        String[] targetHotel = promotionPO.getTargetHotel().split(";");
        return new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                promotionPO.getTargetUser(), promotionPO.getTargetArea(), targetHotel, promotionPO.
                getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                promotionPO.getPromotionID());
    }
}
