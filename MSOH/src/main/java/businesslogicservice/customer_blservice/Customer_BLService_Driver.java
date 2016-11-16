package businesslogicservice.customer_blservice;

import java.rmi.RemoteException;
import java.util.List;

import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;

/**
 * @author ≈À‰ÏÓ£
 */
public class Customer_BLService_Driver {
    public void drive(Customer_BLService customer_BLService) throws RemoteException{
        CustomerVO customerVO = new CustomerVO();
        ResultMessage result1 = customer_BLService.signUp(customerVO);
        if (result1.equals(ResultMessage.Customer_SignupExist))
            System.out.println("ID already exsits");
        if (result1.equals(ResultMessage.Blank))
            System.out.println("Information is blank");
        if (result1.equals(ResultMessage.WrongPhoneFormat))
            System.out.println("Phone format is wrong");
        if (result1.equals(ResultMessage.WrongIDFormat))
            System.out.println("ID format is wrong");
        if (result1.equals(ResultMessage.WrongEmailFormat))
            System.out.println("Email format is wrong");
        if (result1.equals(ResultMessage.Customer_SignupSuccess))
            System.out.println("Sign up successfully");
        ResultMessage result2 = customer_BLService.changeInfo(customerVO);
        if (result2.equals(ResultMessage.Blank))
            System.out.println("Information is blank");
        if (result2.equals(ResultMessage.WrongPhoneFormat))
            System.out.println("Phone format is wrong");
        if (result2.equals(ResultMessage.WrongEmailFormat))
            System.out.println("Email format is wrong");
        if (result2.equals(ResultMessage.ChangeInfoSuccess))
            System.out.println("Change information successfully");
        ResultMessage result3 = customer_BLService.changePassword("320581190001012016", "12345678", "23456789",
                "23456789");
        if (result3 == ResultMessage.Customer_CustomerNotExist)
            System.out.println("Customer Not Exist!");
        if (result3 == ResultMessage.ChangePasswordWrongOldPw)
            System.out.println("Wrong old password!");

        if (customerVO == null) {
            System.out.println("Not exsit such customer");
        } else {
            System.out.println("get successfullu");
        }
        CreditRecordVO creditRecordVO = customer_BLService.getCreditRecord(customerVO);
        List<HotelVO> list2 = customer_BLService.getHistoryHotel(customerVO);

    }

}
