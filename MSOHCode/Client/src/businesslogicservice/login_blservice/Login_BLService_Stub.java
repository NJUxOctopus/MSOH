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
			// ��½�ɹ�
			return ResultMessage.Login_Success;
		} else if (ID == "" || password == "") {
			// �û���������δ��д
			return ResultMessage.Blank;
		} else if (ID.equals("000000000000000000") && password.equals("000000")) {
			// �������
			return ResultMessage.Login_WrongPassword;
		} else {
			// �û���������
			return ResultMessage.Login_NoUser;
		}
	}

}
