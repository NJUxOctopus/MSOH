package dataservice.promotion_dataservice;

import po.PromotionPO;
import util.PromotionType;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author zqh
 *
 */
public interface Promotion_DataService extends Remote{
	// 新增促销策略
	public boolean addPromotion(PromotionPO po) throws RemoteException;

	// 根据ID查找促销策略
	public PromotionPO getPromotion(int promotionID) throws RemoteException;

	// 得到所有促销策略
	public List<PromotionPO> getAllWebPromotions() throws IOException, ClassNotFoundException;

	// 获得适用于某酒店的营销策略
	public List<PromotionPO> getPromotionByHotelID(String hotelID) throws IOException,ClassNotFoundException;

	// 删除促销策略
	public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException;

	// 更新促销策略
	public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException;

	// 根据促销策略类型获得促销策略
	public List<PromotionPO> getPromotionByPromotionType(PromotionType promotionType) throws IOException,ClassNotFoundException;
}
