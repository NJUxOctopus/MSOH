package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

/**
 * @author Pxr created:2016.10.14 latest modify:2016.10.15
 */
public class Member_BLService_Stub implements Member_BLService {
	// ��Ա�ȼ���Ϣ
	MemberLevelVO memberLevelVO;
	// �û���Ϣ
	CustomerVO customerVO;
	// ��Ա��Ϣ
	MemberVO memberVO;

	/**
	 * ��Աע��
	 */
	public ResultMessage signUp( MemberVO memberVO, String customerID) {
		// ���û�����ֵ�������ý��ޣ������ע���Ա����ѡ���Ա����
		if (customerVO.credit >= memberLevelVO.creditBoundaries[1]) {
			// ���û�δѡ���Ա���ͣ���������Ϣ�հ�
			if (memberVO.memberType == null) {
				return ResultMessage.Blank;
			}
			// ���û�ѡ���Ϊ��ͨ��Ա
			else if (memberVO.memberType.equals("NORMAL")) {
				// ���û�δ��д���գ���������Ϣ�հ�
				if (memberVO.birthday == null) {
					return ResultMessage.Blank;
				}
				// ���û�������дΪ12.25���򷵻�ע��ɹ�
				else if (memberVO.birthday.equals(12.25)) {
					return ResultMessage.Member_NormalSignupSuccess;
				}
			}
			// ���û�ѡ���Ϊ��ҵ��Ա
			else if (memberVO.memberType.equals("ENTERPRICE")) {
				// ���û�δ��д��ҵ������������Ϣ�հ�
				if (memberVO.companyName == null) {
					return ResultMessage.Blank;
				}
				// ���û���ҵ����дΪ����Ƥ�ﳧ���򷵻�ע��ɹ�
				else if (memberVO.companyName.equals("����Ƥ�ﳧ")) {
					return ResultMessage.Member_EnterpriseSignupSuccess;
				}
			}
		} else {
			return ResultMessage.Member_SignupCreditNotEnough;
		}
		return null;
	}


}
