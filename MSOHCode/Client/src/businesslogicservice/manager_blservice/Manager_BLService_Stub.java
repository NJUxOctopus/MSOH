package businesslogicservice.manager_blservice;

import util.ResultMessage;
import vo.ClerkVO;
import vo.CustomerVO;
import vo.HotelVO;
import vo.ManagerVO;
import vo.MarketerVO;

/**
 * 
 * @author ST 2016/10/15
 *
 */

public class Manager_BLService_Stub implements Manager_BLService {

	ManagerVO managerVO;

	/**
	 * 网站管理人员维护个人信息
	 */
	@Override
	public ResultMessage changeInfo(ManagerVO vo) {
		// TODO Auto-generated method stub
		if (vo.phone == "") {
			// 有信息未填写
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else {
			// 修改信息成功
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	/**
	 * 网站管理人员修改密码
	 */
	@Override
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		// TODO Auto-generated method stub
		if (!oldPw.equals(managerVO.password)) {
			// 旧密码错误
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (ID.equals("320581190001012016")) {
			// 找不到对应工作人员
			return ResultMessage.Manager_ManagerNotExist;
		} else if (oldPw.equals(managerVO.password) && !newPw1.equals(newPw2)) {
			// 两次新密码不一致
			return ResultMessage.ChangePassword2DifferentNew;
		} else {
			// 修改密码成功
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	/**
	 * 网站管理人员添加客户
	 */
	@Override
	public ResultMessage addCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("320581190001012016")) {
			// 已存在该客户
			return ResultMessage.Manager_AddCustomerAlreadyExist;
		} else if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.userName == "") {
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
			// 添加成功
			return ResultMessage.Manager_AddCustomerSuccess;
		}

	}

	/**
	 * 网站管理人员维护客户个人信息
	 */
	@Override
	public ResultMessage changeCustomerInfo(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.userName == "") {
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
			return ResultMessage.Manager_ChangeCustomerInfoSuccess;
		}
	}

	/**
	 * 网站管理人员添加酒店工作人员
	 */
	@Override
	public ResultMessage addClerk(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID.equals("123456")) {
			// 没有对应酒店
			return ResultMessage.Hotel_HotelNotExist;
		} else if (vo.hotelID.equals("234567")) {
			// 该酒店已存在工作人员
			return ResultMessage.Manager_AddClerkAlreadyExist;
		} else if (vo.hotelID == "" || vo.hotelName == "" || vo.ID == "" || vo.name == "" || vo.password == ""
				|| vo.phone == "" || vo.position == null) {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// 手机号码格式错误
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			// 身份证号格式错误
			return ResultMessage.WrongIDFormat;
		} else {
			return ResultMessage.Manager_AddClerkSuccess;
		}
	}

	/**
	 * 网站管理人员删除酒店工作人员
	 */
	@Override
	public ResultMessage deleteClerk(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "320581190001012016") {
			// 不存在对应工作人员
			return ResultMessage.Clerk_ClerkNotExist;
		} else {
			// 删除成功
			return ResultMessage.Manager_DeleteClerkSuccess;
		}
	}

	/**
	 * 网站管理人员维护酒店工作人员个人信息
	 */
	@Override
	public ResultMessage changeClerkInfo(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("320581199001012016")) {
			// 不存在对应工作人员
			return ResultMessage.Clerk_ClerkNotExist;
		} else if (vo.hotelID == "" || vo.hotelName == "" || vo.ID == "" || vo.name == "" || vo.password == ""
				|| vo.phone == "" || vo.position == null) {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// 手机号码格式错误
			return ResultMessage.WrongPhoneFormat;
		} else {
			return ResultMessage.Manager_ChangeClerkInfoSuccess;
		}
	}

	/**
	 * 网站管理人员添加网站营销人员
	 */
	@Override
	public ResultMessage addMarketer(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "" || vo.name == "" || vo.password == "" || vo.phone == "") {
			return ResultMessage.Blank;
		} else if (vo.ID.equals("320581190001012016")) {
			// 营销人员已存在
			return ResultMessage.Marketer_MarketerAlreadyExist;
		} else if (vo.phone.length() != 11) {
			// 手机号码格式错误
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			// 身份证号格式错误
			return ResultMessage.WrongIDFormat;
		} else {
			// 添加成功
			return ResultMessage.Manager_AddMarketerSuccess;
		}
	}

	/**
	 * 网站管理人员删除网站营销人员
	 */
	@Override
	public ResultMessage deleteMarketer(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "320581190001012016") {
			// 对应网站营销人员不存在
			return ResultMessage.Marketer_MarketerNotExist;
		} else {
			// 删除成功
			return ResultMessage.Manager_DeleteMarketerSuccess;
		}
	}

	/**
	 * 网站管理人员维护网站营销人员信息
	 */
	@Override
	public ResultMessage changeMarketerInfo(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "320581190001012016") {
			// 对应网站营销人员不存在
			return ResultMessage.Marketer_MarketerNotExist;
		} else if (vo.ID == "" || vo.name == "" || vo.password == "" || vo.phone == "") {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// 手机号码格式错误
			return ResultMessage.WrongPhoneFormat;
		} else {
			// 修改成功
			return ResultMessage.Manager_ChangeMarketerInfoSuccess;
		}
	}

	/**
	 * 网站管理人员添加酒店
	 */
	@Override
	public ResultMessage addHotel(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID.equals("123456")) {
			// 酒店已存在
			return ResultMessage.Hotel_HotelAlreadyExist;
		} else if (vo.area == "" || vo.hotelAddress == "" || vo.hotelID == "" || vo.license == "") {
			// 信息未填写完整
			return ResultMessage.Blank;
		} else if (vo.hotelID.length() != 6) {
			// 酒店ID格式错误
			return ResultMessage.WrongHotelIDFormat;
		} else {
			// 添加酒店成功
			return ResultMessage.Manager_AddHotelSuccess;
		}
	}

	/**
	 * 网站管理人员删除酒店
	 */
	@Override
	public ResultMessage deleteHotel(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID == "123456") {
			// 酒店不存在
			return ResultMessage.Hotel_HotelNotExist;
		} else {
			// 删除酒店成功
			return ResultMessage.Manager_DeleteHotelSuccess;
		}
	}

	/**
	 * 网站管理人员维护酒店信息
	 */
	@Override
	public ResultMessage changeHotelInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID == "123456") {
			// 酒店不存在
			return ResultMessage.Hotel_HotelNotExist;
		} else if (vo.area == "" || vo.hotelAddress == "" || vo.hotelID == "" || vo.license == "") {
			// 信息未填写完整
			return ResultMessage.Blank;
		} else if (vo.hotelID.length() != 6) {
			// 酒店ID格式错误
			return ResultMessage.WrongHotelIDFormat;
		} else {
			// 修改酒店信息成功
			return ResultMessage.Manager_ChangeHotelInfoSuccess;
		}
	}

}
