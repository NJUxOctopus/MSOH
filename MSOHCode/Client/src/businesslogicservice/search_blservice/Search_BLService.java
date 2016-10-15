package businesslogicservice.search_blservice;

import java.util.List;

import javax.swing.text.Position;

public interface Search_BLService {
	public List<WorkerVO>ByPosition(Position position);
	
	public List<CustomerVO> Customer(String ID);
	
	public List<WorkerVO> WorkerByID(String ID);
	
	public List<WorkerVO> WorkerByName(String name);
	
	public List<HotelVO> HotelByID(String ID);
	
	public List<HotelVO> HotelByName(String name);
	
	public List<HotelVO> HotelAddress(String address);
	
	public List<OrderVo> OrderByStatus(String status);
	
	public List<OrderVO> OrderByCustomerName (String customerName);
	
	public List<OrderVO> OrderByHotelName(String hotelName);
}
