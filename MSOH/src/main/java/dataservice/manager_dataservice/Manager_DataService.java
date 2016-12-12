package dataservice.manager_dataservice;

import po.ManagerPO;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author zqh
 */
public interface Manager_DataService extends Remote {
    // 更新网站管理人员信息
    public boolean modifyManager(ManagerPO managerPO) throws RemoteException;

    // 根据ID查找网站管理人员
    public ManagerPO findManagerByID(String ID) throws RemoteException;

    // 获得所有网站管理人员
    public List<ManagerPO> findAllManagers() throws IOException, ClassNotFoundException;

    // 根据姓名查找网站管理人员
    public List<ManagerPO> findManagerByName(String name) throws IOException, ClassNotFoundException;

}
