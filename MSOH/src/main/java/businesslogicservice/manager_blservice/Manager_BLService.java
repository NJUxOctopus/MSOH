package businesslogicservice.manager_blservice;

import util.ResultMessage;
import vo.ManagerVO;

import java.rmi.RemoteException;

/**
 * 
 * @author ST 2016/10/15
 *
 */

public interface Manager_BLService {
	
	public ResultMessage changeInfo(ManagerVO managerVO)throws RemoteException;

	public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)throws RemoteException;
}
