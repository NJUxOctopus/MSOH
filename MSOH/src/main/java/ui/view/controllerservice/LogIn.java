package ui.view.controllerservice;

import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/13.
 */
public interface LogIn {
    public ResultMessage login(String ID,String password) throws RemoteException;

    public String ifRememberPW(String ID) throws RemoteException;

    public ResultMessage rememberPassword(String ID, String password) throws RemoteException;

    public ResultMessage cancelRemPassword(String ID) throws RemoteException;

    public List<String> getRememberedID() throws RemoteException;
}
