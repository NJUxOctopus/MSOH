package DataHelper;

import po.PromotionPO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface PromotionDataHelper {
    public boolean addPromotion(PromotionPO po);

    public PromotionPO getPromotion(int promotionID);

    public List<PromotionPO> getAllPromotions();

    public boolean deletePromotion(PromotionPO promotionPO);

    public boolean modifyPromotion(PromotionPO promotionPO);
}
