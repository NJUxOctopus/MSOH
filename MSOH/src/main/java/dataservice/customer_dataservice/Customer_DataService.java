package dataservice.customer_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.CustomerPO;
import po.HotelPO;
import util.ResultMessage;
/**
 * 
 * @author zqh
 *
 */
public interface Customer_DataService extends Remote{
	
	public ResultMessage add(CustomerPO customerPO) throws RemoteException;
	
	public ResultMessage modify(CustomerPO customerPO) throws RemoteException;
	
	public List<CustomerPO> find(String id) throws RemoteException;
	
	public String getID (CustomerPO customerPO) throws RemoteException;
	
	public int getCurrentCredit (String customer_id) throws RemoteException;
	
	public List<HotelPO> getReservedHotel(CustomerPO customerPO) throws RemoteException;
}
