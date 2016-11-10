package ui.view.controllerservice;

import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zqh on 2016/11/10.
 */
public interface CreditCharge {
    public ResultMessage creditCharge(String ID, CustomerVO customerVO);


}
