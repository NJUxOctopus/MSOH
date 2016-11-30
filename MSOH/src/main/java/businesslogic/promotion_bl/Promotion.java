package businesslogic.promotion_bl;

import businesslogicservice.promotion_blservice.Promotion_BLService;
import dataservice.promotion_dataservice.Promotion_DataService_Stub;
import po.PromotionPO;
import rmi.RemoteHelper;
import util.ResultMessage;
import vo.PromotionVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class Promotion implements Promotion_BLService {

    Promotion_DataService_Stub promotion_dataService_stub = new Promotion_DataService_Stub();

    /**
     * 增加营销策略
     * @param promotionVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addPromotion(PromotionVO promotionVO) throws RemoteException {
        if(promotionVO.endTime==null||promotionVO.promotionName.equals("")||promotionVO.startTime==null||
                promotionVO.targetUser==null||promotionVO.targetHotel==null||promotionVO.targetArea.equals("")||
                promotionVO.promotionID.equals("")){//若结束时间，策略名称，开始时间，目标用户，目标酒店，目标区域，策略ID为空
            return ResultMessage.Blank;
        }else if(promotionVO.discount<=0||promotionVO.discount>=10){//若折扣小于0或大于10
            return ResultMessage.DataFormatWrong;
        }else {
            if(promotion_dataService_stub.getPromotion(promotionVO.promotionID)!=null)
                //若已经存在该ID的营销策略
                return ResultMessage.PromotionExist;
            promotion_dataService_stub.addPromotion(new PromotionPO(promotionVO.framerName,
                    promotionVO.frameDate,promotionVO.promotionName,promotionVO.targetUser,promotionVO.targetArea,
                    promotionVO.targetHotel,promotionVO.startTime,promotionVO.endTime,promotionVO.discount,
                    promotionVO.minRoom,promotionVO.promotionID));
            return ResultMessage.Promotion_AddPromotionSuccess;
        }
    }

    /**
     * 修改营销策略
     * @param promotionVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyPromotion(PromotionVO promotionVO) throws RemoteException {
        if(promotionVO.frameDate==null||promotionVO.endTime==null||promotionVO.promotionName.equals("")||promotionVO.startTime==null||
                promotionVO.targetUser==null||promotionVO.targetHotel==null||promotionVO.targetArea.equals("")){
            //若结束时间，策略名称，开始时间，目标用户，目标酒店，目标区域为空
            return ResultMessage.Blank;
        }else if(promotionVO.discount<=0||promotionVO.discount>=10){//若折扣小于0或大于10
            return ResultMessage.DataFormatWrong;
        }else {
            PromotionPO promotionPO = promotion_dataService_stub.getPromotion(promotionVO.promotionID);
            if(promotionPO==null)
                return ResultMessage.PromotionNotExist;
            promotionPO.setDiscount(promotionVO.discount);
            promotionPO.setEndTime(promotionVO.endTime);
            promotionPO.setFrameDate(promotionVO.frameDate);
            promotionPO.setMinRoom(promotionVO.minRoom);
            promotionPO.setPromotionName(promotionVO.promotionName);
            promotionPO.setStartTime(promotionVO.startTime);
            promotionPO.setTargetArea(promotionVO.targetArea);
            promotionPO.setTargetUser(promotionVO.targetUser);
            promotion_dataService_stub.modifyPromotion(promotionPO);
            return ResultMessage.Promotion_ModifyPromotionSuccess;
        }
    }

    /**
     * 删除营销策略
     * @param promotionID
     * @return
     * @throws RemoteException
     */
    public ResultMessage deletePromotion(String promotionID) throws RemoteException {
        if(promotion_dataService_stub.getPromotion(promotionID)==null)
            //若不存在该营销策略
            return ResultMessage.PromotionNotExist;
        promotion_dataService_stub.deletePromotion(promotion_dataService_stub.getPromotion(promotionID));
        return ResultMessage.Promotion_DeletePromotionSuccess;
    }
}
