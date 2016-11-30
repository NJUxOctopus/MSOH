package businesslogicservice.clerk_blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import util.ResultMessage;
import vo.ClerkVO;

/**
 * @author ST 2016/10/15
 */
public interface Clerk_BLService extends Remote{

    public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException;

    public ResultMessage changeInfo(ClerkVO clerkVO) throws RemoteException;

    public ResultMessage deleteClerk(ClerkVO clerkVO) throws RemoteException;

    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException;
}
