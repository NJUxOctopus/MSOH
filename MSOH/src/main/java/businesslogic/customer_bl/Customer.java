package businesslogic.customer_bl;

import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.customer_blservice.Customer_BLService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;
import util.DataFormat;
import util.MemberType;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pxr on 16/11/16.
 */
public class Customer implements Customer_BLService {
    Customer_DataService_Stub customer_dataService_stub = new Customer_DataService_Stub();

    /**
     * 根据用户id得到用户的信用
     *
     * @param customerID
     * @return
     * @throws RemoteException
     */
    public int getCredit(String customerID) throws RemoteException {
        if (customer_dataService_stub.findCustomerByID(customerID) == null)
            //若不存在该用户，返回-1
            return -1;
        return customer_dataService_stub.findCustomerByID(customerID).getCredit();
    }

    /**
     * 用户注册
     *
     * @param customerVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage signUp(CustomerVO customerVO) throws RemoteException {
        if (customerVO.name.equals("") || customerVO.email.equals("") || customerVO.password.equals("") ||
                customerVO.ID.equals("") || customerVO.phone.equals("")) {
            //若用户未输入名字或邮箱或密码或ID或手机，则返回有信息空白
            return ResultMessage.Blank;
        } else if (customer_dataService_stub.findCustomerByID(customerVO.ID) != null) {
            //若用户输入ID，已存在该用户，返回注册已存在
            return ResultMessage.Customer_SignupExist;
        } else if (customerVO.password.matches(DataFormat.Password_Format) && customerVO.email.matches(DataFormat.Email_Format) &&
                customerVO.phone.matches(DataFormat.Phone_Format) && customerVO.ID.matches(DataFormat.ID_Format)) {
            //若用户已输入ID并且不存在该ID的用户，则添加该用户，并返回注册成功
            if (customer_dataService_stub.addCustomer(new CustomerPO(customerVO.name, customerVO.password,
                    customerVO.phone, customerVO.email, 1000, customerVO.picUrl, customerVO.ID, MemberType.NONMEMBER)))
                return ResultMessage.Customer_SignupSuccess;
            else
                return ResultMessage.Fail;
        } else
            return ResultMessage.DataFormatWrong;
    }

    /**
     * 更改用户信息
     *
     * @param customerVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeInfo(CustomerVO customerVO) throws RemoteException {
        if (customer_dataService_stub.findCustomerByID(customerVO.ID) == null)
            //若找不到该用户
            return ResultMessage.Customer_CustomerNotExist;
        if (customerVO.name.equals("") || customerVO.email.equals("") || customerVO.phone.equals(""))
            //若用户修改时未填写名字，邮箱，手机号，返回有信息空白
            return ResultMessage.Blank;
        if (!customerVO.email.matches(DataFormat.Email_Format) || !customerVO.phone.matches(DataFormat.Phone_Format))
            return ResultMessage.DataFormatWrong;
        //根据ID获取该用户后修改用户的po，返回修改信息成功
        CustomerPO customerPO = customer_dataService_stub.findCustomerByID(customerVO.ID);
        customerPO.setEmail(customerVO.email);
        customerPO.setPhone(customerVO.phone);
        customerPO.setPicture(customerVO.picUrl);
        customerPO.setUserName(customerVO.name);
        if (customer_dataService_stub.modifyCustomer(customerPO))
            return ResultMessage.ChangeInfoSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 根据用户id得到预定过的酒店
     *
     * @param customerID
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> getHistoryHotel(String customerID) throws RemoteException {
        if (customer_dataService_stub.findCustomerByID(customerID) == null)
            //如果找不到该用户
            return null;
        List<HotelVO> listVO = new ArrayList<HotelVO>();
        List<HotelPO> listPO = customer_dataService_stub.getCustomerReservedHotel(customerID);
        if (listPO == null || listPO.isEmpty())
            //如果列表为空
            return null;
        Iterator iterator = listPO.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            HotelPO hotelPO = (HotelPO) object;
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] infra = hotelPO.getInfra().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            HotelUtil hotelUtil = new HotelUtil();
            HotelVO hotelVO = new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(),
                    hotelPO.getIntro(), infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl,
                    hotelPO.getClerkID(), hotelPO.getHotelID(),
                    hotelUtil.getDailyRoomInfo(hotelPO.getHotelID(), new Timestamp(System.currentTimeMillis())), hotelUtil.getComment(hotelPO.getHotelID()));
            listVO.add(hotelVO);
        }
        return listVO;
    }

    public List<CreditRecordVO> getCreditRecord(String customerID) throws RemoteException {
        if (customerID.equals(""))
            return null;
        if (customer_dataService_stub.findCustomerByID(customerID) == null)
            return null;
        List<CreditRecordPO> creditRecordPOList = customer_dataService_stub.findCreditRecordByID(customerID);
        if (creditRecordPOList == null || creditRecordPOList.isEmpty())
            return null;
        List<CreditRecordVO> creditRecordVOList = new ArrayList<CreditRecordVO>();
        for (int i = 0; i < creditRecordPOList.size(); i++) {
            CreditRecordPO creditRecordPO = creditRecordPOList.get(i);
            creditRecordVOList.add(new CreditRecordVO(creditRecordPO.getVariation(), creditRecordPO.getChangeTime(),
                    creditRecordPO.getCustomerName(), creditRecordPO.getCustomerID(), creditRecordPO.getAfterChangeCredit()
                    , creditRecordPO.getOrderID(), creditRecordPO.getMarketerName()));
        }
        return creditRecordVOList;
    }

    public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) throws RemoteException {
        if (customer_dataService_stub.findCustomerByID(ID) == null)
            return ResultMessage.Customer_CustomerNotExist;
        CreditRecordPO creditRecordPO = new CreditRecordPO(creditRecordVO.variation, creditRecordVO.changeTime, creditRecordVO.customerName,
                creditRecordVO.customerID, creditRecordVO.afterChangeCredit, creditRecordVO.orderID, creditRecordVO.marketerName);
        if (customer_dataService_stub.addCreditRecord(creditRecordPO))
            return ResultMessage.Customer_AddCreditRecordSuccess;
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
        } else if (customer_dataService_stub.findCustomerByID(ID) == null) {
            return ResultMessage.Customer_CustomerNotExist;
        } else if (customer_dataService_stub.findCustomerByID(ID).getPassword().equals(oldPassword) && newPassword1.matches
                (DataFormat.Password_Format) && newPassword2.matches(DataFormat.Password_Format)) {
            //若旧密码输入正确，且格式正确
            if (newPassword1.equals(newPassword2)) {
                //如果两次新密码输入相同
                CustomerPO customerPO = customer_dataService_stub.findCustomerByID(ID);
                customerPO.setPassword(newPassword1);
                if (customer_dataService_stub.modifyCustomer(customerPO))
                    return ResultMessage.ChangePasswordSuccess;
                else
                    return ResultMessage.Fail;
            } else {
                //两次新密码输入不同
                return ResultMessage.ChangePassword2DifferentNew;
            }
        } else if (!customer_dataService_stub.findCustomerByID(ID).getPassword().equals(oldPassword)) {
            //旧密码输入错误
            return ResultMessage.ChangePasswordWrongOldPw;
        } else
            return ResultMessage.DataFormatWrong;
    }
}
