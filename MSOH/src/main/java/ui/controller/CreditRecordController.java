package ui.controller;

import ui.view.controllerservice.CreditRecord;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;

/**
 * Created by zqh on 2016/11/10.
 */
public class CreditRecordController implements CreditRecord{

    @Override
    public CreditRecordVO getCreditRecord(CustomerVO customerVO) {
        return null;
    }

    @Override
    public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) {
        return null;
    }
}
