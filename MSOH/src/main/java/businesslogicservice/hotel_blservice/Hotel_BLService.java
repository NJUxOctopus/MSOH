package businesslogicservice.hotel_blservice;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import util.ResultMessage;
import vo.*;

public interface Hotel_BLService {

    public ResultMessage addRoom(RoomVO vo) throws RemoteException;

    public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException;

    public ResultMessage modifyRoom(RoomVO roomVO) throws RemoteException;

    public ResultMessage deleteRoom(RoomVO roomVO) throws RemoteException;

    public ResultMessage changeAvailableRoom(String ID, String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException;

    public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException;

    public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException;

    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) throws RemoteException;

    public ResultMessage addHotel(HotelVO hotelVO) throws RemoteException;

    public ResultMessage modifyHotel(HotelVO hotelVO) throws RemoteException;

    public ResultMessage deleteHotel(String hotelID) throws RemoteException;

    public ResultMessage modifyDailyRoomInfo(DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException;

}
