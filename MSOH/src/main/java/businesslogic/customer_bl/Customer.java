package businesslogic.customer_bl;

import businesslogicservice.customer_blservice.Customer_BLService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import po.CustomerPO;
import po.HotelPO;
import util.DataFormat;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pxr on 16/11/16.
 */
public class Customer implements Customer_BLService {
    Customer_DataService_Stub customer_dataService_stub = new Customer_DataService_Stub();

    public int getCredit(String customerID) throws RemoteException {
        //返回该用户当前信用值
        if (customer_dataService_stub.findCustomerByID(customerID) == null)
            return -1;
        return customer_dataService_stub.findCustomerByID(customerID).getCredit();
    }

    public ResultMessage signUp(CustomerVO customerVO) throws RemoteException {
        if (customerVO.name.equals("") || customerVO.email.equals("") || customerVO.password.equals("") ||
                customerVO.ID.equals("") || customerVO.phone.equals("")) {
            //若用户未输入名字或邮箱或密码或ID或手机，则返回有信息空白
            return ResultMessage.Blank;
        } else if (customer_dataService_stub.findCustomerByID(customerVO.ID) != null) {
            //若用户输入ID，已存在该用户，返回注册已存在
            return ResultMessage.Customer_SignupExist;
        } else if (customerVO.password.matches(DataFormat.Password_Format)&&customerVO.email.matches(DataFormat.Email_Format)) {
            //若用户已输入ID并且不存在该ID的用户，则添加该用户，并返回注册成功
            customer_dataService_stub.addCustomer(new CustomerPO(customerVO.name, customerVO.password,
                    customerVO.phone, customerVO.email, customerVO.credit, customerVO.picUrl, customerVO.ID, customerVO.memberType));
            return ResultMessage.Customer_SignupSuccess;
        } else
            return ResultMessage.DataFormatWrong;
    }

    public ResultMessage changeInfo(CustomerVO customerVO) throws RemoteException {
        if (customer_dataService_stub.findCustomerByID(customerVO.ID) == null)
            return ResultMessage.Customer_CustomerNotExist;
        if (customerVO.name.equals("") || customerVO.email.equals("") || customerVO.phone.equals(""))
            //若用户修改时未填写名字，邮箱，手机号，返回有信息空白
            return ResultMessage.Blank;
        //根据ID获取该用户后修改用户的po，返回修改信息成功
        CustomerPO customerPO = customer_dataService_stub.findCustomerByID(customerVO.ID);
        customerPO.setEmail(customerVO.email);
        customerPO.setPhone(customerVO.phone);
        customerPO.setPicture(customerVO.picUrl);
        customerPO.setUserName(customerVO.name);
        customer_dataService_stub.modifyCustomer(customerPO);
        return ResultMessage.ChangeInfoSuccess;
    }

    public List<HotelVO> getHistoryHotel(String customerID) throws RemoteException {
        //这个主要就是把po转成vo
        if (customer_dataService_stub.findCustomerByID(customerID) == null)
            return null;
        List<HotelVO> listVO = new ArrayList<HotelVO>();
        List<HotelPO> listPO = customer_dataService_stub.getCustomerReservedHotel(customerID);
        Iterator iterator = listPO.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            HotelPO hotelPO = (HotelPO) object;
            HotelVO hotelVO = new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(),
                    hotelPO.getIntro(), hotelPO.getInfra(), hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(),hotelPO.getPicUrls(),
                    hotelPO.getClerkName(), hotelPO.getClerkPhone(), hotelPO.getHotelID(), null, null);
            //这里有个疑问，评论和酒店每日信息需要转成VO吗？
            listVO.add(hotelVO);
        }
        return listVO;
    }

    public CreditRecordVO getCreditRecord(String customerID) throws RemoteException {
        return null;
    }

    public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) throws RemoteException {
        //暂时还不会写
        return null;
    }

    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException {
        if (ID.equals("") || oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {//ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        } else if (customer_dataService_stub.findCustomerByID(ID) == null) {
            return ResultMessage.Customer_CustomerNotExist;
        } else if (customer_dataService_stub.findCustomerByID(ID).getPassword().equals(oldPassword) && newPassword1.matches
                (DataFormat.Password_Format) && newPassword2.matches(DataFormat.Password_Format)) {//若旧密码输入正确
            if (newPassword1.equals(newPassword2)) {//如果两次新密码输入相同
                CustomerPO customerPO = customer_dataService_stub.findCustomerByID(ID);
                customerPO.setPassword(newPassword1);
                customer_dataService_stub.modifyCustomer(customerPO);
                return ResultMessage.ChangePasswordSuccess;
            } else {//两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!customer_dataService_stub.findCustomerByID(ID).getPassword().equals(oldPassword)) {//旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            return ResultMessage.DataFormatWrong;
    }
}
