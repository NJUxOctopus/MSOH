package businesslogic.marketer_bl;

import businesslogic.customer_bl.CustomerUtil;
import businesslogicservice.marketer_blservice.Marketer_BLService;
import dataservice.marketer_dataservice.Marketer_DataService;
import po.CustomerPO;
import po.MarketerPO;
import rmi.RemoteHelper;
import util.DataFormat;
import util.ResultMessage;
import util.WorkerPosition;
import vo.CustomerVO;
import vo.MarketerVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/22.
 */
public class Marketer implements Marketer_BLService {
    private Marketer_DataService marketer_dataService = RemoteHelper.getInstance().getMarketerDataService();

    /**
     * 根据ID删除营销人员
     *
     * @param marketerID
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteMarketer(String marketerID) throws RemoteException {
        if (marketer_dataService.findMarketerByID(marketerID) == null)
            //若无营销人员
            return ResultMessage.Marketer_MarketerNotExist;
        if (marketer_dataService.deleteMarketer(marketer_dataService.findMarketerByID(marketerID)))
            return ResultMessage.Marketer_DeleteMarketerSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 添加营销人员
     *
     * @param marketerVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addMarketer(MarketerVO marketerVO) throws RemoteException {
        if (marketerVO.name.equals("") || marketerVO.ID.equals("") || marketerVO.password.equals("") || marketerVO.phone.equals(""))
            //若名字，ID，密码或电话为空
            return ResultMessage.Blank;
        if (marketer_dataService.findMarketerByID(marketerVO.ID) != null)
            //若ID已存在
            return ResultMessage.Marketer_MarketerAlreadyExist;
        if (marketerVO.password.matches(DataFormat.Password_Format)&&marketerVO.phone.matches(DataFormat.Phone_Format)&&
                marketerVO.ID.matches(DataFormat.ID_Format)) {
            //若密码格式正确
            if (marketer_dataService.addMarketer(new MarketerPO(marketerVO.name, marketerVO.ID, marketerVO.phone,
                    marketerVO.password, marketerVO.picUrl, WorkerPosition.Marketer)))
                return ResultMessage.Marketer_AddMarketerSuccess;
            else
                return ResultMessage.Fail;
        } else
            return ResultMessage.DataFormatWrong;
    }

    /**
     * 更改营销人员信息
     *
     * @param marketerVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeInfo(MarketerVO marketerVO) throws RemoteException {
        if (marketer_dataService.findMarketerByID(marketerVO.ID) == null)
            //若营销人员不存在
            return ResultMessage.Marketer_MarketerNotExist;
        else if (marketerVO.phone.equals("") || marketerVO.name.equals(""))
            //电话或名称为空
            return ResultMessage.Blank;
        if(!marketerVO.phone.matches(DataFormat.Phone_Format))
            return ResultMessage.DataFormatWrong;
        MarketerPO marketerPO = marketer_dataService.findMarketerByID(marketerVO.ID);
        marketerPO.setPhone(marketerVO.phone);
        //marketerPO.setPicUrl(marketerVO.picUrl);
        marketerPO.setName(marketerVO.name);
        if (marketer_dataService.modifyMarketer(marketerPO))
            return ResultMessage.Marketer_ChangeInfoSuccess;
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
        if (ID.equals("") || oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {//ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        } else if (marketer_dataService.findMarketerByID(ID) == null) {
            return ResultMessage.Marketer_MarketerNotExist;
        } else if (marketer_dataService.findMarketerByID(ID).getPassword().equals(oldPassword) && newPassword1.matches
                (DataFormat.Password_Format) && newPassword2.matches(DataFormat.Password_Format)) {//若旧密码输入正确
            if (newPassword1.equals(newPassword2)) {//如果两次新密码输入相同
                MarketerPO marketerPO = marketer_dataService.findMarketerByID(ID);
                marketerPO.setPassword(newPassword1);
                if (marketer_dataService.modifyMarketer(marketerPO))
                    return ResultMessage.ChangePasswordSuccess;
                else
                    return ResultMessage.Fail;
            } else {//两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!marketer_dataService.findMarketerByID(ID).getPassword().equals(oldPassword)) {//旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            return ResultMessage.DataFormatWrong;
    }


}
