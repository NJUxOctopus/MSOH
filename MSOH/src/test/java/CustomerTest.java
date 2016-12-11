
import businesslogic.customer_bl.Customer;
import junit.framework.TestCase;
import po.HotelPO;
import util.MemberType;
import util.ResultMessage;
import vo.CommentVO;
import vo.CustomerVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/11/19.
 */
public class CustomerTest extends TestCase {
//    Customer customer = new Customer();
//
//    public void testGetCredit() throws RemoteException {
//        int credit1 = customer.getCredit("320200000000000000");
//        int credit2 = customer.getCredit("123");
//        assertEquals(credit1, 10);
//        assertEquals(credit2, -1);
//    }
//
//    public void testSignUp() throws RemoteException{
//        ResultMessage resultMessage1 = customer.signUp(new CustomerVO("pxr", "123456", "12345678910", "123@qq.com", 0, null,
//                "320200000000000000", MemberType.NONMEMBER));
//        ResultMessage resultMessage2 = customer.signUp(new CustomerVO("pxr", "123456", "12345678910", "123@qq.com", 0, null,
//                "320200000000000001", MemberType.NONMEMBER));
//        ResultMessage resultMessage3 = customer.signUp(new CustomerVO("", "123456", "12345678910", "123@qq.com", 0, null,
//                "320200000000000000", MemberType.NONMEMBER));
//        ResultMessage resultMessage4 = customer.signUp(new CustomerVO("pxr", "1234", "12345678910", "123@qq.com", 0, null,
//                "320200000000000001", MemberType.NONMEMBER));
//        ResultMessage resultMessage5 = customer.signUp(new CustomerVO("pxr", "123456", "12345678910", "123qq.com", 0, null,
//                "320200000000000001", MemberType.NONMEMBER));
//        assertEquals(resultMessage1,ResultMessage.Customer_SignupExist);
//        assertEquals(resultMessage2,ResultMessage.Customer_SignupSuccess);
//        assertEquals(resultMessage3,ResultMessage.Blank);
//        assertEquals(resultMessage4,ResultMessage.DataFormatWrong);
//        assertEquals(resultMessage5,ResultMessage.DataFormatWrong);
//
//    }
//
//    public void testChangeInfo()throws RemoteException{
//        ResultMessage resultMessage1 = customer.changeInfo(new CustomerVO("pxr", "123456", "12345678910", "123@qq.com", 0, null,
//                "320200000000000000", MemberType.NONMEMBER));
//        ResultMessage resultMessage2 = customer.changeInfo(new CustomerVO("", "123456", "12345678910", "123@qq.com", 0, null,
//                "320200000000000000", MemberType.NONMEMBER));
//        assertEquals(resultMessage1,ResultMessage.ChangeInfoSuccess);
//        assertEquals(resultMessage2,ResultMessage.Blank);
//    }
//
//    public void testGetHistoryHotel()throws RemoteException{
//        List<HotelVO> hotelVOList = customer.getHistoryHotel("320200000000000000");
//        List<HotelVO> hotelVOList1 = new ArrayList<HotelVO>();
//        hotelVOList1.add(new HotelVO("RUJIA", "NJU", "XIANLIN", "wu",null, null, 5,
//                5, "has",null, "pxr", "123", null, null));
//        List<HotelVO> hotelVOList2 = customer.getHistoryHotel("123");
//        assertEquals(hotelVOList.get(0).hotelID,hotelVOList1.get(0).hotelID);
//        assertEquals(hotelVOList2,null);
//    }
//
    public void testChangePassword()throws RemoteException{
//        ResultMessage resultMessage1 = customer.changePassword("320200000000000000","123456","1234567","1234567");
//        ResultMessage resultMessage2 = customer.changePassword("320200000000000000","12345","1234567","1234567");
//        ResultMessage resultMessage3 = customer.changePassword("320200000000000000","123456","1234567","123456");
//        ResultMessage resultMessage4 = customer.changePassword("","123456","1234567","1234567");
//        ResultMessage resultMessage5 = customer.changePassword("320200000000000001","123456","1234567","1234567");
//
//        assertEquals(resultMessage1,ResultMessage.ChangePasswordSuccess);
//        assertEquals(resultMessage2,ResultMessage.ChangePasswordWrongOldPw);
//        assertEquals(resultMessage3,ResultMessage.ChangePassword2DifferentNew);
//        assertEquals(resultMessage4,ResultMessage.Blank);
//        assertEquals(resultMessage5,ResultMessage.Customer_CustomerNotExist);
    }

}
