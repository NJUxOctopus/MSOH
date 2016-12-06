package ui.controller;

import businesslogic.login_bl.Login;
import businesslogic.login_bl.MockLogin;
import ui.view.controllerservice.LogIn;
import ui.view.presentation.PaneAdder;
import util.ResultMessage;
import businesslogicservice.login_blservice.Login_BLService;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/13.
 */
public class LogInController implements LogIn {

    private Login_BLService login_blService;

    public LogInController() {
        login_blService = new Login();
    }

    /**
     * 登录
     *
     * @param ID
     * @param password
     * @return
     * @throws RemoteException
     */
    public ResultMessage login(String ID, String password) throws RemoteException {
//        login_blService = new MockLogin(ID, password);
        ResultMessage resultMessage = login_blService.login(ID, password);
        return resultMessage;
    }

    /**
     * 若账号已被记住，选择账号后直接显示密码，若未被记住返回空
     *
     * @param ID
     * @return resultMessage
     * @throws RemoteException
     */
    public String ifRememberPW(String ID) throws RemoteException {
        if (login_blService.autoLogin(ID) != null) {
            return login_blService.autoLogin(ID);
        } else {
            return "";
        }
    }

    /**
     * 记住密码
     *
     * @param ID
     * @param password
     * @return
     * @throws RemoteException
     */
    public ResultMessage rememberPassword(String ID, String password) throws RemoteException {
        return login_blService.rememberPassword(ID, password);
    }

    /**
     * 取消记住密码
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ResultMessage cancelRemPassword(String ID) throws RemoteException {
        return login_blService.cancelRemPassword(ID);
    }
}
