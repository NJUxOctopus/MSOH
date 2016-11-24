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
                promotionVO.targetUser==null||promotionVO.targetHotel==null||promotionVO.targetArea==null){
            return ResultMessage.Blank;
        }else if(promotionVO.discount<=0||promotionVO.discount>=10){
            //这个返回什么呢
            return null;
        }else {
            promotion_dataService_stub.addPromotion(new PromotionPO(promotionVO.framerName,
                    promotionVO.frameDate,promotionVO.promotionName,promotionVO.targetUser,promotionVO.targetArea,
                    promotionVO.targetHotel,promotionVO.startTime,promotionVO.endTime,promotionVO.discount,
                    promotionVO.minRoom,promotionVO.promotionID));
            return ResultMessage.Promotion_AddPromotionSuccess;
        }
    }

    public ResultMessage modifyPromotion(PromotionVO promotionVO) throws RemoteException {
        if(promotionVO.endTime==null||promotionVO.promotionName.equals("")||promotionVO.startTime==null||
                promotionVO.targetUser==null||promotionVO.targetHotel==null||promotionVO.targetArea==null){
            return ResultMessage.Blank;
        }else if(promotionVO.discount<=0||promotionVO.discount>=10){
            //这个返回什么呢
            return null;
        }else {

            return ResultMessage.Promotion_ModifyPromotionSuccess;
        }
    }

    public ResultMessage deletePromotion(String promotionID) throws RemoteException {
        return null;
    }
}
