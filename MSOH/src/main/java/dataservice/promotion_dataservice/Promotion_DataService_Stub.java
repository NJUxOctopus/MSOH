package dataservice.promotion_dataservice;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import po.HotelPO;
import po.PromotionPO;
import util.MemberType;
import util.ResultMessage;
/**
 * 
 * @author Ǯ����
 *
 */
public class Promotion_DataService_Stub implements Promotion_DataService{

	public boolean addPromotion(PromotionPO po) throws RemoteException {
		return false;
	}

	public PromotionPO getPromotion(String promotionID) throws RemoteException {
		return null;
	}

	public List<PromotionPO> getAllPromotions() throws RemoteException {
		return null;
	}

	public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException {
		return false;
	}

	public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException {
		return false;
	}
}
