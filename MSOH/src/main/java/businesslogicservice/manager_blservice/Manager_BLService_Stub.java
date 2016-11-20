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
	 * ��վ������Աά��������Ϣ
	 */
	public ResultMessage changeInfo(ManagerVO vo) {
		// TODO Auto-generated method stub
		if (vo.phone == "") {
			// ����Ϣδ��д
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			return ResultMessage.WrongPhoneFormat;
		} else {
			// �޸���Ϣ�ɹ�
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա�޸�����
	 */
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		// TODO Auto-generated method stub
		if (!oldPw.equals(managerVO.password)) {
			// ���������
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ������Ա
			return ResultMessage.Manager_ManagerNotExist;
		} else if (oldPw.equals(managerVO.password) && !newPw1.equals(newPw2)) {
			// ���������벻һ��
			return ResultMessage.ChangePassword2DifferentNew;
		} else {
			// �޸�����ɹ�
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	/**
	 * ��վ������Ա��ӿͻ�
	 */
	public ResultMessage addCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("320581190001012016")) {
			// �Ѵ��ڸÿͻ�
			return ResultMessage.Manager_AddCustomerAlreadyExist;
		} else if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.name== "") {
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
			// ��ӳɹ�
			return ResultMessage.Manager_AddCustomerSuccess;
		}

	}

	/**
	 * ��վ������Աά���ͻ�������Ϣ
	 */
	public ResultMessage changeCustomerInfo(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.name == "") {
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
			return ResultMessage.Manager_ChangeCustomerInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա��ӾƵ깤����Ա
	 */
	public ResultMessage addClerk(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID.equals("123456")) {
			// û�ж�Ӧ�Ƶ�
			return ResultMessage.Hotel_HotelNotExist;
		} else if (vo.hotelID.equals("234567")) {
			// �þƵ��Ѵ��ڹ�����Ա
			return ResultMessage.Manager_AddClerkAlreadyExist;
		} else if (vo.hotelID == "" || vo.hotelName == "" || vo.ID == "" || vo.name == "" || vo.password == ""
				|| vo.phone == "") {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// �ֻ������ʽ����
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			// ���֤�Ÿ�ʽ����
			return ResultMessage.WrongIDFormat;
		} else {
			return ResultMessage.Manager_AddClerkSuccess;
		}
	}

	/**
	 * ��վ������Աɾ���Ƶ깤����Ա
	 */
	public ResultMessage deleteClerk(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "320581190001012016") {
			// �����ڶ�Ӧ������Ա
			return ResultMessage.Clerk_ClerkNotExist;
		} else {
			// ɾ���ɹ�
			return ResultMessage.Manager_DeleteClerkSuccess;
		}
	}

	/**
	 * ��վ������Աά���Ƶ깤����Ա������Ϣ
	 */
	public ResultMessage changeClerkInfo(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("320581199001012016")) {
			// �����ڶ�Ӧ������Ա
			return ResultMessage.Clerk_ClerkNotExist;
		} else if (vo.hotelID == "" || vo.hotelName == "" || vo.ID == "" || vo.name == "" || vo.password == ""
				|| vo.phone == "" ) {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// �ֻ������ʽ����
			return ResultMessage.WrongPhoneFormat;
		} else {
			return ResultMessage.Manager_ChangeClerkInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա�����վӪ����Ա
	 */
	public ResultMessage addMarketer(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "" || vo.name == "" || vo.password == "" || vo.phone == "") {
			return ResultMessage.Blank;
		} else if (vo.ID.equals("320581190001012016")) {
			// Ӫ����Ա�Ѵ���
			return ResultMessage.Marketer_MarketerAlreadyExist;
		} else if (vo.phone.length() != 11) {
			// �ֻ������ʽ����
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			// ���֤�Ÿ�ʽ����
			return ResultMessage.WrongIDFormat;
		} else {
			// ��ӳɹ�
			return ResultMessage.Manager_AddMarketerSuccess;
		}
	}

	/**
	 * ��վ������Աɾ����վӪ����Ա
	 */
	public ResultMessage deleteMarketer(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "320581190001012016") {
			// ��Ӧ��վӪ����Ա������
			return ResultMessage.Marketer_MarketerNotExist;
		} else {
			// ɾ���ɹ�
			return ResultMessage.Manager_DeleteMarketerSuccess;
		}
	}

	/**
	 * ��վ������Աά����վӪ����Ա��Ϣ
	 */
	public ResultMessage changeMarketerInfo(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "320581190001012016") {
			// ��Ӧ��վӪ����Ա������
			return ResultMessage.Marketer_MarketerNotExist;
		} else if (vo.ID == "" || vo.name == "" || vo.password == "" || vo.phone == "") {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// �ֻ������ʽ����
			return ResultMessage.WrongPhoneFormat;
		} else {
			// �޸ĳɹ�
			return ResultMessage.Manager_ChangeMarketerInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա��ӾƵ�
	 */
	public ResultMessage addHotel(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID.equals("123456")) {
			// �Ƶ��Ѵ���
			return ResultMessage.Hotel_HotelAlreadyExist;
		} else if (vo.area == "" || vo.hotelAddress == "" || vo.hotelID == "" || vo.license == "") {
			// ��Ϣδ��д����
			return ResultMessage.Blank;
		} else if (vo.hotelID.length() != 6) {
			// �Ƶ�ID��ʽ����
			return ResultMessage.WrongHotelIDFormat;
		} else {
			// ��ӾƵ�ɹ�
			return ResultMessage.Manager_AddHotelSuccess;
		}
	}

	/**
	 * ��վ������Աɾ���Ƶ�
	 */
	public ResultMessage deleteHotel(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID == "123456") {
			// �Ƶ겻����
			return ResultMessage.Hotel_HotelNotExist;
		} else {
			// ɾ���Ƶ�ɹ�
			return ResultMessage.Manager_DeleteHotelSuccess;
		}
	}

	/**
	 * ��վ������Աά���Ƶ���Ϣ
	 */
	public ResultMessage changeHotelInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID == "123456") {
			// �Ƶ겻����
			return ResultMessage.Hotel_HotelNotExist;
		} else if (vo.area == "" || vo.hotelAddress == "" || vo.hotelID == "" || vo.license == "") {
			// ��Ϣδ��д����
			return ResultMessage.Blank;
		} else if (vo.hotelID.length() != 6) {
			// �Ƶ�ID��ʽ����
			return ResultMessage.WrongHotelIDFormat;
		} else {
			// �޸ľƵ���Ϣ�ɹ�
			return ResultMessage.Manager_ChangeHotelInfoSuccess;
		}
	}

}
