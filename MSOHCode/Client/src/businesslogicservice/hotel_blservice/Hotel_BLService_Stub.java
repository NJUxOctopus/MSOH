package businesslogicservice.hotel_blservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.OrderStatus;
import util.ResultMessage;
import vo.CommentVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;

/**
 * 
 * @author ST 2016/10/15
 *
 */

public class Hotel_BLService_Stub implements Hotel_BLService {

	DailyRoomInfoVO daliyRoomInfoVO;
	List<DailyRoomInfoVO> daliyRoomInfo = new ArrayList<DailyRoomInfo>();
	List<RoomVO> roomVO = new ArrayList<RoomVO>();
	List<HotelVO> hotel = new ArrayList<HotelVO>();
	HotelVO hotelVO;

	/**
	 * ¼����ÿͷ�
	 */
	@Override
	public ResultMessage addRoom(RoomVO roomVO) {
		// TODO Auto-generated method stub
		if (roomVO.roomType == "") {
			return ResultMessage.Blank;
		} else {
			return ResultMessage.Hotel_AddRoomSuccess;
		}
	}

	/**
	 * �ı��Ԥ���ͷ�����
	 */
	@Override
	public ResultMessage changeAvailableRoom(String ID, String type, int number, Date date, DailyRoomInfoVO vo) {
		// TODO Auto-generated method stub
		if (ID.equals("123456789")) {
			// û�ж�Ӧ�ľƵ�
			return ResultMessage.Hotel_HotelNotExist;
		} else {
			
		}
		return null;
	}

	/**
	 * �ı���Ԥ���ͷ�����
	 */
	@Override
	public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * �ı�����ס�ͷ�����
	 */
	@Override
	public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ���ض�Ӧ�Ƶ�ķ�����Ϣ
	 */
	@Override
	public List<RoomVO> getRoom(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("123456")) {
			// û�ж�Ӧ�ľƵ�
			return null;
		} else {
			return roomVO;
		}
	}

	/**
	 * ���ض�Ӧ�Ƶ����Ϣ
	 */
	@Override
	public HotelVO getSingle(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("123456")) {
			// û�ж�Ӧ�ľƵ�
			return null;
		} else {
			return hotelVO;
		}
	}

	/**
	 * �������оƵ����Ϣ
	 */
	@Override
	public List<HotelVO> getAll() {
		// TODO Auto-generated method stub
		return hotel;
	}

	/**
	 * �ھƵ���Ϣ���������
	 */
	@Override
	public void addComment(CommentVO commentVO, OrderVO orderVO) {
		// TODO Auto-generated method stub
		if(orderVO.orderType.equals(OrderStatus.FINISHED_UNEVALUATED)){
			// �����ѽ�����δ����
			hotelVO.comment.add(commentVO);
		}
		

	}

	/**
	 * ���Ƶ갴�۸�����
	 */
	@Override
	public List<HotelVO> sortByPrice(List<HotelVO> list) {
		// TODO Auto-generated method stub
		return hotel;
	}

	/**
	 * ���Ƶ갴�Ǽ�����
	 */
	@Override
	public List<HotelVO> sortByStar(List<HotelVO> list) {
		// TODO Auto-generated method stub
		return hotel;
	}

	/**
	 * ���Ƶ갴��������
	 */
	@Override
	public List<HotelVO> sortByScore(List<HotelVO> list) {
		// TODO Auto-generated method stub
		return hotel;
	}

}
