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

	/**
	 * 通过ID查找酒店工作人员
	 */
	public ClerkVO getSingleByID(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("000000000000000000")) {
			return clerkVO;
		} else {
			// 找不到对应酒店工作人员
			return null;
		}
	}

	/**
	 * 酒店工作人员维护个人信息
	 */
	public ResultMessage changeInfo(ClerkVO clerkVO) {
		if (clerkVO.phone == "" || clerkVO.name == "") {
			// 有信息未填写
			return ResultMessage.Blank;
		} else if (clerkVO.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else {
			// 修改信息成功
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	/**
	 * 酒店工作人员修改密码
	 */
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		if (!oldPw.equals(clerkVO.password)) {
			// 旧密码错误
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (ID.equals("320581190001012016")) {
			// 找不到对应工作人员
			return ResultMessage.Clerk_ClerkNotExist;
		} else if (oldPw.equals(clerkVO.password) && !newPw1.equals(newPw2)) {
			// 两次新密码不一致
			return ResultMessage.ChangePassword2DifferentNew;
		} else {
			// 修改密码成功
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	/**
	 * 通过用户名查找酒店工作人员
	 */
	public ClerkVO getSingleByName(String name) {
		// TODO Auto-generated method stub
		if (name.equals("Admin")) {
			return clerkVO;
		} else {
			// 找不到对应酒店工作人员
			return null;
		}

	}

	/**
	 * 返回所有的酒店工作人员列表
	 */
	public List<ClerkVO> getAll() {
		// TODO Auto-generated method stub
		List<ClerkVO> clerk = new ArrayList<ClerkVO>();
		return clerk;
	}

}
