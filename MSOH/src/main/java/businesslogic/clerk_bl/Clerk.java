package businesslogic.clerk_bl;

import businesslogic.bl_Factory.Abstract_BLFactory;
import businesslogic.bl_Factory.Default_BLFactory;
import businesslogic.hotel_bl.Hotel;
import businesslogicservice.clerk_blservice.Clerk_BLService;
import dataservice.clerk_dataservice.Clerk_DataService;
import po.ClerkPO;
import rmi.RemoteHelper;
import util.DataFormat;
import util.ResultMessage;
import util.WorkerPosition;
import vo.ClerkVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/17.
 */
public class Clerk implements Clerk_BLService {
    private Clerk_DataService clerk_dataService = RemoteHelper.getInstance().getClerkDataService();
    private Abstract_BLFactory abstract_blFactory = new Default_BLFactory();

    /**
     * 添加工作人员
     *
     * @param clerkVO
     * @return ResultMessage
     * @throws RemoteException
     */
    public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException {
        Hotel hotel = abstract_blFactory.createHotel();
        if (clerkVO.name.equals("") || clerkVO.phone.equals("") || clerkVO.ID.equals("") ||
                clerkVO.password.equals("") || clerkVO.hotelID.equals("")) {
            //若工作人员的名字或电话或ID或密码或所在酒店ID，名称为空
            return ResultMessage.Blank;
        } else if (clerk_dataService.findClerkByID(clerkVO.ID) != null) {
            //若已存在该工作人员
            return ResultMessage.Clerk_AddClerkExist;
        } else if (hotel.addClerk(clerkVO).equals(ResultMessage.Hotel_HasClerk)) {
            return ResultMessage.Clerk_AddClerkExist;
        } else if (hotel.addClerk(clerkVO).equals(ResultMessage.Clerk_AddClerkSuccess) &&
                clerkVO.password.matches(DataFormat.Password_Format) &&
                clerkVO.phone.matches(DataFormat.Phone_Format)
                && clerkVO.ID.matches(DataFormat.ID_Format)) {
            //若数据格式正确，添加工作人员
            clerk_dataService.addClerk(new ClerkPO(clerkVO.name, clerkVO.phone,
                    clerkVO.password, clerkVO.ID, clerkVO.hotelName, clerkVO.hotelID, WorkerPosition.Clerk, clerkVO.picUrl));
            return ResultMessage.Clerk_AddClerkSuccess;
        } else
            //否则，返回数据格式错误
            return ResultMessage.DataFormatWrong;
    }

    /**
     * 工作人员更改个人信息
     *
     * @param clerkVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeInfo(ClerkVO clerkVO) throws RemoteException {
        //若不存在该工作人员
        if (clerk_dataService.findClerkByID(clerkVO.ID) == null)
            return ResultMessage.Clerk_ClerkNotExist;
        if (clerkVO.name.equals("") || clerkVO.phone.equals("") || clerkVO.ID.equals(""))
            //若工作人员的名字或电话或ID或所在酒店ID，名称为空
            return ResultMessage.Blank;
        if (!clerkVO.phone.matches(DataFormat.Phone_Format))
            return ResultMessage.DataFormatWrong;
        ClerkPO clerkPO = clerk_dataService.findClerkByID(clerkVO.ID);
        clerkPO.setPhone(clerkVO.phone);
//        clerkPO.setPicUrl(clerkVO.picUrl);
        clerkPO.setName(clerkVO.name);
        if (clerk_dataService.modifyClerk(clerkPO))
            return ResultMessage.ChangeInfoSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 删除工作人员
     *
     * @param clerkVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteClerk(ClerkVO clerkVO) throws RemoteException {
        if (clerk_dataService.findClerkByID(clerkVO.ID) == null)
            //若该工作人员不存在
            return ResultMessage.Clerk_DeleteClerkNotExist;
        Hotel hotel = abstract_blFactory.createHotel();
        if (clerk_dataService.deleteClerk(clerk_dataService.findClerkByID(clerkVO.ID)) &&
                hotel.deleteClerk(clerkVO.hotelID).equals(ResultMessage.Hotel_deleteClerkSuccess))
            return ResultMessage.Clerk_DeleteClerkSuccess;
        else
            return ResultMessage.Fail;
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
        if (ID.equals("") || oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {
            //ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        } else if (clerk_dataService.findClerkByID(ID) == null) {
            //不存在该用户
            return ResultMessage.Clerk_ClerkNotExist;
        } else if (clerk_dataService.findClerkByID(ID).getPassword().equals(oldPassword) && newPassword1.matches
                (DataFormat.Password_Format) && newPassword2.matches(DataFormat.Password_Format)) {
            //若旧密码输入正确,且密码格式正确
            if (newPassword1.equals(newPassword2)) {
                //如果两次新密码输入相同
                ClerkPO clerkPO = clerk_dataService.findClerkByID(ID);
                clerkPO.setPassword(newPassword1);
                if (clerk_dataService.modifyClerk(clerkPO))
                    return ResultMessage.ChangePasswordSuccess;
                else
                    return ResultMessage.Fail;
            } else {
                //两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!clerk_dataService.findClerkByID(ID).getPassword().equals(oldPassword)) {
            //旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            //密码格式错误
            return ResultMessage.DataFormatWrong;
    }
}
