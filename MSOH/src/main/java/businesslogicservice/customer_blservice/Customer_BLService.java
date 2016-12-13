package businesslogicservice.customer_blservice;

import java.rmi.RemoteException;
import java.util.List;

import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;

/**
 * @author ST 2016/10/15
 */

public interface Customer_BLService {
    public int getCredit(String customerID) throws RemoteException;

    public ResultMessage signUp(CustomerVO customerVO) throws RemoteException;

    public ResultMessage changeInfo(CustomerVO customerVO) throws RemoteException;

    public List<HotelVO> getHistoryHotel(String customerID) throws RemoteException;

    public List<CreditRecordVO> getCreditRecord(String customerID) throws RemoteException;

    public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) throws RemoteException;

    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) throws RemoteException;

    public ResultMessage creditCharge(String ID, int credit) throws RemoteException;

    public ResultMessage changeCredit(String ID,int change)throws RemoteException;
}
