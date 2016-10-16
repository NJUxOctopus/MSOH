package businesslogicservice.clerk_blservice;

import java.util.List;
import util.ResultMessage;

/**
 * 
 * @author ST 2016/10/15
 *
 */
public interface Clerk_BLService {

	public ResultMessage changeInfo(ClerkVO clerkVO);

	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2);

	public ClerkVO getSingleByID(String ID);

	public ClerkVO getSingleByName(String name);

	public List<ClerkVO> getAll();

}
