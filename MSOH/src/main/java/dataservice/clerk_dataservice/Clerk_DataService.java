
package dataservice.clerk_dataservice;

import po.ClerkPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author zqh
 *
 */
public interface Clerk_DataService extends Remote {
	// 在数据库中增加酒店工作人员信息
	public boolean addClerk(ClerkPO clerkPO) throws RemoteException;

	// 更改在数据中的酒店工作人员信息
	public boolean modifyClerk (ClerkPO clerkPO) throws RemoteException;

	// 根据姓名查找酒店工作人员
	public List<ClerkPO> findClerkByName(String name) throws RemoteException;

	// 根据ID查找酒店工作人员
	public ClerkPO findClerkByID(String id) throws RemoteException;

	// 查找全部酒店工作人员
	public List<ClerkPO> findAllClerks() throws RemoteException;

	// 删除酒店工作人员
	public boolean deleteClerk(ClerkPO clerkPO) throws RemoteException;

	// 根据酒店ID搜索酒店工作人员
	public ClerkPO findClerkByHotelID(String hotelID) throws RemoteException;
}

