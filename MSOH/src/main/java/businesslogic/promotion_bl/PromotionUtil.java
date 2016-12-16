package businesslogic.promotion_bl;

import businesslogicservice.promotion_blservice.PromotionUtil_BLService;
import dataservice.promotion_dataservice.Promotion_DataService;
import po.PromotionPO;
import rmi.RemoteHelper;
import util.PromotionType;
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
                "" + promotionPO.getPromotionID(), promotionPO.getPromotionType(), promotionPO.getCompanyName());
    }

    /**
     * 得到该酒店当天的所有促销策略,时间参数传入搜索当天的时间
     *
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> getPromotionByHotelID(String hotelID, Timestamp timestamp) throws ClassNotFoundException, IOException {
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

    /**
     * 根据促销策略类型和酒店ID获得促销策略
     *
     * @param promotionType
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> getPromotionByTypeAndHotelID(PromotionType promotionType, String hotelID, Timestamp timestamp) throws IOException, ClassNotFoundException {
        List<PromotionVO> promotionVOList = getPromotionByHotelID(hotelID, timestamp);
        List<PromotionVO> list = new ArrayList<PromotionVO>();
        for (PromotionVO promotionVO : promotionVOList) {
            if (promotionVO.promotionType.equals(promotionType))
                list.add(promotionVO);
        }
        return list;
    }

    /**
     * 得到某一个酒店一段时间内的促销策略
     *
     * @param hotelID
     * @param timestamp1
     * @param timestamp2
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionVO> getHotelPromotionBetweenTwoDate(String hotelID, Timestamp timestamp1, Timestamp timestamp2) throws IOException, ClassNotFoundException {
        List<PromotionVO> promotionVOList = getPromotionByHotelID(hotelID, timestamp1);
        long oneDay = 1000 * 60 * 60 * 24;
        long days = (timestamp2.getTime() - timestamp1.getTime()) / oneDay;//算共住多少天
        List<Timestamp> list = new ArrayList<Timestamp>();
        for (int i = 1; i < days - 1; i++) {//除了最后一天，获得所有的促销策略，不重复
            list.add(new Timestamp(timestamp1.getTime() + i * oneDay));
        }
        for (Timestamp temp : list) {
            List<PromotionVO> tempList = getPromotionByHotelID(hotelID, temp);
            promotionVOList = mergePromotionList(promotionVOList, tempList);
        }
        return promotionVOList;
    }

    /**
     * 合并两个促销策略列表
     *
     * @param list1
     * @param list2
     * @return
     */
    public List<PromotionVO> mergePromotionList(List<PromotionVO> list1, List<PromotionVO> list2) throws RemoteException {
        List<String> idList1 = new ArrayList<String>();
        for (PromotionVO promotionVO : list1) {
            idList1.add(promotionVO.promotionID);
        }
        List<String> idList2 = new ArrayList<String>();
        for (PromotionVO promotionVO : list2) {
            idList2.add(promotionVO.promotionID);
        }
        idList1.removeAll(idList2);
        idList1.addAll(idList2);
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        for (String id : idList1) {
            promotionVOList.add(getSingle(id));
        }
        return promotionVOList;
    }

    /**
     * 根据策略种类得到策略列表
     *
     * @param promotionType
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<PromotionVO> getPromotionByType(PromotionType promotionType, Timestamp timestamp) throws IOException, ClassNotFoundException {
        List<PromotionPO> promotionPOList = promotion_dataService.getPromotionByPromotionType(promotionType);
        if (promotionPOList == null || promotionPOList.isEmpty())
            return new ArrayList<PromotionVO>();
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        for (PromotionPO promotionPO : promotionPOList) {
            if (timestamp.before(promotionPO.getEndTime()) && timestamp.after(promotionPO.getStartTime()))
                promotionVOList.add(new PromotionVO(promotionPO.getFramerName(), promotionPO.getFrameDate(), promotionPO.getPromotionName(),
                        promotionPO.getTargetUser(), promotionPO.getTargetArea(), promotionPO.getTargetHotel().split(";"), promotionPO.
                        getStartTime(), promotionPO.getEndTime(), promotionPO.getDiscount(), promotionPO.getMinRoom(),
                        "" + promotionPO.getPromotionID(), promotionPO.getPromotionType(), promotionPO.getCompanyName()));
        }
        return promotionVOList;
    }
}


