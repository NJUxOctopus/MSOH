package ui.controller;

import businesslogic.marketer_bl.Marketer;
import businesslogicservice.marketer_blservice.Marketer_BLService;
import ui.view.controllerservice.MarketerInfoChange;
import util.ResultMessage;
import vo.MarketerVO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/10.
 */
public class MarketerInfoChangeController implements MarketerInfoChange{
    private Marketer_BLService marketer_blService;

    public MarketerInfoChangeController() {
        marketer_blService = new Marketer();
    }
    public ResultMessage changeInfo(MarketerVO marketerVO)throws RemoteException {
        return marketer_blService.changeInfo(marketerVO);
    }

    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2)throws RemoteException {
        return null;
    }
}
