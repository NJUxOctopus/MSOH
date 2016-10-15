package businesslogicservice.member_blservice;

import util.ResultMessage;

public interface Member_BLService {

	public ResultMessage signUp(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO);

	public void Upgrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO);

	public void Degrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO);

	public MemberVO getSingle(String ID);
}
