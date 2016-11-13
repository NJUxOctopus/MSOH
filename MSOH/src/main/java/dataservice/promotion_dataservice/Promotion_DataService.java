package dataservice.promotion_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import po.HotelPO;
import po.PromotionPO;
import util.MemberType;
import util.ResultMessage;
/**
 * 
 * @author zqh
 *
 */
public interface Promotion_DataService extends Remote{
	public ResultMessage add (PromotionPO po) throws RemoteException;
	
	public String getTargetAera (PromotionPO po) throws RemoteException;

	public List<String> getTargetHotel (PromotionPO po) throws RemoteException;
	
	public MemberType getTargetUser (PromotionPO po) throws RemoteException;
	
	public Date getStartTime (PromotionPO po) throws RemoteException;
	
	public Date getEndTime (PromotionPO po) throws RemoteException;
	
	public double getDiscount (PromotionPO po) throws RemoteException;
	
	public int getMinRoom (PromotionPO po) throws RemoteException;

}
