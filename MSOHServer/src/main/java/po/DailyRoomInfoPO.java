package po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author 週沁涵
 *
 */
public class DailyRoomInfoPO implements Serializable {
	// 酒店ID
	private String hotelID;
	// 日期
	private Date date;
	// 若干个RoomPO
	private List<RoomPO> room;

	public DailyRoomInfoPO() {
	}

	public DailyRoomInfoPO(String hotelID, Date date, List<RoomPO> room) {
		this.hotelID = hotelID;
		this.date = date;
		this.room = room;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<RoomPO> getRoom() {
		return room;
	}

	public void setRoom(List<RoomPO> room) {
		this.room = room;
	}

}
