package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

public interface Member_BLService {

	public ResultMessage signUp(MemberVO memberVO, CustomerVO customerVO);

}
