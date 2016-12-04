package businesslogicservice.hotelUtil_blservice;

import businesslogic.hotel_bl.Hotel;
import vo.CommentVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface HotelUtil_BLService {
    public List<HotelVO> getAll() throws RemoteException;

    public List<HotelVO> sortByPrice(List<HotelVO> list) throws RemoteException;

    public List<HotelVO> sortByStar(List<HotelVO> list) throws RemoteException;

    public List<HotelVO> sortByScore(List<HotelVO> list) throws RemoteException;

    public HotelVO getByID(String ID) throws RemoteException;

    public List<HotelVO> getByName(String name) throws RemoteException;

    public List<HotelVO> getByArea(String area) throws RemoteException;

    public HotelVO getHotelByClerkID(String clerkID) throws RemoteException;

    public RoomVO getRoomByName(String hotelID, String roomName) throws RemoteException;

    public List<CommentVO> getComment(String hotelID) throws RemoteException;

    public DailyRoomInfoVO getDailyRoomInfo(String hotelID, Date date)throws RemoteException;

    public List<HotelVO> filterByStar(String star) throws RemoteException;

    public List<HotelVO> filterByScore(String low, String high) throws RemoteException;

    public List<HotelVO> filterByCity(String city) throws RemoteException;

    public List<HotelVO> filterByDate(Timestamp timestamp1, Timestamp timestamp2) throws RemoteException;

    public List<HotelVO> filterByArea(String area) throws RemoteException;

    public List<HotelVO> filter(String star, String name, String low, String high, String city, Timestamp timestamp1, Timestamp timestamp2, String area) throws RemoteException;

    public List<HotelVO> searchHotel(String address, String area, Timestamp timestamp1, Timestamp timestamp2, String star, String low, String high) throws RemoteException;

    public List<HotelVO> filterByName(String name) throws RemoteException;

    public List<String> getAllCities() throws RemoteException;

    public List<String> getAreaByCity(String city) throws RemoteException;
}

