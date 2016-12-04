package businesslogic.promotion_bl;

import businesslogicservice.promotion_blservice.Promotion_BLService;
import dataservice.promotion_dataservice.Promotion_DataService_Stub;
import po.PromotionPO;
import rmi.RemoteHelper;
import util.MemberType;
import util.ResultMessage;
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
    public ResultMessage addPromotion(PromotionVO promotionVO) throws RemoteException {
        if (promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null || promotionVO.targetHotel == null || promotionVO.targetArea.equals("") ||
                promotionVO.promotionID.equals("")) {//若结束时间，策略名称，开始时间，目标用户，目标酒店，目标区域，策略ID为空
            return ResultMessage.Blank;
        } else if (promotionVO.discount <= 0 || promotionVO.discount >= 10) {//若折扣小于0或大于10
            return ResultMessage.DataFormatWrong;
        } else {
            if (promotion_dataService_stub.getPromotion(Integer.parseInt(promotionVO.promotionID)) != null)
                //若已经存在该ID的营销策略
                return ResultMessage.PromotionExist;
            String targetHotel = "";
            for (int i = 0; i < promotionVO.targetHotel.length; i++) {
                if (i != promotionVO.targetHotel.length - 1)
                    targetHotel += promotionVO.targetHotel[i] + ";";
                else
                    targetHotel += promotionVO.targetHotel[i];
            }
            if (promotion_dataService_stub.addPromotion(new PromotionPO(promotionVO.framerName,
                    promotionVO.frameDate, promotionVO.promotionName, promotionVO.targetUser, promotionVO.targetArea,
                    targetHotel, promotionVO.startTime, promotionVO.endTime, promotionVO.discount,
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
    public ResultMessage modifyPromotion(PromotionVO promotionVO) throws RemoteException {
        if (promotionVO.frameDate == null || promotionVO.endTime == null || promotionVO.promotionName.equals("") || promotionVO.startTime == null ||
                promotionVO.targetUser == null || promotionVO.targetHotel == null || promotionVO.targetArea.equals("")) {
            //若结束时间，策略名称，开始时间，目标用户，目标酒店，目标区域为空
            return ResultMessage.Blank;
        } else if (promotionVO.discount <= 0 || promotionVO.discount >= 10) {//若折扣小于0或大于10
            return ResultMessage.DataFormatWrong;
        } else {
            PromotionPO promotionPO = promotion_dataService_stub.getPromotion(Integer.parseInt(promotionVO.promotionID));
            if (promotionPO == null)
                return ResultMessage.PromotionNotExist;
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
     * @param hotelID
     * @param memberType
     * @param area
     * @param roomNum
     * @return
     * @throws RemoteException
     */
    public List<PromotionVO> promotionRequirements(String hotelID, MemberType memberType, String area, int roomNum) throws RemoteException, IOException, ClassNotFoundException {
        List<PromotionPO> promotionPOList = promotion_dataService_stub.getPromotionByHotelID(hotelID);
        if (promotionPOList == null || promotionPOList.isEmpty())
            return null;
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        for (int i = 0; i < promotionPOList.size(); i++) {
            PromotionPO promotionPO = promotionPOList.get(i);
            String[] areaArray = promotionPO.getTargetArea().split(";");

            boolean areaMeetReq = false;//用来判断商圈是否符合
            //若所有商圈都满足
            if (areaArray.length == 1 && areaArray[0].equals("All"))
                areaMeetReq = true;
            else {
                for (int j = 0; j < areaArray.length; j++) {
                    if (area.equals(areaArray[j])) {
                        areaMeetReq = true;
                        break;
                    }
                }
            }
            boolean memberTypeMeetReq = false;//用来判断会员类型是否符合
            if (promotionPO.getTargetUser().equals(MemberType.NONMEMBER)) {
                //若要求为非会员，所有类型都满足
                memberTypeMeetReq = true;
            } else if (promotionPO.getTargetUser().equals(memberType)) {
                memberTypeMeetReq = true;
            }

            if (roomNum >= promotionPO.getMinRoom() && areaMeetReq && memberTypeMeetReq)
                promotionVOList.add(new PromotionVO(promotionPO.getDiscount(),promotionPO.getPromotionName()));
        }
        return promotionVOList;
    }
}
