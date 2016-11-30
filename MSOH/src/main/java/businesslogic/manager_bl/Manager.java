package businesslogic.manager_bl;

import businesslogicservice.manager_blservice.Manager_BLService;
import dataservice.manager_dataservice.Manager_DataService_Stub;
import po.ManagerPO;
import util.DataFormat;
import util.ResultMessage;
import vo.ManagerVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/23.
 */
public class Manager implements Manager_BLService {
    Manager_DataService_Stub manager_dataService_stub = new Manager_DataService_Stub();

    /**
     * 更改管理人员的信息
     * @param managerVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeInfo(ManagerVO managerVO) throws RemoteException{
        ManagerPO managerPO = manager_dataService_stub.findManager(managerVO.ID);
        if(managerPO==null)
            //找不到该管理人员
            return ResultMessage.Manager_ManagerNotExist;
        if(managerVO.name.equals("")||managerVO.phone.equals("")){
            //若姓名，电话为空
            return ResultMessage.Blank;
        }
        managerPO.setPic(managerVO.picUrl);
        managerPO.setPhone(managerVO.phone);
        managerPO.setName(managerVO.name);
        manager_dataService_stub.modifyManager(managerPO);
        return ResultMessage.Manager_ChangeManagerInfoSuccess;
    }

    /**
     * 更改密码
     * @param ID
     * @param oldPassword
     * @param newPassword1
     * @param newPassword2
     * @return
     * @throws RemoteException
     */
    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException{
        if (ID.equals("") || oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {//ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        }else if(manager_dataService_stub.findManager(ID)==null){
            return ResultMessage.Manager_ManagerNotExist;
        }else if (manager_dataService_stub.findManager(ID).getPassword().equals(oldPassword)&&newPassword1.matches
                (DataFormat.Password_Format)&&newPassword2.matches(DataFormat.Password_Format)) {//若旧密码输入正确
            if (newPassword1.equals(newPassword2)) {//如果两次新密码输入相同
                ManagerPO managerPO = manager_dataService_stub.findManager(ID);
                managerPO.setPassword(newPassword1);
                manager_dataService_stub.modifyManager(managerPO);
                return ResultMessage.ChangePasswordSuccess;
            } else {//两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!manager_dataService_stub.findManager(ID).getPassword().equals(oldPassword)) {//旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            return ResultMessage.DataFormatWrong;
    }
}
