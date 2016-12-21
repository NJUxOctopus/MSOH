package businesslogicservice.hotel_blservice;

import vo.CommentVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface HotelUtil_BLService {
    public List<HotelVO> getAll() throws RemoteException;

    public List<HotelVO> sortByStar(List<HotelVO> list) throws RemoteException;

    public List<HotelVO> sortByScore(List<HotelVO> list) throws RemoteException;

    public HotelVO getByID(String ID) throws RemoteException;

    public List<HotelVO> getByName(String name) throws RemoteException;

    public List<HotelVO> getByArea(String area) throws RemoteException;

    public HotelVO getHotelByClerkID(String clerkID) throws RemoteException;

    public RoomVO getRoomByName(String hotelID, String roomName, Timestamp timestamp) throws RemoteException;

    public List<CommentVO> getComment(String hotelID) throws RemoteException;

    public DailyRoomInfoVO getDailyRoomInfo(String hotelID, Timestamp timestamp) throws RemoteException;

    public List<HotelVO> filter(String star, String name, String low, String high, Timestamp timestamp1, Timestamp timestamp2, String roomType, int roomNum, String area) throws RemoteException;

    public List<HotelVO> searchHotel(String area, Timestamp timestamp1, Timestamp timestamp2, String star, String low, String high) throws RemoteException;

    public List<String> getAllCities() throws RemoteException;

    public List<String> getAreaByCity(String city) throws RemoteException;

    public Boolean roomEnough(String hotelID, Timestamp timestamp1, Timestamp timestamp2, int BigBedRoom, int SingleRoom, int StandardRoom) throws RemoteException;

    public RoomVO getBetweenDate(String hotelID, String roomName, Timestamp timestamp1, Timestamp timestamp2) throws RemoteException;

    public boolean hotelIsReserverd(String customerID, String hotelID) throws RemoteException;
}

