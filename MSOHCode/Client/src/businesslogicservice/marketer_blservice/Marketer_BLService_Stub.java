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
	 * ��վӪ����Աά��������Ϣ
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
	 * ��վӪ����Ա�޸�����
	 */
	@Override
	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ��Ӫ����Ա
			return ResultMessage.Marketer_MarketerNotExist;
		} else if (!oldPw.equals(marketerVO.password)) {
			// ���������
			return ResultMessage.ChangePasswordWrongOldPw;
		} else if (oldPw.equals(marketerVO.password) && !newPw1.equals(newPw2)) {
			// ���������벻һ��
			return ResultMessage.ChangePassword2DifferentNew;
		} else {
			// �޸�����ɹ�
			return ResultMessage.ChangePasswordSuccess;
		}
	}

	/**
	 * ͨ��ID���ض�Ӧ����վӪ����Ա
	 */
	@Override
	public MarketerVO getSingleByID(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ��Ӫ����Ա
			return null;
		} else {
			return marketerVO;
		}
	}

	/**
	 * ͨ�����ַ��ض�Ӧ����վӪ����Ա
	 */
	@Override
	public MarketerVO getSingleByName(String name) {
		// TODO Auto-generated method stub
		if (name.equals("Admin")) {
			// �Ҳ�����Ӧ��Ӫ����Ա
			return null;
		} else {
			return marketerVO;
		}
	}

	/**
	 * ��������Ӫ����Ա
	 */
	@Override
	public List<MarketerVO> getAll() {
		// TODO Auto-generated method stub
		return marketer;
	}

	/**
	 * ��վӪ����Ա��ֵ����
	 */
	@Override
	public ResultMessage CreditCharge(String ID, int credit, CustomerVO vo) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ�Ŀͻ�
			return ResultMessage.Customer_CustomerNotExist;
		} else {
			// ��ֵ�ɹ�
			vo.credit+=credit;
			return ResultMessage.Marketer_CreditChargeSuccess;
		}
	}

}
