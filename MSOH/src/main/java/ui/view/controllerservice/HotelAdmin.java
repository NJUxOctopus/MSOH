package ui.view.controllerservice;

import util.ResultMessage;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by zqh on 2016/11/10.
 */
public interface HotelAdmin {

    HotelVO findByID(String ID) throws RemoteException;

    HotelVO findByName(String name);

    HotelVO findByAddress(String address);

    HotelVO findByClerkID(String clerkID) throws RemoteException;

    ResultMessage addHotel(HotelVO hotelVO);

    ResultMessage deleteHotel(HotelVO hotelVO);

    public ResultMessage modifyDailyRoomInfo(DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException;

    ResultMessage updateHotelInfo(HotelVO hotelVO) throws RemoteException;

    DailyRoomInfoVO getDailyRoomInfo(String hotelID, Timestamp timestamp) throws RemoteException;

    RoomVO getRoomInfo(String hotelID, String roomType, Timestamp timestamp) throws RemoteException;

    List<String> getAllCities() throws RemoteException;

    List<String> getAreaByCity(String city) throws RemoteException;

}
