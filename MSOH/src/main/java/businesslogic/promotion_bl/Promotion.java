package businesslogic.promotion_bl;

import businesslogic.bl_Factory.Abstract_BLFactory;
import businesslogic.bl_Factory.Default_BLFactory;
import businesslogic.customer_bl.CustomerUtil;
import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.promotion_blservice.Promotion_BLService;
import dataservice.promotion_dataservice.Promotion_DataService;
import po.PromotionPO;
import rmi.RemoteHelper;
import util.PromotionType;
import util.ResultMessage;
import util.strategy.BirthdayPromotion;
import util.strategy.CompanyPromotion;
import util.strategy.NormalPromotion;
import util.strategy.Strategy;
import vo.HotelVO;
import vo.OrderVO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Promotion implements Promotion_BLService {

    private Promotion_DataService promotion_dataService = RemoteHelper.getInstance().getPromotionDataService();
    private Abstract_BLFactory abstract_blFactory = new Default_BLFactory();
    private HotelUtil hotelUtil = abstract_blFactory.createHotelUtil();
    private PromotionUtil promotionUtil = abstract_blFactory.createPromotionUtil();
    private CustomerUtil customerUtil = abstract_blFactory.createCustomerUtil();

    /**
     * 增加网站营销策略，只需要填写目标商圈，自动获得商圈内所有酒店，用分号隔开
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage addWebPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        if (promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null) {//若结束时间，策略名称，开始时间，目标用户为空
            return ResultMessage.Blank;
        } else {
            List<HotelVO> hotelVOList = hotelUtil.getByArea(promotionVO.targetArea);
            String targetHotel = "";
            for (int i = 0; i < hotelVOList.size(); i++) {
                if (i != hotelVOList.size() - 1)
                    targetHotel += hotelVOList.get(i).hotelID + ";";
                else
                    targetHotel += hotelVOList.get(i).hotelID;
            }
            if (promotion_dataService.addPromotion(new PromotionPO(promotionVO.framerName,
                    promotionVO.frameDate, promotionVO.promotionName, promotionVO.targetUser, promotionVO.targetArea,
                    targetHotel, promotionVO.startTime, promotionVO.endTime, promotionVO.discount / 10,
                    promotionVO.minRoom, null, promotionVO.promotionType)))
                return ResultMessage.Promotion_AddPromotionSuccess;
            else
                return ResultMessage.Fail;
        }
    }

    /**
     * 增加酒店促销策略，这里就没有商圈的概念，目标酒店就是酒店本身
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage addHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        if (promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null) {//若结束时间，策略名称，开始时间，目标用户为空
            return ResultMessage.Blank;
        } else {
            String targetArea = hotelUtil.getByID(promotionVO.targetHotel[0]).area;
            if (promotion_dataService.addPromotion(new PromotionPO(promotionVO.framerName,
                    promotionVO.frameDate, promotionVO.promotionName, promotionVO.targetUser, targetArea,
                    promotionVO.targetHotel[0], promotionVO.startTime, promotionVO.endTime, promotionVO.discount / 10,
                    promotionVO.minRoom, promotionVO.companyName, promotionVO.promotionType)))
                return ResultMessage.Promotion_AddPromotionSuccess;
            else
                return ResultMessage.Fail;
        }
    }

    /**
     * 修改网站营销策略
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage modifyWebPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        if (promotionVO.frameDate == null || promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null) {
            //若结束时间，策略名称，开始时间，目标用户为空
            return ResultMessage.Blank;
        } else {
            PromotionPO promotionPO = promotion_dataService.getPromotion(Integer.parseInt(promotionVO.promotionID));
            if (promotionPO == null)
                return ResultMessage.PromotionNotExist;
            List<HotelVO> hotelVOList = hotelUtil.getByArea(promotionVO.targetArea);
            String targetHotel = "";
            if (!promotionVO.targetArea.equals(promotionPO.getTargetArea())) {
                for (int i = 0; i < hotelVOList.size(); i++) {
                    if (i != hotelVOList.size() - 1)
                        targetHotel += hotelVOList.get(i).hotelID + ";";
                    else
                        targetHotel += hotelVOList.get(i).hotelID;
                }
                promotionPO.setTargetHotel(targetHotel);
            }
            promotionPO.setDiscount(promotionVO.discount / 10);
            promotionPO.setEndTime(promotionVO.endTime);
            promotionPO.setMinRoom(promotionVO.minRoom);
            promotionPO.setPromotionName(promotionVO.promotionName);
            promotionPO.setStartTime(promotionVO.startTime);
            promotionPO.setTargetArea(promotionVO.targetArea);
            promotionPO.setTargetUser(promotionVO.targetUser);
            if (promotion_dataService.modifyPromotion(promotionPO))
                return ResultMessage.Promotion_ModifyPromotionSuccess;
            else
                return ResultMessage.Fail;
        }
    }

    /**
     * 修改酒店促销策略
     *
     * @param promotionVO
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ResultMessage modifyHotelPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        if (promotionVO.frameDate == null || promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null) {
            //若结束时间，策略名称，开始时间，目标用户为空
            return ResultMessage.Blank;
        } else if (promotionVO.discount <= 0 || promotionVO.discount >= 10) {//若折扣小于0或大于10
            return ResultMessage.DataFormatWrong;
        } else {
            PromotionPO promotionPO = promotion_dataService.getPromotion(Integer.parseInt(promotionVO.promotionID));
            if (promotionPO == null)
                return ResultMessage.PromotionNotExist;
            promotionPO.setDiscount(promotionVO.discount);
            promotionPO.setEndTime(promotionVO.endTime);
            promotionPO.setMinRoom(promotionVO.minRoom);
            promotionPO.setPromotionName(promotionVO.promotionName);
            promotionPO.setStartTime(promotionVO.startTime);
            promotionPO.setTargetUser(promotionVO.targetUser);
            if (promotion_dataService.modifyPromotion(promotionPO))
                return ResultMessage.Promotion_ModifyPromotionSuccess;
            else
                return ResultMessage.Fail;
        }
    }

    /**
     * 删除营销策略
     *
     * @param promotionID
     * @return
     * @throws RemoteException
     */
    public ResultMessage deletePromotion(String promotionID) throws RemoteException {
        if (promotion_dataService.getPromotion(Integer.parseInt(promotionID)) == null)
            //若不存在该营销策略
            return ResultMessage.PromotionNotExist;
        if (promotion_dataService.deletePromotion(promotion_dataService.getPromotion(Integer.parseInt(promotionID))))
            return ResultMessage.Promotion_DeletePromotionSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 判断是否符合策略要求，返回满足要求的promotion
     *
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> promotionRequirements(OrderVO orderVO) throws IOException, ClassNotFoundException {
        //得到该酒店所有的促销策略在入住期间的
        List<PromotionVO> hotelPromotion = promotionUtil.getHotelPromotionBetweenTwoDate(orderVO.hotelID, orderVO.estimatedCheckinTime, orderVO.estimatedCheckoutTime);
        if (hotelPromotion == null || hotelPromotion.isEmpty())
            return null;
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        for (PromotionVO promotionVO : hotelPromotion) {
            if (promotionVO.promotionType.equals(PromotionType.HotelPromotion_Birthday)) {
                Strategy birthdayPromotion = new BirthdayPromotion();
                if (birthdayPromotion.usePromotion(orderVO))
                    promotionVOList.add(new PromotionVO(promotionVO.discount, promotionVO.promotionName));
            } else if (promotionVO.promotionType.equals(PromotionType.HotelPromotion_Company)) {
                Strategy companyPromotion = new CompanyPromotion(promotionVO.companyName);
                if (companyPromotion.usePromotion(orderVO))
                    promotionVOList.add(new PromotionVO(promotionVO.discount, promotionVO.promotionName));
            } else if (promotionVO.promotionType.equals(PromotionType.HotelPromotion_Holiday)) {
                //酒店的节日特惠是入住期间有优惠,在获取时直接就根据入住和离开时间获取了，所以直接加入优惠的list
                promotionVOList.add(promotionVO);
            } else if (promotionVO.promotionType.equals(PromotionType.HotelPromotion_Reserve) || promotionVO.promotionType.equals(PromotionType.HotelPromotion_Other)) {
                Strategy normalPromotion = new NormalPromotion(customerUtil.getSingle(orderVO.customerID).memberType, orderVO.rooms.length);
                if (normalPromotion.usePromotion(orderVO))
                    promotionVOList.add(new PromotionVO(promotionVO.discount, promotionVO.promotionName));
            }
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Timestamp timestamp = Timestamp.valueOf(sdf.format(date));
        List<PromotionVO> webPromotion = promotionUtil.getAllWebPromotions(timestamp);
        //网站的节日特惠是根据预定的那一天判断的
        for (PromotionVO promotionVO : webPromotion) {
            if (promotionVO.promotionType.equals(PromotionType.WebPromotion_Holiday))
                promotionVOList.add(promotionVO);
        }
        return promotionVOList;
    }


}
