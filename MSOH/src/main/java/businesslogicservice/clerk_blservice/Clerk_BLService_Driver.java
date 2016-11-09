package businesslogicservice.clerk_blservice;

import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import util.ResultMessage;
import vo.ClerkVO;

/**
 * 
 * @author ST 2016/10/16
 *
 */

public class Clerk_BLService_Driver {

	ClerkVO clerkVO = new ClerkVO();
	List<ClerkVO> clerk = new ArrayList<ClerkVO>();

	public void drive(Clerk_BLService clerk_BLService) {

		ResultMessage result1 = clerk_BLService.changeInfo(clerkVO);
		if (result1 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result1 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result1 == ResultMessage.ChangeInfoSuccess)
			System.out.println("Update Infomation Success!");

		ResultMessage result2 = clerk_BLService.changePassword("320581190001012016", "12345678", "23456789",
				"23456789");
		if (result2 == ResultMessage.Clerk_ClerkNotExist)
			System.out.println("Clerk Not Exist!");
		else if (result2 == ResultMessage.ChangePassword2DifferentNew)
			System.out.println("New Password don't match!");
		else if (result2 == ResultMessage.ChangePasswordWrongOldPw)
			System.out.println("Wrong old password!");
		else if (result2 == ResultMessage.ChangePasswordSuccess)
			System.out.println("Change Password Success!");

		clerkVO = clerk_BLService.getSingleByID("320581190001012016");
		if (clerkVO == null)
			System.out.println("Find Fail!");
		else
			System.out.println("Success");

		clerkVO = clerk_BLService.getSingleByName("Admin");
		if (clerkVO == null)
			System.out.println("Find Fail!");
		else
			System.out.println("Success");

		clerk = clerk_BLService.getAll();
		if (clerk == null)
			System.out.println("Find Fail!");
		else
			System.out.println("Success");

	}

}
