package po;

import util.CopyUtil;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author zqh
 */
public class DailyRoomInfoPO implements Serializable, Cloneable {
    // 酒店ID
    private String hotelID;
    // 日期
    private Timestamp date;
    // 当日可用房间类型及数量
    private List<RoomPO> room;

    public DailyRoomInfoPO() {
    }

    public DailyRoomInfoPO(String hotelID, Timestamp date, List<RoomPO> room) {
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

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<RoomPO> getRoom() {
        return room;
    }

    public void setRoom(List<RoomPO> room) {
        this.room = room;
    }

    @Override
    public Object clone() {
        DailyRoomInfoPO dailyRoomInfoPO = null;
        try {
            dailyRoomInfoPO = (DailyRoomInfoPO) super.clone();
            dailyRoomInfoPO.setRoom(CopyUtil.deepCopy(dailyRoomInfoPO.getRoom()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return dailyRoomInfoPO;
    }

}
