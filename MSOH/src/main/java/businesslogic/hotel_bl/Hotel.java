package businesslogic.hotel_bl;

import businesslogicservice.hotel_blservice.Hotel_BLService;
import util.ResultMessage;
import vo.*;

import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Hotel implements Hotel_BLService {
    public ResultMessage addRoom(RoomVO vo) {
        return null;
    }

    public double getRoomPrice(double price,double number) {
        return price*number;
    }

    public ResultMessage modifyRoom(RoomVO roomVO) {
        return null;
    }

    public ResultMessage deleteRoom(RoomVO roomVO) {
        return null;
    }

    public ResultMessage changeAvailableRoom(String ID, String type, int number, DailyRoomInfoVO dailyRoomInfoVO) {
        return null;
    }

    public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) {
        return null;
    }

    public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) {
        return null;
    }

    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) {
        return null;
    }

    public List<DailyRoomInfoVO> getDailyRoomInfo(String ID, Date Date) {
        return null;
    }

    public List<RoomVO> getRoom(String ID) {
        return null;
    }

    public ResultMessage addHotel(HotelVO roomVO) {
        return null;
    }

    public ResultMessage modifyHotel(HotelVO roomVO) {
        return null;
    }

    public ResultMessage deleteHotel(HotelVO roomVO) {
        return null;
    }

    public ResultMessage addToListOfHotelReservedByCustomer(HotelVO hotelVO, CustomerVO customerVO) {
        return null;
    }

    public ResultMessage modifyDailyRoomInfo(List<DailyRoomInfoVO> list) {
        return null;
    }
}
