package businesslogicservice.hotel_blservice;

import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;

public interface Hotel_BLService {

    public void addDailyRoomInfo(DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException;

    public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException;

    public ResultMessage changeAvailableRoom(OrderVO orderVO, int change) throws RemoteException;

    public ResultMessage changeReservedRoom(OrderVO orderVO, int change) throws RemoteException;

    public ResultMessage changeOccupiedRoom(OrderVO orderVO, int change) throws RemoteException;

    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) throws RemoteException;

    public ResultMessage addHotel(HotelVO hotelVO) throws RemoteException;

    public ResultMessage modifyHotel(HotelVO hotelVO) throws RemoteException;

    public ResultMessage deleteHotel(String hotelID) throws RemoteException;

    public ResultMessage modifyDailyRoomInfo(DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException;


}
