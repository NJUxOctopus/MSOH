package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

public class Member_BLService_Driver {
	public void drive(Member_BLService member_BLService){
		MemberLevelVO memberLevelVO = new MemberLevelVO();
		MemberVO memberVO = new MemberVO();
		CustomerVO customerVO = new CustomerVO();
		ResultMessage result = member_BLService.signUp(memberLevelVO, memberVO, customerVO);
		if(result.equals(ResultMessage.Blank))
			System.out.println("Member's information is blank");
		if(result.equals(ResultMessage.Member_NormalSignupSuccess));
			System.out.println("Normal member signs up successfully");
		if(result.equals(ResultMessage.Member_EnterpriceSignupSuccess))
			System.out.println("Enterprice member signs up successfully");
		if(result.equals(ResultMessage.Member_SignupCreditNotEnough))
			System.out.println("Sorry,your credit is not enough");
		member_BLService.deGrade(memberLevelVO, memberVO, customerVO);
		member_BLService.upGrade(memberLevelVO, memberVO, customerVO);
		memberVO=member_BLService.getSingle("3202XXXXXXXXXXXXXX");
	}
}
