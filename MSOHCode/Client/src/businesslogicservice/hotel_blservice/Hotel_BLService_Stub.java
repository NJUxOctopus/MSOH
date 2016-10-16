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
	 * 录入可用客房
	 */
	@Override
	public ResultMessage addRoom(RoomVO roomVO) {
		// TODO Auto-generated method stub
		if (roomVO.roomType == "") {
			return ResultMessage.Hotel_AddRoomBlank;
		} else {
			return ResultMessage.Hotel_AddRoomSuccess;
		}
	}

	/**
	 * 改变可预订客房数量
	 */
	@Override
	public ResultMessage changeAvailableRoom(String ID, String type, int number, Date date, DailyRoomInfoVO vo) {
		// TODO Auto-generated method stub
		if (ID.equals("123456789")) {
			// 没有对应的酒店
			return ResultMessage.Hotel_HotelNotExist;
		} else {

		}
		return null;
	}

	/**
	 * 改变已预订客房数量
	 */
	@Override
	public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 改变已入住客房数量
	 */
	@Override
	public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回对应酒店的房间信息
	 */
	@Override
	public List<RoomVO> getRoom(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("123456")) {
			// 没有对应的酒店
			return null;
		} else {
			return roomVO;
		}
	}

	/**
	 * 返回对应酒店的信息
	 */
	@Override
	public HotelVO getSingle(String ID) {
		// TODO Auto-generated method stub
		if (ID.equals("123456")) {
			// 没有对应的酒店
			return null;
		} else {
			return hotelVO;
		}
	}

	/**
	 * 返回所有酒店的信息
	 */
	@Override
	public List<HotelVO> getAll() {
		// TODO Auto-generated method stub
		return hotel;
	}

	/**
	 * 在酒店信息中添加评论
	 */
	@Override
	public void addComment(CommentVO commentVO, OrderVO orderVO) {
		// TODO Auto-generated method stub
		if(orderVO.orderType.equals(OrderStatus.FINISHED_UNEVALUATED))){
			// 订单已结束且未评价
			hotelVO.comment.add(commentVO);
		}
		

	}

	/**
	 * 将酒店按价格排序
	 */
	@Override
	public List<HotelVO> sortByPrice(List<HotelVO> list) {
		// TODO Auto-generated method stub
		return hotel;
	}

	/**
	 * 将酒店按星级排序
	 */
	@Override
	public List<HotelVO> sortByStar(List<HotelVO> list) {
		// TODO Auto-generated method stub
		return hotel;
	}

	/**
	 * 将酒店按评分排序
	 */
	@Override
	public List<HotelVO> sortByScore(List<HotelVO> list) {
		// TODO Auto-generated method stub
		return hotel;
	}

}
