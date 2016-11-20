package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

import java.rmi.RemoteException;

public class Member_BLService_Driver {
	public void drive(Member_BLService member_BLService)throws RemoteException {
		MemberLevelVO memberLevelVO = new MemberLevelVO();
		MemberVO memberVO = new MemberVO();
		CustomerVO customerVO = new CustomerVO();
		ResultMessage result = member_BLService.signUp(memberVO,"123");
		if(result.equals(ResultMessage.Blank))
			System.out.println("Member's information is blank");
		if(result.equals(ResultMessage.Member_NormalSignupSuccess));
			System.out.println("Normal member signs up successfully");
		if(result.equals(ResultMessage.Member_EnterpriseSignupSuccess))
			System.out.println("Enterprise member signs up successfully");
	}
}
