package vo;

import java.io.Serializable;

/**
 * @author pxr
 */
public class RoomVO implements Serializable {

    public String hotelID;

    public String roomType;

    public int occupiedRooms;

    public int reservedRooms;

    public int leftRooms;

    public double price;

    public RoomVO() {
    }

    public RoomVO(String hotelID, String roomType, int occupiedRooms, int reservedRooms, int leftRooms, double price) {
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.occupiedRooms = occupiedRooms;
        this.reservedRooms = reservedRooms;
        this.leftRooms = leftRooms;
        this.price = price;
    }

//    public double getPrice() {
//        return price;
//    }

    /**
     * 录入可用客房时使用的构造方法
     * @param hotelID
     * @param roomType
     * @param leftRooms
     * @param price
     */
    public RoomVO(String hotelID, String roomType, int leftRooms, double price) {
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.leftRooms = leftRooms;
        this.price = price;
    }

}
