package businesslogicservice.marketer_blservice;

import java.util.List;

import util.ResultMessage;

public interface Marketer_BLService {

	public ResultMessage signUp(MarketerVo vo);

	public MarketerVO getSingleByID(String ID);

	public MarketerVO getSingleByName(String name);

	public List<MarketerVO> getAll();

	public ResultMessage CreditCharge(String ID, int credit);
}
