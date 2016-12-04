package businesslogic.promotion_bl;

import businesslogicservice.promotionUtil_blservice.PromotionUtil_BLService;
import businesslogicservice.promotion_blservice.Promotion_BLService;
import dataservice.promotion_dataservice.Promotion_DataService_Stub;
import po.PromotionPO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.Timestamp;
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
     * 根据ID得到一个营销策略
     *
     * @param promotionID
     * @return
     * @throws RemoteException
     */
    public PromotionVO getSingle(String promotionID) throws RemoteException {
        if (promotionID.equals(""))
            //若ID为空
            return null;
        if (promotion_dataService_stub.getPromotion(Integer.parseInt(promotionID)) == null)
            //若不存在该营销策略
            return null;
        PromotionPO promotionPO = promotion_dataService_stub.getPromotion(Integer.parseInt(promotionID));
        String[] targetHotel = promotionPO.getTargetHotel().split(";");
        return new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                promotionPO.getTargetUser(), promotionPO.getTargetArea(), targetHotel, promotionPO.
                getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                "" + promotionPO.getPromotionID(), promotionPO.getPromotionType());
    }

    /**
     * 得到该酒店当天的所有促销策略
     *
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> getPromotionByHotelID(String hotelID) throws RemoteException, ClassNotFoundException, IOException {
        List<PromotionPO> promotionPOList = promotion_dataService_stub.getPromotionByHotelID(hotelID);
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        if (promotionPOList == null || promotionPOList.isEmpty())
            return null;
        for (int i = 0; i < promotionPOList.size(); i++) {
            PromotionPO promotionPO = promotionPOList.get(i);
            String[] targetHotel = promotionPO.getTargetHotel().split(";");
            if (new Date(System.currentTimeMillis()).after(promotionPO.getStartTime()) && new Date(System.currentTimeMillis()).after(
                    promotionPO.getEndTime()))
                promotionVOList.add(new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                        promotionPO.getTargetUser(), promotionPO.getTargetArea(), targetHotel, promotionPO.
                        getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                        "" + promotionPO.getPromotionID(), promotionPO.getPromotionType()));
        }
        return promotionVOList;
    }

    /**
     * 得到当天所有的网站促销策略
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionVO> getAllWebPromotions() throws IOException, ClassNotFoundException {
        List<PromotionPO> promotionPOList = promotion_dataService_stub.getAllWebPromotions();
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        if (promotionPOList == null || promotionPOList.isEmpty())
            return null;
        for (int i = 0; i < promotionPOList.size(); i++) {
            PromotionPO promotionPO = promotionPOList.get(i);
            String[] targetHotel = promotionPO.getTargetHotel().split(";");
            if (new Date(System.currentTimeMillis()).after(promotionPO.getStartTime()) && new Date(System.currentTimeMillis()).after(
                    promotionPO.getEndTime()))
                promotionVOList.add(new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                        promotionPO.getTargetUser(), promotionPO.getTargetArea(), targetHotel, promotionPO.
                        getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                        "" + promotionPO.getPromotionID(), promotionPO.getPromotionType()));
        }
        return promotionVOList;
    }
}
