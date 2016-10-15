package businesslogicservice.search_blservice;

import java.util.List;

import javax.swing.text.Position;

import vo.CustomerVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.WorkerVO;

/**
 * 
 * @author Pxr created:10.15 latest modify:10.15
 *
 */
public class Search_BLService_Stub implements Search_BLService {

	List<WorkerVO> clerkList;

	List<WorkerVO> marketerList;

	List<CustomerVO> customerList;

	List<WorkerVO> workerList;

	List<HotelVO> hotelList;

	List<OrderVO> orderList;
	
	/**
	 * 按照工作人员职位返回列表
	 */
	@Override
	public List<WorkerVO> ByPosition(WorkerPosition position) {
		if (position.equals(WorkerPosition.CLERK)) {
			return clerkList;
		}
		if (position.equals(WorkerPosition.MARKETER)) {
			return marketerList;
		} else {
			return null;
		}
	}
	
	/**
	 * 按照ID返回用户列表
	 */
	@Override
	public List<CustomerVO> Customer(String ID) {
		return customerList;
	}
	
	/**
	 * 按照ID搜索工作人员
	 */
	@Override
	public List<WorkerVO> WorkerByID(String ID) {
		return workerList;
	}
	
	/**
	 * 按照名字搜索工作人员
	 */
	@Override
	public List<WorkerVO> WorkerByName(String name) {
		return workerList;
	}
	
	/**
	 * 按照ID搜索酒店
	 */
	@Override
	public List<HotelVO> HotelByID(String ID) {
		return hotelList;
	}
	
	/**
	 * 按照名字搜索酒店
	 */
	@Override
	public List<HotelVO> HotelByName(String name) {
		return hotelList;
	}
	
	/**
	 * 按照地址搜索酒店
	 */
	@Override
	public List<HotelVO> HotelAddress(String address) {
		return hotelList;
	}
	
	/**
	 * 按照订单状态搜索订单
	 */
	@Override
	public List<OrderVO> OrderByStatus(String status) {
		return orderList;
	}
	
	/**
	 * 按照用户姓名搜索订单
	 */
	@Override
	public List<OrderVO> OrderByCustomerName(String customerName) {
		return orderList;
	}
	
	/**
	 * 按照酒店名搜索订单
	 */
	@Override
	public List<OrderVO> OrderByHotelName(String hotelName) {
		return orderList;
	}

}
