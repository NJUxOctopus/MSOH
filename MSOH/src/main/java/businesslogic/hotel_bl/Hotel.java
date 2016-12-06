package businesslogic.hotel_bl;

import businesslogic.clerk_bl.Clerk;
import businesslogic.clerk_bl.ClerkUtil;
import businesslogicservice.hotel_blservice.Hotel_BLService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import po.*;
import util.ResultMessage;
import util.WorkerPosition;
import vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Hotel implements Hotel_BLService {
    Hotel_DataService_Stub hotel_dataService_stub = new Hotel_DataService_Stub();

    /**
     * 录入房间
     *
     * @param dailyRoomInfoVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addDailyRoomInfo(DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        if (hotel_dataService_stub.findHotelByID(dailyRoomInfoVO.hotelID) == null)
            return ResultMessage.Hotel_HotelNotExist;
        List<RoomVO> roomVOList = dailyRoomInfoVO.room;
        if (roomVOList == null || roomVOList.isEmpty())
            return ResultMessage.Blank;
        List<RoomPO> roomPOList = new ArrayList<RoomPO>();
        for (int i = 0; i < roomVOList.size(); i++) {
            RoomVO roomVO = roomVOList.get(i);
            roomPOList.add(new RoomPO(roomVO.hotelID, roomVO.roomType, roomVO.occupiedRooms, roomVO.reservedRooms, roomVO.leftRooms, roomVO.price));
        }
        if (hotel_dataService_stub.addDailyRoomInfo(new DailyRoomInfoPO(dailyRoomInfoVO.hotelID, dailyRoomInfoVO.date, roomPOList)))
            return ResultMessage.Hotel_AddRoomSuccess;
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
        if (commentVO.score < 0)
            return ResultMessage.DataFormatWrong;
        if (commentVO.comment.equals(""))
            commentVO.comment = "默认好评";
        int commentNum = hotel_dataService_stub.getCommentByHotel(orderVO.hotelID).size();
        double score = (commentNum * hotel_dataService_stub.findHotelByID(orderVO.hotelID).getScore() + commentVO.score) / (commentNum + 1);
        if (hotel_dataService_stub.addComment(new CommentPO(score, commentVO.comment, orderVO.customerName, orderVO.customerID
                , orderVO.hotelName, orderVO.hotelID, orderVO.orderID, commentVO.commentTime)))
            return ResultMessage.Hotel_addCommentSuccess;
        else
            return ResultMessage.Fail;

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
                hotelVO.star, 0, hotelVO.license, picUrl, null, null)))
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
        HotelPO hotelPO = hotel_dataService_stub.findHotelByID(hotelVO.hotelID);
        if (hotelPO == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (hotelVO.hotelName.equals("") || hotelVO.hotelAddress.equals("") || hotelVO.area.equals(""))
            return ResultMessage.Blank;
        if (hotelVO.star < 0)
            return ResultMessage.DataFormatWrong;
        hotelPO.setArea(hotelVO.area);
        hotelPO.setHotelName(hotelVO.hotelName);
        hotelPO.setHotelAddress(hotelVO.hotelAddress);
        String infra = "";
        for (int i = 0; i < hotelVO.infra.length; i++) {
            if (i != hotelVO.infra.length - 1)
                infra += hotelVO.infra[i] + ";";
            else
                infra += hotelVO.infra[i];
        }
        hotelPO.setInfra(infra);
        hotelPO.setIntro(hotelVO.intro);
        hotelPO.setStar(hotelVO.star);
        if (hotel_dataService_stub.modifyHotel(hotelPO))
            return ResultMessage.Hotel_modifyHotelInfoSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 删除酒店
     *
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public ResultMessage deleteHotel(String hotelID) throws RemoteException {
        if (hotel_dataService_stub.findHotelByID(hotelID) == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (hotel_dataService_stub.deleteHotel(hotel_dataService_stub.findHotelByID(hotelID)))
            return ResultMessage.Hotel_deleteHotelSuccess;
        else
            return ResultMessage.Fail;
    }

    /**
     * 修改每日房间信息
     *
     * @param dailyRoomInfoVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyDailyRoomInfo(DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService_stub.getDailyRoomInfo(dailyRoomInfoVO.hotelID, dailyRoomInfoVO.date);
        List<RoomVO> roomVOList = dailyRoomInfoVO.room;
        List<RoomPO> roomPOList = new ArrayList<RoomPO>();
        for (int i = 0; i < roomVOList.size(); i++) {
            RoomVO roomVO = roomVOList.get(i);
            if (roomVO.reservedRooms < 0 || roomVO.occupiedRooms < 0 || roomVO.leftRooms < 0)
                return ResultMessage.DataFormatWrong;
            roomPOList.add(new RoomPO(roomVO.hotelID, roomVO.roomType, roomVO.occupiedRooms, roomVO.reservedRooms, roomVO.leftRooms
                    , roomVO.price));
        }
        dailyRoomInfoPO.setRoom(roomPOList);

        return ResultMessage.Hotel_ModifyDailyRoomInfoSuccess;
    }


    /**
     * 增加酒店工作人员
     *
     * @param clerkVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addClerk(ClerkVO clerkVO) throws RemoteException {
        HotelPO hotelPO = hotel_dataService_stub.findHotelByID(clerkVO.hotelID);
        if (hotelPO == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (!hotelPO.getClerkID().equals(""))
            return ResultMessage.Hotel_HasClerk;
        hotelPO.setClerkID(clerkVO.ID);
        return ResultMessage.Clerk_AddClerkSuccess;
    }
}
