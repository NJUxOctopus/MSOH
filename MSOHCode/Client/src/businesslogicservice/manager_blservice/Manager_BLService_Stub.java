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
	@Override
	public ResultMessage changeInfo(ManagerVO vo) {
		// TODO Auto-generated method stub
		if (vo.phone == "") {
			// ����Ϣδ��д
			return ResultMessage.Blank;
		} else {
			// �޸���Ϣ�ɹ�
			return ResultMessage.ChangeInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա�޸�����
	 */
	@Override
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		// TODO Auto-generated method stub
		if (!oldPw.equals(managerVO.password)) {
			// ���������
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ������Ա
			return ResultMessage.ChangePasswordNotExist;
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
	@Override
	public ResultMessage addCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("320581190001012016")) {
			// �Ѵ��ڸÿͻ�
			return ResultMessage.Manager_AddCustomerAlreadyExist;
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
			// ��ӳɹ�
			return ResultMessage.Manager_AddCustomerSuccess;
		}

	}

	/**
	 * ��վ������Աά���ͻ�������Ϣ
	 */
	@Override
	public ResultMessage changeCustomerInfo(CustomerVO vo) {
		// TODO Auto-generated method stub
		if (vo.email == "" || vo.ID == "" || vo.password == "" || vo.phone == "" || vo.userName == "") {
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
			// �޸ĳɹ�
			return ResultMessage.Manager_changeCustomerInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա��ӾƵ깤����Ա
	 */
	@Override
	public ResultMessage addClerk(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID.equals("123456")) {
			// û�ж�Ӧ�Ƶ�
			return ResultMessage.Hotel_HotelNotExist;
		} else if (vo.hotelID.equals("234567")) {
			// �þƵ��Ѵ��ڹ�����Ա
			return ResultMessage.Manager_AddClerkAlreadyExist;
		} else if (vo.hotelID == "" || vo.hotelName == "" || vo.ID == "" || vo.name == "" || vo.password == ""
				|| vo.phone == "" || vo.position == "") {
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
	@Override
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
	@Override
	public ResultMessage changeClerkInfo(ClerkVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID.equals("320581199001012016")) {
			// �����ڶ�Ӧ������Ա
			return ResultMessage.Clerk_ClerkNotExist;
		} else if (vo.hotelID == "" || vo.hotelName == "" || vo.ID == "" || vo.name == "" || vo.password == ""
				|| vo.phone == "" || vo.position == "") {
			return ResultMessage.Blank;
		} else if (vo.phone.length() != 11) {
			// �ֻ������ʽ����
			return ResultMessage.WrongPhoneFormat;
		} else if (vo.ID.length() != 18) {
			// ���֤�Ÿ�ʽ����
			return ResultMessage.WrongIDFormat;
		} else {
			return ResultMessage.Manager_ChangeClerkInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա�����վӪ����Ա
	 */
	@Override
	public ResultMessage addMarketer(MarketerVO vo) {
		// TODO Auto-generated method stub
		if (vo.ID == "" || vo.name == "" || vo.password == "" || vo.phone == "") {
			return ResultMessage.Blank;
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
	@Override
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
	@Override
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
		} else if (vo.ID.length() != 18) {
			// ���֤�Ÿ�ʽ����
			return ResultMessage.WrongIDFormat;
		} else {
			// �޸ĳɹ�
			return ResultMessage.Manager_ChangeMarketerInfoSuccess;
		}
	}

	/**
	 * ��վ������Ա��ӾƵ�
	 */
	@Override
	public ResultMessage addHotel(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID.equals("123456")) {
			// �Ƶ��Ѵ���
			return ResultMessage.Hotel_HotelAlreadyExist;
		} else if (vo.area == "" || vo.hotelAddress == "" || vo.hotelID == "" || vo.license == "" || vo.star == "") {
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
	@Override
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
	@Override
	public ResultMessage changeHotelInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		if (vo.hotelID == "123456") {
			// �Ƶ겻����
			return ResultMessage.Hotel_HotelNotExist;
		} else if (vo.area == "" || vo.hotelAddress == "" || vo.hotelID == "" || vo.license == "" || vo.star == "") {
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
