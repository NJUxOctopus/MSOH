package businesslogic.customer_bl;

import businesslogic.hotel_bl.Hotel;
import businesslogicservice.customer_blservice.Customer_BLService;
import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;
import rmi.RemoteHelper;
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

    public double getCredit(CustomerVO customerVO) throws RemoteException {
        //返回该用户当前信用值
        return RemoteHelper.getInstance().getCustomerDataService().getCurrentCredit(customerVO.ID);
    }

    public ResultMessage signUp(CustomerVO customerVO) throws RemoteException {
        if (customerVO.name == null || customerVO.email == null || customerVO.password == null ||
                customerVO.ID == null || customerVO.phone == null) {
            //若用户未输入名字或邮箱或密码或ID或手机，则返回有信息空白
            return ResultMessage.Blank;
        } else if (RemoteHelper.getInstance().getCustomerDataService().find(customerVO.ID) == null && customerVO.ID != null) {
            //若用户已输入ID并且不存在该ID的用户，则添加该用户，并返回注册成功
            RemoteHelper.getInstance().getCustomerDataService().add(new CustomerPO(customerVO.name, customerVO.password,
                    customerVO.phone, customerVO.email, customerVO.credit, customerVO.pic, customerVO.ID, customerVO.memberType));
            return ResultMessage.Customer_SignupSuccess;
        } else if (RemoteHelper.getInstance().getCustomerDataService().find(customerVO.ID) != null && customerVO.ID != null) {
            //若用户输入ID，已存在该用户，返回注册已存在
            return ResultMessage.Customer_SignupExist;
        } else
            return null;
    }

    public ResultMessage changeInfo(CustomerVO customerVO) throws RemoteException {
        if (customerVO.name == null || customerVO.email == null || customerVO.phone == null || customerVO.pic == null) {
            //若用户修改时未填写名字，邮箱，手机号，返回有信息空白
            return ResultMessage.Blank;
        } else {//根据ID获取该用户后修改用户的po，返回修改信息成功
            CustomerPO customerPO = RemoteHelper.getInstance().getCustomerDataService().find(customerVO.ID);
            if (!customerPO.getEmail().equals(customerVO.email))
                customerPO.setEmail(customerVO.email);
            if (!customerPO.getPhone().equals(customerVO.phone))
                customerPO.setPhone(customerVO.phone);
            if (!customerPO.getPicture().equals(customerVO.pic))
                customerPO.setPicture(customerVO.pic);
            if (!customerPO.getUserName().equals(customerVO.name))
                customerPO.setUserName(customerVO.name);
            RemoteHelper.getInstance().getCustomerDataService().modify(customerPO);
            return ResultMessage.ChangeInfoSuccess;
        }
    }

    public List<HotelVO> getHistoryHotel(CustomerVO customerVO) throws RemoteException {
        //这个主要就是把po转成vo
        List<HotelVO> listVO = new ArrayList<HotelVO>();
        List<HotelPO> listPO = RemoteHelper.getInstance().getCustomerDataService().
                getReservedHotel(RemoteHelper.getInstance().getCustomerDataService().find(customerVO.ID));
        Iterator iterator = listPO.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            HotelPO hotelPO = (HotelPO) object;
            HotelVO hotelVO = new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(),
                    hotelPO.getIntro(), hotelPO.getInfra(), hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(),
                    hotelPO.getClerkName(), hotelPO.getClerkPhone(), hotelPO.getHotelID(), null, null);
            //这里有个疑问，评论和酒店每日信息需要转成VO吗？
            listVO.add(hotelVO);
        }
        return listVO;
    }

    public CreditRecordVO getCreditRecord(CustomerVO customerVO) throws RemoteException {
        //这个主要也是将po转成vo
        CreditRecordPO creditRecordPO = RemoteHelper.getInstance().getCustomerDataService().
                getCreditRecord(RemoteHelper.getInstance().getCustomerDataService().find(customerVO.ID));
        return new CreditRecordVO(creditRecordPO.getVariation(), creditRecordPO.getChangeTime(),
                creditRecordPO.getCustomerName(), creditRecordPO.getCustomerID(), creditRecordPO.getAfterChangeCredit(),
                creditRecordPO.getOrderID(), creditRecordPO.getMarketerName());
    }

    public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) throws RemoteException {
        //暂时还不会写
        return null;
    }

    public ResultMessage changePassword(String ID, String oldPassword, String newPassword1, String newPassword2) throws RemoteException {
        if (ID == null || oldPassword == null || newPassword1 == null || newPassword2 == null) {//ID或旧密码或两次新密码未输入
            return ResultMessage.Blank;
        } else if (RemoteHelper.getInstance().getCustomerDataService().find(ID).getPassword().equals(oldPassword)) {//若旧密码输入正确
            if (newPassword1.equals(newPassword2)) {//如果两次新密码输入相同
                CustomerPO customerPO = RemoteHelper.getInstance().getCustomerDataService().find(ID);
                customerPO.setPassword(newPassword1);
                RemoteHelper.getInstance().getCustomerDataService().modify(customerPO);
                return ResultMessage.ChangePasswordSuccess;
            } else {//两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!RemoteHelper.getInstance().getCustomerDataService().find(ID).getPassword().equals(oldPassword)) {//旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            return null;
    }
}
