package ui.controller;

import businesslogic.manager_bl.Manager;
import businesslogicservice.manager_blservice.Manager_BLService;
import ui.view.controllerservice.ManagerInfoChange;
import util.ResultMessage;
import vo.ManagerVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/13.
 */
public class ManagerInfoChangeController implements ManagerInfoChange {
    private Manager_BLService manager_blService;

    public ManagerInfoChangeController(){
        manager_blService = new Manager();
    }
    public ResultMessage changeInfo(ManagerVO managerVO)throws RemoteException{
        return manager_blService.changeInfo(managerVO);
    }

    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)throws RemoteException{
        return manager_blService.changePassword(ID, oldPassword, newPassword1, newPassword2);
    }
}
