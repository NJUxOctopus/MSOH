package businesslogicservice.customer_blservice;

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

public interface Customer_BLService {

	public ResultMessage signUp(CustomerVO vo);

	public ResultMessage changeInfo(CustomerVO vo);

	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2);

	public CustomerVO getSingle(String ID);

	public List<CustomerVO> getAll();

	public CreditRecordVO getCreditRecord(CustomerVO vo);

	public List<HotelVO> getHistoryHotel(CustomerVO vo);
}
