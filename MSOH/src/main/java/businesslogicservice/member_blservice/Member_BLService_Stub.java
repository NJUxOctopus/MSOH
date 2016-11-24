package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

/**
 * @author Pxr created:2016.10.14 latest modify:2016.10.15
 */
public class Member_BLService_Stub implements Member_BLService {
	MemberLevelVO memberLevelVO;
	CustomerVO customerVO;
	MemberVO memberVO;

	public ResultMessage signUp( MemberVO memberVO, String customerID) {

		if (customerVO.credit >= memberLevelVO.creditBoundaries[1]) {

			if (memberVO.memberType == null) {
				return ResultMessage.Blank;
			}

			else if (memberVO.memberType.equals("NORMAL")) {

				if (memberVO.birthday == null) {
					return ResultMessage.Blank;
				}

				else if (memberVO.birthday.equals(12.25)) {
					return ResultMessage.Member_NormalSignupSuccess;
				}
			}

			else if (memberVO.memberType.equals("ENTERPRICE")) {

				if (memberVO.companyName == null) {
					return ResultMessage.Blank;
				}
				else if (memberVO.companyName.equals("")) {
					return ResultMessage.Member_EnterpriseSignupSuccess;
				}
			}
		} else {
			return ResultMessage.Member_SignupCreditNotEnough;
		}
		return null;
	}


}
