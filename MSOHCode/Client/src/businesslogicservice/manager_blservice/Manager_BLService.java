package businesslogicservice.manager_blservice;

import util.ResultMessage;

public interface Manager_BLService {

	public ResultMessage signUp(String name, String password, String ID, String phone);

	public ResultMessage addCustomer(CustomerVo vo);

	public ResultMessage deleteCustomer(CustomerVo vo);

	public ResultMessage changeCustomerInfo(CustomerVo vo);

	public ResultMessage addClerk(ClerkVo Vo);

	public ResultMessage deleteClerk(ClerkVo vo);

	public ResultMessage changeClerkInfo(ClerkVo vo);

	public ResultMessage addMarketer(MarketerVo vo);

	public ResultMessage deleteMarketer(MarketerVo vo);

	public ResultMessage changeMarketerInfo(MarketerVo vo);

	public ResultMessage addHotel(HotelVo vo);

	public ResultMessage deleteHotel(HotelVo vo);

	public ResultMessage changeHotelInfo(HotelVo vo);

}
