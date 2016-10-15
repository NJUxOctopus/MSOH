package businesslogicservice.login_blservice;

import util.ResultMessage;

/**
 * 
 * @author ÷‹«ﬂ∫≠ 2016/10/15
 *
 */
public class Login_BLService_Driver {
	public void drive(Login_BLService login_BLService) {
		ResultMessage result = login_BLService.login("", "123456");
		if (result == ResultMessage.Login_Blank) {
			System.out.println("Info Blank!");
		}

		result = login_BLService.login("000000000000000000", "123456");
		if (result == ResultMessage.Login_Success) {
			System.out.println("Login Success!");
		}

		result = login_BLService.login("000000000000000000", "000000");
		if (result == ResultMessage.Login_WrongPassword) {
			System.out.println("Wrong Password!");
		}
	}

}
