package dataservice.promotion_dataservice;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import po.HotelPO;
import po.PromotionPO;
import util.MemberType;
import util.ResultMessage;

/**
 * @author zqh
 */
public class Promotion_DataService_Stub implements Promotion_DataService {

    public boolean addPromotion(PromotionPO po) throws RemoteException {
        return false;
    }

    public PromotionPO getPromotion(String promotionID) throws RemoteException {
        if (promotionID.equals("123456")) {

            return new PromotionPO("pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                    "RUJIA",new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 29, 9, 0, 0, 0),9,3,123456);
        }else
            return null;
    }

    public List<PromotionPO> getAllPromotions() throws RemoteException {
        List<String> targetHotel = new ArrayList<String>();
        targetHotel.add("RUJIA");
        List<PromotionPO> promotionPOList = new ArrayList<PromotionPO>();

        promotionPOList.add(new PromotionPO("pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                "RUJIA",new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 29, 9, 0, 0, 0),9,3,123456));
        return promotionPOList;
    }

    public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }

    public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }
}
