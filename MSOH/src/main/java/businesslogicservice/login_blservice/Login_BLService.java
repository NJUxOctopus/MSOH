package businesslogicservice.login_blservice;

import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * @author ST 2016/10/14
 */

public interface Login_BLService {

    public ResultMessage login(String ID, String password) throws RemoteException;

    public ResultMessage rememberPassword(String ID,String password)throws RemoteException;

    public ResultMessage cancelRemPassword(String ID)throws RemoteException;

    public String autoLogin(String ID)throws RemoteException;
}
