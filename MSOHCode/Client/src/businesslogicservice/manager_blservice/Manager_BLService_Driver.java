package businesslogicservice.manager_blservice;

import util.ResultMessage;
import vo.ClerkVO;
import vo.CustomerVO;
import vo.HotelVO;
import vo.ManagerVO;
import vo.MarketerVO;

/**
 * 
 * @author ST 2016/10/16
 *
 */
public class Manager_BLService_Driver {

	public void driver(Manager_BLService manager_BLService) {

		ManagerVO managerVO = new ManagerVO();
		CustomerVO customerVO = new CustomerVO();
		ClerkVO clerkVO = new ClerkVO();
		MarketerVO marketerVO = new MarketerVO();
		HotelVO hotelVO = new HotelVO();

		ResultMessage result1 = manager_BLService.changeInfo(managerVO);
		if (result1 == ResultMessage.Blank)
			System.out.println("Infomation Blank!");
		else
			System.out.println("Change Infomation Success!");

		ResultMessage result2 = manager_BLService.changePassword("320581190001012016", "12345678", "23456789",
				"23456789");
		if (result2 == ResultMessage.Manager_ManagerNotExist)
			System.out.println("Manager Not Exist!");
		else if (result2 == ResultMessage.ChangePassword2DifferentNew)
			System.out.println("New Password don't match!");
		else if (result2 == ResultMessage.ChangePasswordWrongOldPw)
			System.out.println("Wrong old password!");
		else if (result2 == ResultMessage.ChangePasswordSuccess)
			System.out.println("Change Password Success!");

		ResultMessage result3 = manager_BLService.addCustomer(customerVO);
		if (result3 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result3 == ResultMessage.WrongEmailFormat)
			System.out.println("Wrong Email Format!");
		else if (result3 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result3 == ResultMessage.WrongIDFormat)
			System.out.println("Wrong ID Format!");
		else if (result3 == ResultMessage.Manager_AddCustomerAlreadyExist)
			System.out.println("Customer Already Exist!");
		else if (result3 == ResultMessage.Manager_AddCustomerSuccess)
			System.out.println("Add Customer Success!");

		ResultMessage result4 = manager_BLService.changeCustomerInfo(customerVO);
		if (result4 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result4 == ResultMessage.WrongEmailFormat)
			System.out.println("Wrong Email Format!");
		else if (result4 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result4 == ResultMessage.Manager_ChangeCustomerInfoSuccess)
			System.out.println("Change Customer Information Success!");

		ResultMessage result5 = manager_BLService.addClerk(clerkVO);
		if (result5 == ResultMessage.Hotel_HotelNotExist)
			System.out.println("Hotel Not Exist!");
		else if (result5 == ResultMessage.Manager_AddClerkAlreadyExist)
			System.out.println("Clerk Already Exist!");
		else if (result5 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result5 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result5 == ResultMessage.WrongIDFormat)
			System.out.println("Wrong ID Format!");
		else if (result5 == ResultMessage.Manager_AddClerkSuccess)
			System.out.println("Add Clerk Success!");

		ResultMessage result6 = manager_BLService.deleteClerk(clerkVO);
		if (result6 == ResultMessage.Clerk_ClerkNotExist)
			System.out.println("Clerk Not Exist!");
		else if (result6 == ResultMessage.Manager_DeleteClerkSuccess)
			System.out.println("Delete Clerk Suceess!");

		ResultMessage result7 = manager_BLService.changeClerkInfo(clerkVO);
		if (result7 == ResultMessage.Clerk_ClerkNotExist)
			System.out.println("Clerk Not Exist!");
		else if (result7 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result7 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result7 == ResultMessage.Manager_ChangeClerkInfoSuccess)
			System.out.println("Change Clerk Infomation Success!");

		ResultMessage result8 = manager_BLService.addMarketer(marketerVO);
		if (result8 == ResultMessage.Marketer_MarketerAlreadyExist)
			System.out.println("Marketer Already Exist!");
		else if (result8 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result8 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result8 == ResultMessage.WrongIDFormat)
			System.out.println("Wrong ID Format!");
		else if (result8 == ResultMessage.Manager_AddMarketerSuccess)
			System.out.println("Add Marketer Success!");

		ResultMessage result9 = manager_BLService.deleteMarketer(marketerVO);
		if (result9 == ResultMessage.Marketer_MarketerNotExist)
			System.out.println("Marketer Not Exist!");
		else if (result9 == ResultMessage.Manager_DeleteMarketerSuccess)
			System.out.println("Delete Marketer Success!");

		ResultMessage result10 = manager_BLService.changeMarketerInfo(marketerVO);
		if (result10 == ResultMessage.Marketer_MarketerNotExist)
			System.out.println("Marketer Already Exist!");
		else if (result10 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result10 == ResultMessage.WrongPhoneFormat)
			System.out.println("Wrong Phone Format!");
		else if (result10 == ResultMessage.Manager_ChangeMarketerInfoSuccess)
			System.out.println("Change Marketer Information Success!");

		ResultMessage result11 = manager_BLService.addHotel(hotelVO);
		if (result11 == ResultMessage.Hotel_HotelAlreadyExist)
			System.out.println("Hotel Already Exist!");
		else if (result11 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result11 == ResultMessage.WrongHotelIDFormat)
			System.out.println("Wrong Hotel ID Format!");
		else if (result11 == ResultMessage.Manager_AddHotelSuccess)
			System.out.println("Add Hotel Success!");

		ResultMessage result12 = manager_BLService.deleteHotel(hotelVO);
		if (result12 == ResultMessage.Hotel_HotelNotExist)
			System.out.println("Hotel Not Exist!");
		else if (result12 == ResultMessage.Manager_DeleteHotelSuccess)
			System.out.println("Delete Hotel Success!");

		ResultMessage result13 = manager_BLService.changeHotelInfo(hotelVO);
		if (result13 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result13 == ResultMessage.Hotel_HotelNotExist)
			System.out.println("Hotel Not Exist!");
		else if (result13 == ResultMessage.WrongHotelIDFormat)
			System.out.println("Wrong Hotel ID Format!");
		else if (result13 == ResultMessage.Manager_ChangeHotelInfoSuccess)
			System.out.println("Change Hotel Information Success!");

	}

}
