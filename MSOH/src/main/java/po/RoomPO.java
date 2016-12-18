package po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author zqh
 *
 */
public class RoomPO implements Serializable {
	private static final long serialVersionUID=1L;
	// 房间ID
	private int roomID;
	// 酒店ID
	private String hotelID;
	// 房间类型
	private String roomType;
	// 已入住客房数量
	private int occupiedRooms;
	// 已预订客房数量
	private int reservedRooms;
	// 剩余可预定客房数量
	private int leftRooms;
	// 价格
	private double price;
	// 日期
	private Timestamp date;

	public RoomPO() {
	}

	public RoomPO(String hotelID, String roomType, int occupiedRooms, int reservedRooms, int leftRooms, double price, Timestamp date) {
		this.hotelID = hotelID;
		this.roomType = roomType;
		this.occupiedRooms = occupiedRooms;
		this.reservedRooms = reservedRooms;
		this.leftRooms = leftRooms;
		this.price = price;
		this.date=date;
	}

	public int getRoomID() {
		return roomID;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getOccupiedRooms() {
		return occupiedRooms;
	}

	public void setOccupiedRooms(int occupiedRooms) {
		this.occupiedRooms = occupiedRooms;
	}

	public int getReservedRooms() {
		return reservedRooms;
	}

	public void setReservedRooms(int reservedRooms) {
		this.reservedRooms = reservedRooms;
	}

	public int getLeftRooms() {
		return leftRooms;
	}

	public void setLeftRooms(int leftRooms) {
		this.leftRooms = leftRooms;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
