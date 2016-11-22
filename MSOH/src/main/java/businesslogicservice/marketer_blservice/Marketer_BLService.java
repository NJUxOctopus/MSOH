package businesslogicservice.marketer_blservice;

import java.rmi.RemoteException;
import java.util.List;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MarketerVO;

public interface Marketer_BLService {

    public ResultMessage deleteMarketer(String marketerID) throws RemoteException;

    public ResultMessage addMarketer(MarketerVO marketerVO) throws RemoteException;

    public ResultMessage changeInfo(MarketerVO marketerVO) throws RemoteException ;

    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2)throws RemoteException;

    public ResultMessage CreditCharge(String ID, int credit, CustomerVO customerVO)throws RemoteException;
}
