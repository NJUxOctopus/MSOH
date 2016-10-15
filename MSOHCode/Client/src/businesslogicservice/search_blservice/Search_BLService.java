package businesslogicservice.search_blservice;

import java.util.List;

import vo.CustomerVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.WorkerVO;

public interface Search_BLService {
	public List<WorkerVO>ByPosition(WorkerPosition position);
	
	public List<CustomerVO> Customer(String ID);
	
	public List<WorkerVO> WorkerByID(String ID);
	
	public List<WorkerVO> WorkerByName(String name);
	
	public List<HotelVO> HotelByID(String ID);
	
	public List<HotelVO> HotelByName(String name);
	
	public List<HotelVO> HotelAddress(String address);
	
	public List<OrderVO> OrderByStatus(String status);
	
	public List<OrderVO> OrderByCustomerName (String customerName);
	
	public List<OrderVO> OrderByHotelName(String hotelName);
}
