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
public class MarketerInfoChangeController implements MarketerInfoChange {

    private Marketer_BLService marketer_blService;

    public MarketerInfoChangeController() {
        marketer_blService = new Marketer();
    }

    public ResultMessage changeInfo(MarketerVO marketerVO) {
        return null;
    }

    /**
     * 修改密码
     *
     * @param ID
     * @param oldPassword
     * @param newPassword1
     * @param newPassword2
     * @return
     * @throws RemoteException
     */
    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException {
        return marketer_blService.changePassword(ID, oldPassword, newPassword1, newPassword2);
    }
}
