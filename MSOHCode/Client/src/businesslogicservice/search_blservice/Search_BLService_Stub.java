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
	 * ���չ�����Աְλ�����б�
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
	 * ����ID�����û��б�
	 */
	@Override
	public List<CustomerVO> Customer(String ID) {
		return customerList;
	}
	
	/**
	 * ����ID����������Ա
	 */
	@Override
	public List<WorkerVO> WorkerByID(String ID) {
		return workerList;
	}
	
	/**
	 * ������������������Ա
	 */
	@Override
	public List<WorkerVO> WorkerByName(String name) {
		return workerList;
	}
	
	/**
	 * ����ID�����Ƶ�
	 */
	@Override
	public List<HotelVO> HotelByID(String ID) {
		return hotelList;
	}
	
	/**
	 * �������������Ƶ�
	 */
	@Override
	public List<HotelVO> HotelByName(String name) {
		return hotelList;
	}
	
	/**
	 * ���յ�ַ�����Ƶ�
	 */
	@Override
	public List<HotelVO> HotelAddress(String address) {
		return hotelList;
	}
	
	/**
	 * ���ն���״̬��������
	 */
	@Override
	public List<OrderVO> OrderByStatus(String status) {
		return orderList;
	}
	
	/**
	 * �����û�������������
	 */
	@Override
	public List<OrderVO> OrderByCustomerName(String customerName) {
		return orderList;
	}
	
	/**
	 * ���վƵ�����������
	 */
	@Override
	public List<OrderVO> OrderByHotelName(String hotelName) {
		return orderList;
	}

}
