package businesslogicservice.marketerUtil_blservice;

import vo.MarketerVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Pxr on 16/11/23.
 */
public interface MarketerUtil_BLService {
    public MarketerVO getSingle(String marketerID)throws RemoteException;

    public List<MarketerVO> getByName(String name)throws RemoteException;

    public List<MarketerVO> getAll()throws RemoteException;
}
