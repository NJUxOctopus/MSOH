package ui.view.controllerservice;

import util.ResultMessage;
import vo.ClerkVO;

import java.rmi.RemoteException;


/**
 * Created by zqh on 2016/11/10.
 */
public interface ClerkInfoChange {
    public ResultMessage changeInfo(ClerkVO clerkVO) throws RemoteException;

    public ResultMessage changePassword(String ID,String oldPw,String newPw1,String newPw2) throws RemoteException;
}
