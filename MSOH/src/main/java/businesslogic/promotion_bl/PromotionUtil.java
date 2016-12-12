package businesslogic.promotion_bl;

import businesslogicservice.promotion_blservice.PromotionUtil_BLService;
import dataservice.promotion_dataservice.Promotion_DataService;
import po.PromotionPO;
import rmi.RemoteHelper;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class PromotionUtil implements PromotionUtil_BLService {
    private Promotion_DataService promotion_dataService = RemoteHelper.getInstance().getPromotionDataService();


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
        if (promotion_dataService.getPromotion(Integer.parseInt(promotionID)) == null)
            //若不存在该营销策略
            return null;
        PromotionPO promotionPO = promotion_dataService.getPromotion(Integer.parseInt(promotionID));
        String[] targetHotel = promotionPO.getTargetHotel().split(";");
        return new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                promotionPO.getTargetUser(), promotionPO.getTargetArea(), targetHotel, promotionPO.
                getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                "" + promotionPO.getPromotionID(),promotionPO.getPromotionType(), promotionPO.getCompanyName());
    }

    /**
     * 得到该酒店当天的所有促销策略,时间参数传入搜索当天的时间
     *
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp timestamp) throws RemoteException, ClassNotFoundException, IOException {
        List<PromotionPO> promotionPOList = promotion_dataService.getPromotionByHotelID(hotelID);
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        if (promotionPOList == null || promotionPOList.isEmpty())
            return new ArrayList<PromotionVO>();
        for (PromotionPO promotionPO : promotionPOList) {
            String[] targetHotel = promotionPO.getTargetHotel().split(";");
            if (timestamp.after(promotionPO.getStartTime()) && timestamp.before(promotionPO.getEndTime()))
                promotionVOList.add(new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                        promotionPO.getTargetUser(), promotionPO.getTargetArea(), targetHotel, promotionPO.
                        getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                        "" + promotionPO.getPromotionID(), promotionPO.getPromotionType(), promotionPO.getCompanyName()));
        }
        return promotionVOList;
    }

    /**
     * 得到当天所有的网站促销策略
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionVO> getAllWebPromotions(Timestamp timestamp) throws IOException, ClassNotFoundException {
        List<PromotionPO> promotionPOList = promotion_dataService.getAllWebPromotions();
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        if (promotionPOList == null || promotionPOList.isEmpty())
            return new ArrayList<PromotionVO>();
        for (PromotionPO promotionPO : promotionPOList) {
            if (timestamp.after(promotionPO.getStartTime()) && timestamp.before(promotionPO.getEndTime()))
                promotionVOList.add(new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                        promotionPO.getTargetUser(), promotionPO.getTargetArea(), promotionPO.getTargetHotel().split(";"), promotionPO.
                        getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                        "" + promotionPO.getPromotionID(), promotionPO.getPromotionType(), promotionPO.getCompanyName()));
        }
        return promotionVOList;
    }
}
