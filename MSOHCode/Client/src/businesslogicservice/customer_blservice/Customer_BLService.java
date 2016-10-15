package businesslogicservice.customer_blservice;

import java.util.List;

import util.ResultMessage;

public interface Customer_BLService {

	public ResultMessage signUp(CustomerVo vo);

	public ResultMessage changeInfo(CustomerVo vo);

	public CustomerVO getSingle(String ID);

	public List<CustomerVO> getAll();

	public CreditRecordVo getCreditRecord(CustomerVo vo);

	public List<HotelVO> getHistory(CustomerVo vo);
}
