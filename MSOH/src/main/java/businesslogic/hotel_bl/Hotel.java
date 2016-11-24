package businesslogic.hotel_bl;

import businesslogicservice.hotel_blservice.Hotel_BLService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import po.RoomPO;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Hotel implements Hotel_BLService {
    Hotel_DataService_Stub hotel_dataService_stub = new Hotel_DataService_Stub();

    public ResultMessage addRoom(RoomVO roomVO) throws RemoteException {
        if(hotel_dataService_stub.findHotelByID(roomVO.hotelID)==null)
            return ResultMessage.Hotel_HotelNotExist;
        if(roomVO.leftRooms<0||roomVO.occupiedRooms<0||roomVO.reservedRooms<0||roomVO.price<0)
            return ResultMessage.DataFormatWrong;
        if(roomVO.roomType.equals(""))
            return ResultMessage.Blank;
        hotel_dataService_stub.addRoom(new RoomPO(roomVO.hotelID,roomVO.roomType,roomVO.occupiedRooms,
                roomVO.reservedRooms,roomVO.leftRooms,roomVO.price));
        return ResultMessage.Hotel_AddRoomSuccess;
    }

    public ResultMessage modifyRoom(RoomVO roomVO) throws RemoteException {
        if(hotel_dataService_stub.findHotelByID(roomVO.hotelID)==null)
            return ResultMessage.Hotel_HotelNotExist;
        if(roomVO.roomType.equals(""))
            return ResultMessage.Blank;
        if(roomVO.leftRooms<0||roomVO.occupiedRooms<0||roomVO.reservedRooms<0||roomVO.price<0)
            return ResultMessage.DataFormatWrong;
        RoomPO roomPO = hotel_dataService_stub.getRoom(roomVO.hotelID,roomVO.roomType);
        roomPO.setLeftRooms(roomVO.leftRooms);
        roomPO.setOccupiedRooms(roomVO.occupiedRooms);
        roomPO.setPrice(roomVO.price);
        roomPO.setReservedRooms(roomVO.reservedRooms);
        roomPO.setRoomType(roomVO.roomType);
        hotel_dataService_stub.modifyRoom(roomPO);
        return ResultMessage.Hotel_ModifyRoomSuccess;
    }

    public ResultMessage deleteRoom(RoomVO roomVO) throws RemoteException {
        return null;
    }

    public ResultMessage changeAvailableRoom(String ID, String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        return null;
    }

    public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        return null;
    }

    public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        return null;
    }

    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) throws RemoteException {
        return null;
    }

    public List<DailyRoomInfoVO> getDailyRoomInfo(String ID, Date Date) throws RemoteException {
        return null;
    }

    public List<RoomVO> getRoom(String ID) throws RemoteException {
        return null;
    }

    public ResultMessage addHotel(HotelVO roomVO) throws RemoteException {
        return null;
    }

    public ResultMessage modifyHotel(HotelVO roomVO) throws RemoteException {
        return null;
    }

    public ResultMessage deleteHotel(HotelVO roomVO) throws RemoteException {
        return null;
    }

    public ResultMessage addToListOfHotelReservedByCustomer(HotelVO hotelVO, CustomerVO customerVO) throws RemoteException {
        return null;
    }

    public ResultMessage modifyDailyRoomInfo(List<DailyRoomInfoVO> list) throws RemoteException {
        return null;
    }
}
