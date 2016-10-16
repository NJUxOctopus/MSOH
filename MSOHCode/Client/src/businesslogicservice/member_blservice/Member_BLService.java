package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

public interface Member_BLService {

	public ResultMessage signUp(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO);

	public void upGrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO);

	public void deGrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO);

	public MemberVO getSingle(String ID);
}
