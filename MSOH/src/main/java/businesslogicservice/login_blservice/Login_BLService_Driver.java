package businesslogicservice.login_blservice;

import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * 
 * @author st
 *
 */
public class Login_BLService_Driver {
	public void drive(Login_BLService login_BLService) throws RemoteException{
		ResultMessage result = login_BLService.login("", "123456");
		if (result == ResultMessage.Blank) {
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
