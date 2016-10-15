package businesslogicservice.login_blservice;

import util.ResultMessage;

/**
 * 
 * @author ST 2016/10/14
 *
 */
public class Login_BLService_Stub implements Login_BLService {

	// String ID;
	// String password;
	//
	// public Login_BLService_Stub(String administrator, String pw) {
	// ID = administrator;
	// password = pw;
	// }

	@Override
	public ResultMessage login(String ID, String password) {
		// TODO Auto-generated method stub
		if (ID.equals("000000000000000000") && password.equals("123456")) {
			// 登陆成功
			return ResultMessage.Login_Success;
		} else if (ID == null && password == null) {
			// 用户名或密码未填写
			return ResultMessage.Login_Blank;
		} else if (ID.equals("000000000000000000") && password.equals("000000")) {
			// 密码错误
			return ResultMessage.Login_WrongPassword;
		} else {
			// 用户名不存在
			return ResultMessage.Login_NoUser;
		}
	}

}
