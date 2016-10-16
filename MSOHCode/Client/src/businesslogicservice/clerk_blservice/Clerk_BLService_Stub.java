package businesslogicservice.clerk_blservice;

import java.util.ArrayList;
import java.util.List;

import util.ResultMessage;
import vo.ClerkVO;

/**
 * 
 * @author ST 2016/10/15
 *
 */

public class Clerk_BLService_Stub implements Clerk_BLService {

	ClerkVO clerkVO;

	/**
	 * ͨ��ID���ҾƵ깤����Ա
	 */
	@Override
	public ClerkVO getSingleByID(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("000000000000000000")) {
			return clerkVO;
		} else {
			// �Ҳ�����Ӧ�Ƶ깤����Ա
			return null;
		}
	}

	/**
	 * �Ƶ깤����Աά��������Ϣ
	 */
	public ResultMessage changeInfo(ClerkVO clerkVO) {
		if (clerkVO.phone == "" || clerkVO.name == "") {
			// ����Ϣδ��д
			return ResultMessage.Blank;
		} else if (clerkVO.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else {
			// �޸���Ϣ�ɹ�
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	/**
	 * �Ƶ깤����Ա�޸�����
	 */
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		if (!oldPw.equals(clerkVO.password)) {
			// ���������
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ������Ա
			return ResultMessage.ChangePasswordNotExist;
		} else if (oldPw.equals(clerkVO.password) && !newPw1.equals(newPw2)) {
			// ���������벻һ��
			return ResultMessage.ChangePassword2DifferentNew;
		} else {
			// �޸�����ɹ�
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	/**
	 * ͨ���û������ҾƵ깤����Ա
	 */
	@Override
	public ClerkVO getSingleByName(String name) {
		// TODO Auto-generated method stub
		if (name.equals("Admin")) {
			return clerkVO;
		} else {
			// �Ҳ�����Ӧ�Ƶ깤����Ա
			return null;
		}

	}

	/**
	 * �������еľƵ깤����Ա�б�
	 */
	@Override
	public List<ClerkVO> getAll() {
		// TODO Auto-generated method stub
		List<ClerkVO> clerk = new ArrayList<ClerkVO>();
		return clerk;
	}

}
