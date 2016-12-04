package dataservice.promotion_dataservice;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import po.HotelPO;
import po.PromotionPO;
import util.MemberType;
import util.PromotionType;
import util.ResultMessage;

/**
 * @author zqh
 */
public class Promotion_DataService_Stub implements Promotion_DataService {

    @Override
    public boolean addPromotion(PromotionPO po) throws RemoteException {
        return false;
    }

    @Override
    public PromotionPO getPromotion(int promotionID) throws RemoteException {
        return null;
    }

    @Override
    public List<PromotionPO> getAllWebPromotions() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<PromotionPO> getPromotionByHotelID(String hotelID) throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }

    @Override
    public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }
}
