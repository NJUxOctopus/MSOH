package ui.controller;

import ui.view.controllerservice.CustomerInfoChange;
import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zqh on 2016/11/10.
 */
public class CustomerInfoChangeController implements CustomerInfoChange{
    @Override
    public ResultMessage changeInfo(CustomerVO customerVO){
        return null;
    }

    @Override
    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
        return null;
    }
}
