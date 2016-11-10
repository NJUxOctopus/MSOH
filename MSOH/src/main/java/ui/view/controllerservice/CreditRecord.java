package ui.view.controllerservice;

import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;

/**
 * Created by zqh on 2016/11/10.
 */
public interface CreditRecord {
    public CreditRecordVO getCreditRecord(CustomerVO customerVO);

    public ResultMessage addCreditRecord(String ID,CreditRecordVO creditRecordVO);
}
