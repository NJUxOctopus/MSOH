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

	/**
	 * �ͻ�ע��
	 */
	@Override
	public ResultMessage signUp(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("000000000000000000")) {
			// �����֤���Ѵ���
			return ResultMessage.Customer_SignupExist;
		} else if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.userName == "") {
			// ��ĳ��δ��д
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// �ֻ������ʽ����
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			// ���֤�Ÿ�ʽ����
			return ResultMessage.WrongIDFormat;
		} else if (!vo.email.contains("@")) {
			// �����ʽ����
			return ResultMessage.WrongEmailFormat;
		} else {
			// ע��ɹ�
			return ResultMessage.Customer_SignupSuccess;
		}
	}

	/**
	 * �ͻ�ά��������Ϣ
	 */
	@Override
	public ResultMessage changeInfo(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.userName == "") {
			// ��ĳ��δ��д
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// �ֻ������ʽ����
			return ResultMessage.WrongPhoneFormat;
		} else if (!vo.email.contains("@")) {
			// �����ʽ����
			return ResultMessage.WrongEmailFormat;
		} else {
			// �޸ĳɹ�
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	/**
	 * �ͻ��޸�����
	 */
	@Override
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		if (!oldPw.equals(customerVO.password)) {
			// ���������
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (oldPw.equals(customerVO.password) && !newPw1.equals(newPw2)) {
			// ���������벻һ��
			return ResultMessage.ChangePassword2DifferentNew;
		} else if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ�û�
			return ResultMessage.ChangePasswordNotExist;
		}else {
			// �޸�����ɹ�
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	/**
	 * �������֤�Ų��ҿͻ�
	 */
	@Override
	public CustomerVO getSingle(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// �ҵ���Ӧ�û�
			return customerVO;
		} else {
			// �����ڶ�Ӧ�û�
			return null;
		}
	}

	/**
	 * �������пͻ��б�
	 */
	@Override
	public List<CustomerVO> getAll() {
		// TODO Auto-generated method stub
		List<CustomerVO> customer = new ArrayList<CustomerVO>();
		return customer;
	}

	/**
	 * �鿴�ͻ����ü�¼
	 */
	@Override
	public CreditRecordVO getCreditRecord(CustomerVO vo) {
		// TODO Auto-generated method stub
		return creditRecordVO;
	}

	/**
	 * ����Ԥ�����ľƵ�
	 */
	@Override
	public List<HotelVO> getHistoryHotel(CustomerVO vo) {
		// TODO Auto-generated method stub
		return hotelVO;
	}

}
