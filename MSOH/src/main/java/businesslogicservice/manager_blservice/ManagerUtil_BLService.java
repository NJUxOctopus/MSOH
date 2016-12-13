package businesslogicservice.manager_blservice;

import businesslogic.manager_bl.Manager;
import vo.ManagerVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/12/13.
 */
public interface ManagerUtil_BLService {
    public ManagerVO getByID(String managerID)throws RemoteException;
}
