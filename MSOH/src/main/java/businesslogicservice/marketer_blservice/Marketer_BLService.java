package businesslogicservice.marketer_blservice;

import util.ResultMessage;
import vo.MarketerVO;

import java.rmi.RemoteException;

public interface Marketer_BLService {

    public ResultMessage deleteMarketer(String marketerID) throws RemoteException;

    public ResultMessage addMarketer(MarketerVO marketerVO) throws RemoteException;

    public ResultMessage changeInfo(MarketerVO marketerVO) throws RemoteException ;

    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2)throws RemoteException;

}
