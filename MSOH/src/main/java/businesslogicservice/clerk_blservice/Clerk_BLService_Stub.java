package businesslogicservice.clerk_blservice;

import java.rmi.RemoteException;
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

	public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException {
		return null;
	}

	public ResultMessage deleteClerk(ClerkVO clerkVO) throws RemoteException {
		return null;
	}

	public ClerkVO getSingleByID(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("000000000000000000")) {
			return clerkVO;
		} else {
			return null;
		}
	}

	public ResultMessage changeInfo(ClerkVO clerkVO) {
		if (clerkVO.phone == "" || clerkVO.name == "") {
			// ����Ϣδ��д
			return ResultMessage.Blank;
		} else if (clerkVO.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else {
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		if (!oldPw.equals(clerkVO.password)) {
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (ID.equals("320581190001012016")) {
			return ResultMessage.Clerk_ClerkNotExist;
		} else if (oldPw.equals(clerkVO.password) && !newPw1.equals(newPw2)) {
			return ResultMessage.ChangePassword2DifferentNew;
		} else {
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	public ClerkVO getSingleByName(String name) {
		// TODO Auto-generated method stub
		if (name.equals("Admin")) {
			return clerkVO;
		} else {
			return null;
		}

	}

	public List<ClerkVO> getAll() {
		// TODO Auto-generated method stub
		List<ClerkVO> clerk = new ArrayList<ClerkVO>();
		return clerk;
	}

}
