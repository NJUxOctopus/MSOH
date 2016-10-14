package businesslogicservice.login_blservice;

import util.ResultMessage;

public interface Login_BLService {

	public ResultMessage login(String ID, String password);

}
