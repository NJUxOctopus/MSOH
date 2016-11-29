package businesslogic.clerk_bl;

import businesslogicservice.clerk_blservice.Clerk_BLService;
import dataservice.clerk_dataservice.Clerk_DataService_Stub;
import dataservice.hotel_dataservice.Hotel_DataService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import po.ClerkPO;
import util.DataFormat;
import util.ResultMessage;
import util.WorkerPosition;
import vo.ClerkVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/17.
 */
public class Clerk implements Clerk_BLService {
    Clerk_DataService_Stub clerk_dataService_stub = new Clerk_DataService_Stub();
    Hotel_DataService_Stub hotel_dataService_stub = new Hotel_DataService_Stub();
    /**
     * 添加工作人员
     * @param clerkVO
     * @return ResultMessage
     * @throws RemoteException
     */
    public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException {
        if (clerkVO.name.equals("") || clerkVO.phone.equals("") || clerkVO.ID.equals("") ||
                clerkVO.password.equals("") || clerkVO.hotelID.equals("") || clerkVO.hotelName.equals("")) {
            //若工作人员的名字或电话或ID或密码或所在酒店ID，名称为空
            return ResultMessage.Blank;
        } else if (clerk_dataService_stub.findClerkByID(clerkVO.ID) != null) {
            //若已存在该工作人员
            return ResultMessage.Clerk_AddClerkExist;
        } else if(hotel_dataService_stub.findHotelByID(clerkVO.hotelID)!=null&&
                !hotel_dataService_stub.findHotelByID(clerkVO.hotelID).getClerkName().equals("")){
            //若工作人员的酒店已有工作人员
            return ResultMessage.Hotel_HasClerk;
        } else if(clerkVO.password.matches(DataFormat.Password_Format)){
            //若数据格式正确，添加工作人员
            clerk_dataService_stub.addClerk(new ClerkPO(clerkVO.hotelName, clerkVO.phone,
                    clerkVO.password, clerkVO.ID, clerkVO.hotelName, clerkVO.hotelID, WorkerPosition.Clerk, clerkVO.picUrl));
            return ResultMessage.Clerk_AddClerkSuccess;
        } else
            //否则，返回数据格式错误
            return ResultMessage.DataFormatWrong;
    }

    /**
     * 工作人员更改个人信息
     * @param clerkVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeInfo(ClerkVO clerkVO) throws RemoteException {
        //若不存在该工作人员
        if(clerk_dataService_stub.findClerkByID(clerkVO.ID)==null)
            return ResultMessage.Clerk_ClerkNotExist;
        if (clerkVO.name.equals("") || clerkVO.phone.equals("") || clerkVO.ID.equals(""))
            //若工作人员的名字或电话或ID或所在酒店ID，名称为空
            return ResultMessage.Blank;
        ClerkPO clerkPO = clerk_dataService_stub.findClerkByID(clerkVO.ID);
        clerkPO.setPhone(clerkVO.phone);
        clerkPO.setPicUrl(clerkVO.picUrl);
        clerkPO.setName(clerkVO.name);
        clerk_dataService_stub.modifyClerk(clerkPO);
        return ResultMessage.ChangeInfoSuccess;
    }

    /**
     *删除工作人员
     * @param clerkVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteClerk(ClerkVO clerkVO) throws RemoteException {
        if (clerk_dataService_stub.findClerkByID(clerkVO.ID) == null)
            //若该工作人员不存在
            return ResultMessage.Clerk_DeleteClerkNotExist;
        clerk_dataService_stub.deleteClerk(clerk_dataService_stub.findClerkByID(clerkVO.ID));
        return ResultMessage.Clerk_DeleteClerkSuccess;
    }

    /**
     * 修改密码
     * @param ID
     * @param oldPassword
     * @param newPassword1
     * @param newPassword2
     * @return
     * @throws RemoteException
     */
    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException {
        if (ID.equals("") || oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {
            //ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        } else if(clerk_dataService_stub.findClerkByID(ID)==null){
            //不存在该用户
            return ResultMessage.Clerk_ClerkNotExist;
        }else if (clerk_dataService_stub.findClerkByID(ID).getPassword().equals(oldPassword)&&newPassword1.matches
                (DataFormat.Password_Format)&&newPassword2.matches(DataFormat.Password_Format)) {
            //若旧密码输入正确,且密码格式正确
            if (newPassword1.equals(newPassword2)) {
                //如果两次新密码输入相同
                ClerkPO clerkPO = clerk_dataService_stub.findClerkByID(ID);
                clerkPO.setPassword(newPassword1);
                clerk_dataService_stub.modifyClerk(clerkPO);
                return ResultMessage.ChangePasswordSuccess;
            } else {
                //两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!clerk_dataService_stub.findClerkByID(ID).getPassword().equals(oldPassword)) {
            //旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            //密码格式错误
            return ResultMessage.DataFormatWrong;
    }
}
