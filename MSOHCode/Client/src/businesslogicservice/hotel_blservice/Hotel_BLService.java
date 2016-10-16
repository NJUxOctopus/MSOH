package businesslogicservice.hotel_blservice;

import java.util.Date;
import java.util.List;

import util.ResultMessage;
import vo.CommentVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;

public interface Hotel_BLService {

	public ResultMessage addRoom(RoomVO vo);

	public ResultMessage changeAvailableRoom(String ID, String type, int number, Date date, DailyRoomInfoVO vo);

	public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO vo);

	public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO vo);

	public List<RoomVO> getRoom(String ID);

	public HotelVO getSingle(String ID);

	public List<HotelVO> getAll();

	public void addComment(CommentVO commentVO, OrderVO orderVO);

	public List<HotelVO> sortByPrice(List<HotelVO> list);

	public List<HotelVO> sortByStar(List<HotelVO> list);

	public List<HotelVO> sortByScore(List<HotelVO> list);

}
