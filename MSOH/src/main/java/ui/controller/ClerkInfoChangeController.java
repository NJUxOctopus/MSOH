package ui.controller;

import businesslogic.clerk_bl.Clerk;
import businesslogicservice.clerk_blservice.Clerk_BLService;
import ui.view.controllerservice.ClerkInfoChange;
import util.ResultMessage;
import vo.ClerkVO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/10.
 */
public class ClerkInfoChangeController implements ClerkInfoChange {

    private Clerk_BLService clerk_blService;

    public ClerkInfoChangeController(){
        clerk_blService = new Clerk();
    }

    /**
     * 工作人员更改个人信息
     *
     * @param clerkVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeInfo(ClerkVO clerkVO) throws RemoteException {
        return clerk_blService.changeInfo(clerkVO);
    }

    /**
     * 工作人员修改密码
     *
     * @param ID
     * @param oldPw
     * @param newPw1
     * @param newPw2
     * @return
     * @throws RemoteException
     */
    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) throws RemoteException {
        return clerk_blService.changePassword(ID, oldPw, newPw1, newPw2);
    }
}
