package businesslogicservice.customer_blservice;

import java.util.ArrayList;
import java.util.List;

import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;

/**
 * 
 * @author ST 2016/10/15
 *
 */

public class Customer_BLService_Stub implements Customer_BLService {

	CustomerVO customerVO;
	CreditRecordVO creditRecordVO;
	HotelVO hotelVO;
	List<HotelVO> list;

	/**
	 * 客户注册
	 */
	public ResultMessage signUp(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("000000000000000000")) {
			// 该身份证号已存在
			return ResultMessage.Customer_SignupExist;
		} else if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.name == "") {
			// 有某项未填写
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// 手机号码格式错误
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			// 身份证号格式错误
			return ResultMessage.WrongIDFormat;
		} else if (!vo.email.contains("@")) {
			// 邮箱格式错误
			return ResultMessage.WrongEmailFormat;
		} else {
			// 注册成功
			return ResultMessage.Customer_SignupSuccess;
		}
	}

	/**
	 * 客户维护个人信息
	 */
	public ResultMessage changeInfo(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.name == "") {
			// 有某项未填写
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// 手机号码格式错误
			return ResultMessage.WrongPhoneFormat;
		} else if (!vo.email.contains("@")) {
			// 邮箱格式错误
			return ResultMessage.WrongEmailFormat;
		} else {
			// 修改成功
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	/**
	 * 客户修改密码
	 */
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		if (!oldPw.equals(customerVO.password)) {
			// 旧密码错误
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (oldPw.equals(customerVO.password) && !newPw1.equals(newPw2)) {
			// 两次新密码不一致
			return ResultMessage.ChangePassword2DifferentNew;
		} else

		{
			// 修改密码成功
			return ResultMessage.ChangePasswordSuccess;
		}
	}



	/**
	 * 查看客户信用记录
	 */
	public CreditRecordVO getCreditRecord(String ID) {
		// TODO Auto-generated method stub
		return creditRecordVO;
	}

	/**
	 * 返回预订过的酒店
	 */
	public List<HotelVO> getHistoryHotel(String ID) {
		// TODO Auto-generated method stub
		return list;
	}

	public double getCredit(String ID) {
		return 0;
	}

	public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) {
		return null;
	}

}
