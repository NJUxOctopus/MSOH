package dataservice.hotel_dataservice;

import java.util.Date;
import java.util.List;

import po.CommentPO;
import po.CustomerPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;
import util.ResultMessage;

/**
 * @author �X����
 */
public interface Hotel_DataService {
    public ResultMessage add(HotelPO po);

    public ResultMessage modify(HotelPO po);

    public ResultMessage delete(HotelPO po);

    public List<HotelPO> find(String address, String area, Date expected_date_of_arrival, Date expected_date_of_departure, int star, double score);

    public HotelPO getHotel(String id);

    public ResultMessage addRoomType(RoomPO po);

    public ResultMessage modifyRoomType(RoomPO po);

    public ResultMessage deleteRoomType(RoomPO po);

    public double getRoomPrice(String roomName, RoomPO roomPO);

    public String getRoomType(RoomPO po);

    public RoomPO getRoom(String hotelID,String roomName);

    public DailyRoomInfoPO getDailyRoomInfo(Date date);

    public ResultMessage setDailyRoomInfo(List<DailyRoomInfoPO> list);

    public ResultMessage addComment(CommentPO po);

    public List<CommentPO> getCommentPO(String hotelID);

    public ResultMessage addToListOfHotelReservedByCustomer(HotelPO hotelPO, CustomerPO customerPO);


}
