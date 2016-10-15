package businesslogicservice.clerk_blservice;

import java.util.List;
import util.ResultMessage;

public interface Clerk_BLService {

	public ResultMessage signUp(ClerkVo vo);

	public ClerkVO getSingleByID(String ID);

	public ClerkVO getSingleByName(String name);

	public List<ClerkVO> getAll();

}
