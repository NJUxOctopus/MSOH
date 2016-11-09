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

public interface Manager_BLService {
	
	public ResultMessage changeInfo(ManagerVO vo);

	public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2);

	public ResultMessage addCustomer(CustomerVO vo);

	public ResultMessage changeCustomerInfo(CustomerVO vo);

	public ResultMessage addClerk(ClerkVO Vo);

	public ResultMessage deleteClerk(ClerkVO vo);

	public ResultMessage changeClerkInfo(ClerkVO vo);

	public ResultMessage addMarketer(MarketerVO vo);

	public ResultMessage deleteMarketer(MarketerVO vo);

	public ResultMessage changeMarketerInfo(MarketerVO vo);

	public ResultMessage addHotel(HotelVO vo);

	public ResultMessage deleteHotel(HotelVO vo);

	public ResultMessage changeHotelInfo(HotelVO vo);

}
