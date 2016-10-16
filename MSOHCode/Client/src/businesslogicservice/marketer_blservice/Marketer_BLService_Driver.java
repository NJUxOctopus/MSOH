package businesslogicservice.marketer_blservice;

import java.util.ArrayList;
import java.util.List;

import util.ResultMessage;
import vo.MarketerVO;

/**
 * 
 * @author ST 2016/10/16
 *
 */

public class Marketer_BLService_Driver {

	MarketerVO marketerVO = new MarketerVO();
	CustomerVO customerVO = new CustomerVO();
	List<MarketerVO> marketer = new ArrayList<MarketerVO>();

	public void driver(Marketer_BLService marketer_BLService) {

		ResultMessage result1 = marketer_BLService.changeInfo(marketerVO);
		if (result1 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result1 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result1 == ResultMessage.ChangeInfoSuccess)
			System.out.println("Change Information Success!");

		ResultMessage result2 = marketer_BLService.changePassword("320581190001012016", "12345678", "23456789",
				"23456789");
		if (result2 == ResultMessage.Marketer_MarketerNotExist)
			System.out.println("Marketer Not Exist!");
		else if (result2 == ResultMessage.ChangePassword2DifferentNew)
			System.out.println("New Password don't match!");
		else if (result2 == ResultMessage.ChangePasswordWrongOldPw)
			System.out.println("Wrong old password!");
		else if (result2 == ResultMessage.ChangePasswordSuccess)
			System.out.println("Change Password Success!");

		marketerVO = marketer_BLService.getSingleByID("320581190001012016");
		if (marketerVO == null)
			System.out.println("Marketer Not Exist!");
		else
			System.out.println("Success!");

		marketerVO = marketer_BLService.getSingleByname("Admin");
		if (marketerVO == null)
			System.out.println("Marketer Not Exist!");
		else
			System.out.println("Success!");

		marketer = marketer_BLService.getAll();
		if (marketer == null)
			System.out.println("Marketer Not Exist!");
		else
			System.out.println("Success!");

		ResultMessage result3 = marketer_BLService.CreditCharge("320581190001012016", 500, customerVO);
		if (result3 == ResultMessage.Customer_CustomerNotExist)
			System.out.println("Customer Not Exist!");
		else if (result3 == ResultMessage.Marketer_CreditChargeSuccess)
			System.out.println("Credit Charge Success!");
	}

}
