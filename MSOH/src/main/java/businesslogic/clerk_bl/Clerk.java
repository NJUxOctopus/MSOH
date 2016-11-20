package businesslogic.clerk_bl;

import businesslogicservice.clerk_blservice.Clerk_BLService;
import dataservice.clerk_dataservice.Clerk_DataService_Stub;
import po.ClerkPO;
import rmi.RemoteHelper;
import util.ResultMessage;
import util.WorkerPosition;
import vo.ClerkVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/17.
 */
public class Clerk implements Clerk_BLService {
    Clerk_DataService_Stub clerk_dataService_stub = new Clerk_DataService_Stub();
    public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException {
        if (clerkVO.name == "" || clerkVO.phone == "" || clerkVO.ID == "" ||
                clerkVO.password == "" || clerkVO.hotelID == "" || clerkVO.hotelName == "") {
            //若工作人员的名字或电话或ID或密码或所在酒店ID，名称为空
            return ResultMessage.Blank;
        } else if (clerkVO.ID != "" && clerk_dataService_stub.findClerkByID(clerkVO.ID) != null) {
            //若已存在该工作人员
            return ResultMessage.Clerk_AddClerkExist;
        } else {
            //否则，添加工作人员
            clerk_dataService_stub.addClerk(new ClerkPO(clerkVO.hotelName, clerkVO.phone,
                    clerkVO.password, clerkVO.ID, clerkVO.hotelName, clerkVO.hotelID, WorkerPosition.Clerk, clerkVO.pic));
            return ResultMessage.Clerk_AddClerkSuccess;
        }
    }

    public ResultMessage changeInfo(ClerkVO clerkVO) throws RemoteException {
        if (clerkVO.name == "" || clerkVO.phone == "" || clerkVO.ID == "") {
            //若工作人员的名字或电话或ID或密码或所在酒店ID，名称为空
            return ResultMessage.Blank;
        } else {
            ClerkPO clerkPO = clerk_dataService_stub.findClerkByID(clerkVO.ID);
            if (!clerkPO.getPhone().equals(clerkVO.phone))
                clerkPO.setPhone(clerkVO.phone);
            if (!clerkPO.getPic().equals(clerkVO.pic))
                clerkPO.setPic(clerkVO.pic);
            if (!clerkPO.getName().equals(clerkVO.name))
                clerkPO.setName(clerkVO.name);
            clerk_dataService_stub.modifyClerk(clerkPO);
            return ResultMessage.ChangeInfoSuccess;
        }
    }

    public ResultMessage deleteClerk(ClerkVO clerkVO) throws RemoteException {
        if (clerk_dataService_stub.findClerkByID(clerkVO.ID) == null) {//若该工作人员不存在
            return ResultMessage.Clerk_DeleteClerkNotExist;
        } else {//这里直接掉数据层吗？
            clerk_dataService_stub.deleteClerk(clerk_dataService_stub.findClerkByID(clerkVO.ID));
            return ResultMessage.Clerk_DeleteClerkSuccess;
        }
    }

    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException {
        //改密码感觉跟别的有重复的了，是不是应该抽出来
        if (ID == "" || oldPassword == "" || newPassword1 == "" || newPassword2 == "") {//ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        } else if (clerk_dataService_stub.findClerkByID(ID).getPassword().equals(oldPassword)) {//若旧密码输入正确
            if (newPassword1.equals(newPassword2)) {//如果两次新密码输入相同
                ClerkPO clerkPO = clerk_dataService_stub.findClerkByID(ID);
                clerkPO.setPassword(newPassword1);
                clerk_dataService_stub.modifyClerk(clerkPO);
                return ResultMessage.ChangePasswordSuccess;
            } else {//两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!clerk_dataService_stub.findClerkByID(ID).getPassword().equals(oldPassword)) {//旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            return null;
    }
}
