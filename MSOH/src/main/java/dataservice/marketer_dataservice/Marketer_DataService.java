package dataservice.marketer_dataservice;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.MarketerPO;
import util.ResultMessage;
import util.WorkerPosition;

/**
 * @author zqh
 */
public interface Marketer_DataService extends Remote {
    public ResultMessage add(MarketerPO marketerPO) throws RemoteException;

    public ResultMessage modify(MarketerPO marketerPO) throws RemoteException;

    public List<MarketerPO> findByMarketerName(String name) throws RemoteException;

    public List<MarketerPO> findByMarketerID(String id) throws RemoteException;

    public List<MarketerPO> findByPosition(WorkerPosition position) throws RemoteException;

    public ResultMessage delete(MarketerPO marketerPO) throws RemoteException;

    public MarketerPO find(String ID) throws RemoteException;
}
