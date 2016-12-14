package ui.view.controllerservice;

import util.ResultMessage;
import vo.ManagerVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/13.
 */
public interface ManagerInfoChange {
    public ResultMessage changeInfo(ManagerVO managerVO)throws RemoteException;

    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)throws RemoteException;
}
