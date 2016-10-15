package businesslogicservice.marketer_blservice;

import java.util.List;

import util.ResultMessage;
import vo.MarketerVO;

public interface Marketer_BLService {

	public ResultMessage signUp(MarketerVO vo);

	public MarketerVO getSingleByID(String ID);

	public MarketerVO getSingleByName(String name);

	public List<MarketerVO> getAll();

	public ResultMessage CreditCharge(String ID, int credit);
}
