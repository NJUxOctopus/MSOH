package vo;

import java.util.Date;
import java.util.List;

import vo.RoomVO;

public class DailyRoomInfoVO {

	public String hotelID;

	public Date date;

	public List<RoomVO> room;

	public DailyRoomInfoVO() {
	}

	public DailyRoomInfoVO(String hotelID, Date date, List<RoomVO> room) {
		this.hotelID = hotelID;
		this.date = date;
		this.room = room;
	}
}
