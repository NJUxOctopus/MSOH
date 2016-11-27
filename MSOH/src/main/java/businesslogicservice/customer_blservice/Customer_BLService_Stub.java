package businesslogicservice.customer_blservice;

import java.util.List;

import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;


public class Customer_BLService_Stub implements Customer_BLService {

	CustomerVO customerVO;
	CreditRecordVO creditRecordVO;
	HotelVO hotelVO;
	List<HotelVO> list;

	public ResultMessage signUp(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("000000000000000000")) {
			return ResultMessage.Customer_SignupExist;
		} else if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.name == "") {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			return ResultMessage.WrongIDFormat;
		} else if (!vo.email.contains("@")) {
			return ResultMessage.WrongEmailFormat;
		} else {
			return ResultMessage.Customer_SignupSuccess;
		}
	}

	public ResultMessage changeInfo(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.name == "") {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else if (!vo.email.contains("@")) {
			return ResultMessage.WrongEmailFormat;
		} else {
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		if (!oldPw.equals(customerVO.password)) {
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (oldPw.equals(customerVO.password) && !newPw1.equals(newPw2)) {
			return ResultMessage.ChangePassword2DifferentNew;
		} else

		{
			return ResultMessage.ChangePasswordSuccess;
		}
	}



	public CreditRecordVO getCreditRecord(String ID) {
		// TODO Auto-generated method stub
		return creditRecordVO;
	}

	public List<HotelVO> getHistoryHotel(String ID) {
		// TODO Auto-generated method stub
		return list;
	}

	public int getCredit(String ID) {
		return 0;
	}

	public ResultMessage addCreditRecord(String ID, CreditRecordVO creditRecordVO) {
		return null;
	}

}
