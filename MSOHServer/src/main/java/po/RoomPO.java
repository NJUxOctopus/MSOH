package po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zqh
 */
@Entity
@Table(name = "room", schema = "msoh_database")
public class RoomPO implements Serializable, Cloneable {
    private static final long serialVersionUID=1L;
    // 房间ID
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_seq")
    @SequenceGenerator(name = "room_id_seq", sequenceName = "room_id_seq", allocationSize = 1)
    @Column(name = "roomID")
    private int roomID;
    // 酒店ID
    @Column(name = "hotelID")
    private String hotelID;
    // 房间类型
    @Column(name = "roomType")
    private String roomType;
    // 已入住客房数量
    @Column(name = "occupiedRooms")
    private int occupiedRooms;
    // 已预订客房数量
    @Column(name = "reservedRooms")
    private int reservedRooms;
    // 剩余可预定客房数量
    @Column(name = "leftRooms")
    private int leftRooms;
    // 价格
    @Column(name = "price")
    private double price;
    // 日期
    @Column(name = "date")
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
        this.date = date;
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

    @Override
    public Object clone() {
        RoomPO roomPO = null;
        try {
            roomPO = (RoomPO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return roomPO;
    }

}
