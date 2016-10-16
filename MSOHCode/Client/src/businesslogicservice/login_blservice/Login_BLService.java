package businesslogicservice.login_blservice;

import util.ResultMessage;

/**
 * 
 * @author ST 2016/10/14
 *
 */

public interface Login_BLService {

	public ResultMessage login(String ID, String password);

}
