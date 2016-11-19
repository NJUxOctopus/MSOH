package dataservice.manager_dataservice;

import po.ManagerPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author zqh
 */
public interface Manager_DataService extends Remote {
    // 更新网站管理人员信息
    public boolean modifyManager(ManagerPO po) throws RemoteException;
    // 根据ID查找网站管理人员
    public ManagerPO findManager(String ID) throws RemoteException;

}
