package businesslogicservice.member_blservice;

import util.ResultMessage;

public interface Member_BLService {

	public ResultMessage signUp(MemberLevelVO MemberLevelvo, MemberVO Membervo, CustomerVO Customervo);

	public void Upgrade(MemberLevelVO MemberLevelvo, MemberVO Membervo, CustomerVO Customervo);

	public void Degrade(MemberLevelVO MemberLevelvo, MemberVO Membervo, CustomerVO Customervo);

	public MemberVO getSingle(String ID);
}
