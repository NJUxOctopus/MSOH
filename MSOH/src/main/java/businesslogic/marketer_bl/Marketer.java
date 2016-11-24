package businesslogic.marketer_bl;

import businesslogicservice.marketer_blservice.Marketer_BLService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import dataservice.marketer_dataservice.Marketer_DataService_Stub;
import po.CustomerPO;
import po.MarketerPO;
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
    Marketer_DataService_Stub marketer_dataService_stub = new Marketer_DataService_Stub();
    Customer_DataService_Stub customer_dataService_stub = new Customer_DataService_Stub();
    public ResultMessage deleteMarketer(String marketerID) throws RemoteException {
        if (marketer_dataService_stub.findMarketerByID(marketerID) == null)
            return ResultMessage.Marketer_MarketerNotExist;
        marketer_dataService_stub.deleteMarketer(marketer_dataService_stub.findMarketerByID(marketerID));
        return ResultMessage.Marketer_DeleteMarketerSuccess;
    }

    public ResultMessage addMarketer(MarketerVO marketerVO) throws RemoteException {
        if(marketerVO.name.equals("")||marketerVO.ID.equals("")||marketerVO.password.equals("")||marketerVO.phone.equals(""))
            return ResultMessage.Blank;
        if (marketer_dataService_stub.findMarketerByID(marketerVO.ID) != null)
            return ResultMessage.Marketer_MarketerAlreadyExist;
        if(marketerVO.password.matches(DataFormat.Password_Format)) {
            marketer_dataService_stub.addMarketer(new MarketerPO(marketerVO.name, marketerVO.ID, marketerVO.phone,
                    marketerVO.password, marketerVO.picUrl, WorkerPosition.Marketer));
            return ResultMessage.Marketer_AddMarketerSuccess;
        }else
            return ResultMessage.DataFormatWrong;
    }

    public ResultMessage changeInfo(MarketerVO marketerVO) throws RemoteException{
        if(marketer_dataService_stub.findMarketerByID(marketerVO.ID)==null)
            return ResultMessage.Marketer_MarketerNotExist;
        else if(marketerVO.phone.equals("")||marketerVO.name.equals(""))
            return ResultMessage.Blank;
        MarketerPO  marketerPO= marketer_dataService_stub.findMarketerByID(marketerVO.ID);
        marketerPO.setPhone(marketerVO.phone);
        marketerPO.setPicUrl(marketerVO.picUrl);
        marketerPO.setName(marketerVO.name);
        marketer_dataService_stub.modifyMarketer(marketerPO);
        return ResultMessage.Marketer_ChangeInfoSuccess;
    }

    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException{
        if (ID.equals("") || oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {//ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        } else if(marketer_dataService_stub.findMarketerByID(ID)==null){
            return ResultMessage.Marketer_MarketerNotExist;
        } else if (marketer_dataService_stub.findMarketerByID(ID).getPassword().equals(oldPassword)&&newPassword1.matches
                (DataFormat.Password_Format)&&newPassword2.matches(DataFormat.Password_Format)) {//若旧密码输入正确
            if (newPassword1.equals(newPassword2)) {//如果两次新密码输入相同
                MarketerPO marketerPO = marketer_dataService_stub.findMarketerByID(ID);
                marketerPO.setPassword(newPassword1);
                marketer_dataService_stub.modifyMarketer(marketerPO);
                return ResultMessage.ChangePasswordSuccess;
            } else {//两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!marketer_dataService_stub.findMarketerByID(ID).getPassword().equals(oldPassword)) {//旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            return ResultMessage.DataFormatWrong;
    }

    public ResultMessage CreditCharge(String ID, int credit, CustomerVO customerVO) throws RemoteException{
        if(customer_dataService_stub.findCustomerByID(ID)==null)
            return ResultMessage.Customer_CustomerNotExist;
        customerVO.credit+=credit;
        CustomerPO customerPO = customer_dataService_stub.findCustomerByID(ID);
        customerPO.setCredit(customerPO.getCredit()+credit);
        customer_dataService_stub.modifyCustomer(customerPO);
        return ResultMessage.Marketer_CreditChargeSuccess;
    }
}
