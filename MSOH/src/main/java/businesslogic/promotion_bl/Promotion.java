package businesslogic.promotion_bl;

import businesslogic.customer_bl.CustomerUtil;
import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.promotion_blservice.Promotion_BLService;
import dataservice.promotion_dataservice.Promotion_DataService_Stub;
import po.PromotionPO;
import rmi.RemoteHelper;
import util.MemberType;
import util.PromotionType;
import util.ResultMessage;
import util.strategy.BirthdayPromotion;
import util.strategy.NormalPromotion;
import util.strategy.Strategy;
import vo.HotelVO;
import vo.OrderVO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Promotion implements Promotion_BLService {

    Promotion_DataService_Stub promotion_dataService_stub = new Promotion_DataService_Stub();

    /**
     * 增加营销策略
     *
     * @param promotionVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        HotelUtil hotelUtil = new HotelUtil();
        if (promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null || promotionVO.promotionID.equals("")) {//若结束时间，策略名称，开始时间，目标用户，策略ID为空
            return ResultMessage.Blank;
        } else if (promotionVO.discount <= 0 || promotionVO.discount >= 10) {//若折扣小于0或大于10
            return ResultMessage.DataFormatWrong;
        } else {
            List<HotelVO> hotelVOList = hotelUtil.getHotelByArea(promotionVO.targetArea);
            String targetHotel = "";
            if (promotionVO.promotionType.equals(PromotionType.WebPromotion)) {
                for (int i = 0; i < hotelVOList.size(); i++) {
                    if (i != hotelVOList.size() - 1)
                        targetHotel += hotelVOList.get(i).hotelID + ";";
                    else
                        targetHotel += hotelVOList.get(i).hotelID;
                }
                if (promotion_dataService_stub.addPromotion(new PromotionPO(promotionVO.framerName,
                        promotionVO.frameDate, promotionVO.promotionName, promotionVO.targetUser, promotionVO.targetArea,
                        targetHotel, promotionVO.startTime, promotionVO.endTime, promotionVO.discount,
                        promotionVO.minRoom, Integer.parseInt(promotionVO.promotionID), promotionVO.promotionType)))
                    return ResultMessage.Promotion_AddPromotionSuccess;
            }

            if (promotion_dataService_stub.addPromotion(new PromotionPO(promotionVO.framerName,
                    promotionVO.frameDate, promotionVO.promotionName, promotionVO.targetUser, null,
                    promotionVO.targetHotel[0], promotionVO.startTime, promotionVO.endTime, promotionVO.discount,
                    promotionVO.minRoom, Integer.parseInt(promotionVO.promotionID), promotionVO.promotionType)))
                return ResultMessage.Promotion_AddPromotionSuccess;
            else
                return ResultMessage.Fail;
        }
    }

    /**
     * 修改营销策略
     *
     * @param promotionVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyPromotion(PromotionVO promotionVO) throws IOException, ClassNotFoundException {
        HotelUtil hotelUtil = new HotelUtil();
        if (promotionVO.frameDate == null || promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null) {
            //若结束时间，策略名称，开始时间，目标用户为空
            return ResultMessage.Blank;
        } else if (promotionVO.discount <= 0 || promotionVO.discount >= 10) {//若折扣小于0或大于10
            return ResultMessage.DataFormatWrong;
        } else {
            PromotionPO promotionPO = promotion_dataService_stub.getPromotion(Integer.parseInt(promotionVO.promotionID));
            if (promotionPO == null)
                return ResultMessage.PromotionNotExist;
            List<HotelVO> hotelVOList = hotelUtil.getHotelByArea(promotionVO.targetArea);
            String targetHotel = "";
            if (promotionVO.promotionType.equals(PromotionType.WebPromotion) && !promotionVO.targetArea.equals(promotionPO.getTargetArea())) {
                for (int i = 0; i < hotelVOList.size(); i++) {
                    if (i != hotelVOList.size() - 1)
                        targetHotel += hotelVOList.get(i).hotelID + ";";
                    else
                        targetHotel += hotelVOList.get(i).hotelID;
                }
                promotionPO.setTargetHotel(targetHotel);
            }
            promotionPO.setDiscount(promotionVO.discount);
            promotionPO.setEndTime(promotionVO.endTime);
            promotionPO.setFrameDate(promotionVO.frameDate);
            promotionPO.setMinRoom(promotionVO.minRoom);
            promotionPO.setPromotionName(promotionVO.promotionName);
            promotionPO.setStartTime(promotionVO.startTime);
            promotionPO.setTargetArea(promotionVO.targetArea);
            promotionPO.setTargetUser(promotionVO.targetUser);
            if (promotion_dataService_stub.modifyPromotion(promotionPO))
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
        if (promotion_dataService_stub.getPromotion(Integer.parseInt(promotionID)) == null)
            //若不存在该营销策略
            return ResultMessage.PromotionNotExist;
        if (promotion_dataService_stub.deletePromotion(promotion_dataService_stub.getPromotion(Integer.parseInt(promotionID))))
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
        //得到该酒店所有的促销策略
        List<PromotionPO> promotionPOList = promotion_dataService_stub.getPromotionByHotelID(orderVO.hotelID);
        HotelUtil hotelUtil = new HotelUtil();
        CustomerUtil customerUtil = new CustomerUtil();
        if (promotionPOList == null || promotionPOList.isEmpty())
            return null;
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        for (PromotionPO promotionPO : promotionPOList) {
            if (promotionPO.getPromotionName().equals("生日特惠")) {
                Strategy birthdayPromotion = new BirthdayPromotion();
                if (birthdayPromotion.usePromotion(orderVO)) {
                    promotionVOList.add(new PromotionVO(promotionPO.getDiscount(), promotionPO.getPromotionName()));
                }
            }
            Strategy normalPromotion = new NormalPromotion(customerUtil.getSingle(orderVO.customerID).memberType, orderVO.rooms.length);
            if (normalPromotion.usePromotion(orderVO))
                promotionVOList.add(new PromotionVO(promotionPO.getDiscount(), promotionPO.getPromotionName()));

        }
        return promotionVOList;
    }


}
