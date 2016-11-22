package businesslogicservice.marketer_blservice;

import java.rmi.RemoteException;
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

	public ResultMessage deleteMarketer(String marketerID) throws RemoteException {
		return null;
	}

	public ResultMessage addMarketer(MarketerVO marketerVO) throws RemoteException {
		return null;
	}

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


	public ResultMessage CreditCharge(String ID, int credit, CustomerVO vo) {
		// TODO Auto-generated method stub
		if (ID.equals("320581190001012016")) {
			// �Ҳ�����Ӧ�Ŀͻ�
			return ResultMessage.Customer_CustomerNotExist;
		} else {
			// ��ֵ�ɹ�
			vo.credit += credit;
			return ResultMessage.Marketer_CreditChargeSuccess;
		}
	}

}
