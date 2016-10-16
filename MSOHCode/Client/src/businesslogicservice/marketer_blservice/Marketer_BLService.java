package businesslogicservice.marketer_blservice;

import java.util.List;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MarketerVO;

public interface Marketer_BLService {

	public ResultMessage changeInfo(MarketerVO vo);

	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2);

	public MarketerVO getSingleByID(String ID);

	public MarketerVO getSingleByName(String name);

	public List<MarketerVO> getAll();

	public ResultMessage CreditCharge(String ID, int credit, CustomerVO vo);
}
