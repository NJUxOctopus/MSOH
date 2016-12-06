package ui.view.controllerservice;

import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/10.
 */
public interface CreditRecord {
    public List<CreditRecordVO> getCreditRecord(String customerID) throws RemoteException;

    public ResultMessage addCreditRecord(String ID,CreditRecordVO creditRecordVO);
}
