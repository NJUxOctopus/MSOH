package ui.view.controllerservice;

import util.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/10.
 */
public interface CustomerSignUp {
    public ResultMessage signUp(CustomerVO customerVO) throws RemoteException;
}
