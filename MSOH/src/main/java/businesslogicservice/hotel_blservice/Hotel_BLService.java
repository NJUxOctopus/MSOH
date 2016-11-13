package businesslogicservice.hotel_blservice;

import java.util.Date;
import java.util.List;

import util.ResultMessage;
import vo.*;

public interface Hotel_BLService {

	public ResultMessage addRoom(RoomVO vo);

	public ResultMessage modifyRoom(RoomVO roomVO);

	public ResultMessage deleteRoom (RoomVO roomVO);

	public ResultMessage changeAvailableRoom(String ID, String type, int number,DailyRoomInfoVO dailyRoomInfoVO);

	public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO);

	public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO);

	public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO);

	public List<DailyRoomInfoVO> getDailyRoomInfo (String ID , Date Date);

	public List<RoomVO> getRoom(String ID);

	public ResultMessage addHotel(HotelVO roomVO);

	public ResultMessage modifyHotel(HotelVO roomVO);

	public ResultMessage deleteHotel(HotelVO roomVO);

	public ResultMessage  addToListOfHotelReservedByCustomer(HotelVO hotelVO, CustomerVO customerVO);

	public ResultMessage  modifyDailyRoomInfo (List<DailyRoomInfoVO> list);
}
