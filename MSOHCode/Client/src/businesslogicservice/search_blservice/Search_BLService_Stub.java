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
	public List<WorkerVO> searchByPosition(WorkerPosition position) {
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
	public List<CustomerVO> searchCustomer(String ID) {
		return customerList;
	}
	
	/**
	 * ����ID����������Ա
	 */
	@Override
	public List<WorkerVO> searchWorkerByID(String ID) {
		return workerList;
	}
	
	/**
	 * ������������������Ա
	 */
	@Override
	public List<WorkerVO> searchWorkerByName(String name) {
		return workerList;
	}
	
	/**
	 * ����ID�����Ƶ�
	 */
	@Override
	public List<HotelVO> searchHotelByID(String ID) {
		return hotelList;
	}
	
	
	/**
	 * ���յ�ַ�����Ƶ�
	 */
	@Override
	public List<HotelVO> searchHotelAddress(String address) {
		return hotelList;
	}
	
	/**
	 * ���ն���״̬��������
	 */
	@Override
	public List<OrderVO> searchOrderByStatus(OrderStatus status) {
		return orderList;
	}
	
	/**
	 * �����û�������������
	 */
	@Override
	public List<OrderVO> searchOrderByCustomerName(String customerName) {
		return orderList;
	}
	
	/**
	 * ���վƵ�����������
	 */
	@Override
	public List<OrderVO> searchOrderByHotelName(String hotelName) {
		return orderList;
	}

}
