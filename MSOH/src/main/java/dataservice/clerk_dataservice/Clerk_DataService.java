package dataservice.clerk_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.ClerkPO;
import util.ResultMessage;
/**
 * 
 * @author zqh
 *
 */
public interface Clerk_DataService extends Remote{
	public ResultMessage add(ClerkPO clerkPO) throws RemoteException;
	
	public ResultMessage modify (ClerkPO clerkPO) throws RemoteException;
	
	public List<ClerkPO> findByClerkName (String name) throws RemoteException;
	
	public List<ClerkPO> findByClerkID (String id) throws RemoteException;
	
	public ResultMessage delete(ClerkPO clerkPO) throws RemoteException;
}
