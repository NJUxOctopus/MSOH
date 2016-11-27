package vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class DailyRoomInfoVO implements Serializable{

	public String hotelID;

	public Timestamp date;

	public List<RoomVO> room;

	public DailyRoomInfoVO() {
	}

	public DailyRoomInfoVO(String hotelID, Timestamp date, List<RoomVO> room) {
		this.hotelID = hotelID;
		this.date = date;
		this.room = room;
	}
}
