package DataHelper.hotelDataHelper;

import po.RoomPO;

import java.util.List;

/**
 * Created by zqh on 2016/12/5.
 */
public interface RoomDataHelper {
    public boolean addRoom(RoomPO roomPO);

    public boolean modifyRoom(RoomPO roomPO);

    public boolean deleteRoom(RoomPO roomPO);

    public List<RoomPO> getRoomsByHotel(String hotelID);
}
