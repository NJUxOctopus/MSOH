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

    public ResultMessage addPromotion(PromotionVO promotionVO) throws RemoteException {
        if(promotionVO.endTime==null||promotionVO.promotionName.equals("")||promotionVO.startTime==null||
                promotionVO.targetUser==null||promotionVO.targetHotel==null||promotionVO.targetArea.equals("")||promotionVO.promotionID.equals("")){
            return ResultMessage.Blank;
        }else if(promotionVO.discount<=0||promotionVO.discount>=10){
            return ResultMessage.DataFormatWrong;
        }else {
            if(promotion_dataService_stub.getPromotion(promotionVO.promotionID)!=null)
                return ResultMessage.PromotionExist;
            promotion_dataService_stub.addPromotion(new PromotionPO(promotionVO.framerName,
                    promotionVO.frameDate,promotionVO.promotionName,promotionVO.targetUser,promotionVO.targetArea,
                    promotionVO.targetHotel,promotionVO.startTime,promotionVO.endTime,promotionVO.discount,
                    promotionVO.minRoom,promotionVO.promotionID));
            return ResultMessage.Promotion_AddPromotionSuccess;
        }
    }

    public ResultMessage modifyPromotion(PromotionVO promotionVO) throws RemoteException {
        if(promotionVO.frameDate==null||promotionVO.endTime==null||promotionVO.promotionName.equals("")||promotionVO.startTime==null||
                promotionVO.targetUser==null||promotionVO.targetHotel==null||promotionVO.targetArea.equals("")){
            return ResultMessage.Blank;
        }else if(promotionVO.discount<=0||promotionVO.discount>=10){
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

    public ResultMessage deletePromotion(String promotionID) throws RemoteException {
        if(promotion_dataService_stub.getPromotion(promotionID)==null)
            return ResultMessage.PromotionNotExist;
        promotion_dataService_stub.deletePromotion(promotion_dataService_stub.getPromotion(promotionID));
        return ResultMessage.Promotion_DeletePromotionSuccess;
    }
}
