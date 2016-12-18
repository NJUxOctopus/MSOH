package ui.controller;

import businesslogic.customer_bl.Customer;
import businesslogicservice.customer_blservice.Customer_BLService;
import ui.view.controllerservice.CreditRecord;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/10.
 */
public class CreditRecordController implements CreditRecord {

    private Customer_BLService customer_blService;

    public CreditRecordController() {
        customer_blService = new Customer();
    }

    @Override
    public List<CreditRecordVO> getCreditRecord(String customerID) throws RemoteException {
        return customer_blService.getCreditRecord(customerID);

    }

    @Override
    public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) throws RemoteException {
        return customer_blService.addCreditRecord(ID, creditRecordVO);
    }
}
