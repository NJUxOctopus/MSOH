package ui.controller;

import businesslogic.login_bl.Login;
import businesslogic.login_bl.MockLogin;
import ui.view.controllerservice.LogIn;
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

    public ResultMessage login(String ID, String password) throws RemoteException {
//        login_blService = new MockLogin(ID, password);
        ResultMessage resultMessage = login_blService.login(ID, password);
        return resultMessage;
    }
}
