package businesslogicservice.search_blservice;

import java.util.List;

import util.OrderStatus;
import util.WorkerPosition;
import vo.CustomerVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.WorkerVO;

public interface Search_BLService {
	public List<WorkerVO> searchByPosition(WorkerPosition position);
	
	public List<CustomerVO> searchCustomer(String ID);
	
	public List<WorkerVO> searchWorkerByID(String ID);
	
	public List<WorkerVO> searchWorkerByName(String name);
	
	public List<HotelVO> searchHotelByID(String ID);
	
	public List<HotelVO> searchHotelByName(String name);
	
	public List<HotelVO> searchHotelAddress(String address);
	
	public List<OrderVO> searchOrderByStatus(OrderStatus status);
	
	public List<OrderVO> searchOrderByCustomerName (String customerName);
	
	public List<OrderVO> searchOrderByHotelName(String hotelName);
}
