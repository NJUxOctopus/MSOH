package businesslogicservice.hotel_blservice;

import java.util.List;

import util.ResultMessage;

public interface Hotel_BLService {

	public ResultMessage addRoom(RoomVo vo);

	public ResultMessage changeAvailableRoom(String type, int number, DailyRoomInfoVo vo);

	public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVo vo);

	public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVo vo);

	public List<RoomVo> getRoom(String ID);

	public HotelVo getSingle(String ID);

	public List<HotelVo> getAll();

	public void addComment(CommentVo vo, OrderVo vo);

	public List<HotelVO> sortByPrice(List<HotelVO> list);

	public List<HotelVO> sortByStar(List<HotelVO> list);

	public List<HotelVO> sortByScore(List<HotelVO> list);

}
