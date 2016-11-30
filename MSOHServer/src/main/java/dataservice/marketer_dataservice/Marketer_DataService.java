package dataservice.marketer_dataservice;


import po.MarketerPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author zqh
 */
public interface Marketer_DataService extends Remote {
    // 新增营销人员
    public void addMarketer(MarketerPO marketerPO) throws RemoteException;
    // 更改营销人员信息
    public void modifyMarketer(MarketerPO marketerPO) throws RemoteException;
    // 根据姓名查找营销人员信息
    public List<MarketerPO> findMarketerByName(String name) throws RemoteException;
    // 根据ID查找营销人员信息
    public MarketerPO findMarketerByID(String id) throws RemoteException;
    // 得到全部营销人员的信息
    public List<MarketerPO> findAllMarketers() throws RemoteException;
    // 删除营销人员的信息
    public void deleteMarketer(MarketerPO marketerPO) throws RemoteException;

}
