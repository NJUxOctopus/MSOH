package businesslogicservice.marketer_blservice;

import java.util.ArrayList;
import java.util.List;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MarketerVO;

/**
 * 
 * @author ST 2016/10/16
 *
 */

public class Marketer_BLService_Stub implements Marketer_BLService {

	MarketerVO marketerVO;
	List<MarketerVO> marketer = new ArrayList<MarketerVO>();

	/**
	 * 网站营销人员维护个人信息
	 */
	@Override
	public ResultMessage changeInfo(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.name.equals("") || vo.phone.equals("")) {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else {
			return ResultMessage.Marketer_ChangeInfoSuccess;
		}

	}

	/**
	 * 网站营销人员修改密码
	 */
	@Override
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// 找不到对应的营销人员
			return ResultMessage.Marketer_MarketerNotExist;
		} else if (!oldPw.equals(marketerVO.password)) {
			// 旧密码错误
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (oldPw.equals(marketerVO.password) && !newPw1.equals(newPw2)) {
			// 两次新密码不一致
			return ResultMessage.ChangePassword2DifferentNew;
		} else {
			// 修改密码成功
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	/**
	 * 通过ID返回对应的网站营销人员
	 */
	@Override
	public MarketerVO getSingleByID(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// 找不到对应的营销人员
			return null;
		} else {
			return marketerVO;
		}
	}

	/**
	 * 通过名字返回对应的网站营销人员
	 */
	@Override
	public MarketerVO getSingleByName(String name) {
		// TODO Auto-generated method stub
		if (name.equals("Admin")) {
			// 找不到对应的营销人员
			return null;
		} else {
			return marketerVO;
		}
	}

	/**
	 * 返回所有营销人员
	 */
	@Override
	public List<MarketerVO> getAll() {
		// TODO Auto-generated method stub
		return marketer;
	}

	/**
	 * 网站营销人员充值信用
	 */
	@Override
	public ResultMessage CreditCharge(String ID, int credit, CustomerVO vo) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// 找不到对应的客户
			return ResultMessage.Customer_CustomerNotExist;
		} else {
			// 充值成功
			vo.credit += credit;
			return ResultMessage.Marketer_CreditChargeSuccess;
		}
	}

}
