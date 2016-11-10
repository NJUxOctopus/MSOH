package ui.view.controllerservice;

import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zqh on 2016/11/10.
 */
public interface CustomerInfoChange {
    public ResultMessage changeInfo(CustomerVO customerVO);

    public ResultMessage changePassword(String ID,String oldPw,String newPw1,String newPw2);
}
