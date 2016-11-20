package businesslogicservice.hotel_blservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.OrderStatus;
import util.ResultMessage;
import vo.*;

/**
 * 
 * @author ST 2016/10/15
 *
 */

public class Hotel_BLService_Stub implements Hotel_BLService {

	public ResultMessage addRoom(RoomVO vo) {
		return null;
	}

	public ResultMessage modifyRoom(RoomVO roomVO) {
		return null;
	}

	public ResultMessage deleteRoom(RoomVO roomVO) {
		return null;
	}

	public ResultMessage changeAvailableRoom(String ID, String type, int number, DailyRoomInfoVO dailyRoomInfoVO) {
		return null;
	}

	public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) {
		return null;
	}

	public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) {
		return null;
	}

	public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) {
		return null;
	}

	public List<DailyRoomInfoVO> getDailyRoomInfo(String ID, Date Date) {
		return null;
	}

	public List<RoomVO> getRoom(String ID) {
		return null;
	}

	public ResultMessage addHotel(HotelVO roomVO) {
		return null;
	}

	public ResultMessage modifyHotel(HotelVO roomVO) {
		return null;
	}

	public ResultMessage deleteHotel(HotelVO roomVO) {
		return null;
	}

	public ResultMessage addToListOfHotelReservedByCustomer(HotelVO hotelVO, CustomerVO customerVO) {
		return null;
	}

	public ResultMessage modifyDailyRoomInfo(List<DailyRoomInfoVO> list) {
		return null;
	}
}
