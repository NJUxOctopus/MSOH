package businesslogicservice.promotion_blservice;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import util.MemberType;
import util.ResultMessage;
import vo.PromotionVO;

/**
 * 
 * @author Pxr create:10.16 latest modify:10.16
 *
 */
public class Promotion_BLService_Stub implements Promotion_BLService {

	PromotionVO promotionVO;

	public ResultMessage addPromotion(PromotionVO promotionVO) throws RemoteException {
		return null;
	}

	public ResultMessage modifyPromotion(PromotionVO promotionVO) throws RemoteException {
		return null;
	}

	public ResultMessage deletePromotion(String promotionID) throws RemoteException {
		return null;
	}
}
