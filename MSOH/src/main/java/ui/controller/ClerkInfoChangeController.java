package ui.controller;

import ui.view.controllerservice.ClerkInfoChange;
import util.ResultMessage;
import vo.ClerkVO;

/**
 * Created by zqh on 2016/11/10.
 */
public class ClerkInfoChangeController implements ClerkInfoChange{
    @Override
    public ResultMessage changeInfo(ClerkVO clerkVO) {
        return null;
    }

    @Override
    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
        return null;
    }
}
