package ui.view.controllerservice;

import util.ResultMessage;
import vo.MarketerVO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/10.
 */
public interface MarketerInfoChange {
    public ResultMessage changeInfo(MarketerVO marketerVO);

    public ResultMessage changePassword(String ID,String oldPw,String newPw1,String newPw2) throws RemoteException;
}
