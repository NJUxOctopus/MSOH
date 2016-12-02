package businesslogic.hotel_bl;

import businesslogic.clerk_bl.Clerk;
import businesslogic.clerk_bl.ClerkUtil;
import businesslogicservice.hotel_blservice.Hotel_BLService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import po.HotelPO;
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

    /**
     * 增加房间
     *
     * @param roomVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addRoom(RoomVO roomVO) throws RemoteException {
        if (hotel_dataService_stub.findHotelByID(roomVO.hotelID) == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (roomVO.leftRooms < 0 || roomVO.occupiedRooms < 0 || roomVO.reservedRooms < 0 || roomVO.price < 0)
            return ResultMessage.DataFormatWrong;
        if (roomVO.roomType.equals(""))
            return ResultMessage.Blank;
        if (hotel_dataService_stub.addRoom(new RoomPO(roomVO.hotelID, roomVO.roomType, roomVO.occupiedRooms,
                roomVO.reservedRooms, roomVO.leftRooms, roomVO.price)))
            return ResultMessage.Hotel_AddRoomSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 修改房间
     *
     * @param roomVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyRoom(RoomVO roomVO) throws RemoteException {
        if (hotel_dataService_stub.findHotelByID(roomVO.hotelID) == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (roomVO.roomType.equals(""))
            return ResultMessage.Blank;
        if (roomVO.leftRooms < 0 || roomVO.occupiedRooms < 0 || roomVO.reservedRooms < 0 || roomVO.price < 0)
            return ResultMessage.DataFormatWrong;
        RoomPO roomPO = hotel_dataService_stub.getRoom(roomVO.hotelID, roomVO.roomType);
        roomPO.setLeftRooms(roomVO.leftRooms);
        roomPO.setOccupiedRooms(roomVO.occupiedRooms);
        roomPO.setPrice(roomVO.price);
        roomPO.setReservedRooms(roomVO.reservedRooms);
        roomPO.setRoomType(roomVO.roomType);
        if (hotel_dataService_stub.modifyRoom(roomPO))
            return ResultMessage.Hotel_ModifyRoomSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 删除房间
     *
     * @param roomVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteRoom(RoomVO roomVO) throws RemoteException {
        if (roomVO.hotelID.equals("") || roomVO.roomType.equals(""))
            return ResultMessage.Blank;
        RoomPO roomPO = hotel_dataService_stub.getRoom(roomVO.hotelID, roomVO.roomType);
        if (roomPO == null)
            return ResultMessage.Hotel_RoomNotExist;
        if (hotel_dataService_stub.deleteRoom(roomPO))
            return ResultMessage.Hotel_DeleteRoomSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 更改可用房间数量
     *
     * @param ID
     * @param type
     * @param number
     * @param dailyRoomInfoVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeAvailableRoom(String ID, String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        return null;
    }

    /**
     * 更改预定过的房间数量
     *
     * @param type
     * @param number
     * @param dailyRoomInfoVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeReservedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        return null;
    }

    /**
     * 更改已入住的房间数量
     *
     * @param type
     * @param number
     * @param dailyRoomInfoVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeOccupiedRoom(String type, int number, DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        return null;
    }

    /**
     * 增加评论
     *
     * @param commentVO
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) throws RemoteException {
        return null;
    }

    /**
     * 得到每日房间信息
     *
     * @param ID
     * @param Date
     * @return
     * @throws RemoteException
     */
    public List<DailyRoomInfoVO> getDailyRoomInfo(String ID, Date Date) throws RemoteException {
        return null;
    }

    /**
     * 增加酒店
     *
     * @param hotelVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addHotel(HotelVO hotelVO) throws RemoteException {
        if (hotelVO.area.equals("") || hotelVO.hotelName.equals("") || hotelVO.hotelAddress.equals(""))
            return ResultMessage.Blank;
        String infra = "";
        for (int i = 0; i < hotelVO.infra.length; i++) {
            if (i != hotelVO.infra.length - 1)
                infra += hotelVO.infra[i] + ";";
            else
                infra += hotelVO.infra[i];
        }
        String roomType = "";
        for (int i = 0; i < hotelVO.roomType.length; i++) {
            if (i != hotelVO.roomType.length - 1)
                roomType += hotelVO.roomType[i] + ";";
            else
                roomType += hotelVO.roomType[i];
        }
        String picUrl = "";
        for (int i = 0; i < hotelVO.picUrls.length; i++) {
            if (i != hotelVO.picUrls.length - 1)
                picUrl += hotelVO.picUrls[i] + ";";
            else
                picUrl += hotelVO.picUrls[i];
        }
        if (hotel_dataService_stub.addHotel(new HotelPO(hotelVO.hotelName, hotelVO.hotelAddress, hotelVO.area, hotelVO.intro, infra, roomType,
                hotelVO.star, 0, hotelVO.license, picUrl, null, null, null, null)))
            return ResultMessage.Hotel_addHotelSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 修改酒店
     *
     * @param hotelVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyHotel(HotelVO hotelVO) throws RemoteException {
        return null;
    }

    /**
     * 删除酒店
     *
     * @param hotelVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteHotel(HotelVO hotelVO) throws RemoteException {
        return null;
    }

    /**
     * 修改每日房间信息
     *
     * @param list
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyDailyRoomInfo(List<DailyRoomInfoVO> list) throws RemoteException {
        return null;
    }
}
