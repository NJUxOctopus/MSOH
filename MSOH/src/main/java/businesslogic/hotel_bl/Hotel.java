package businesslogic.hotel_bl;

import businesslogic.clerk_bl.Clerk;
import businesslogic.clerk_bl.ClerkUtil;
import businesslogic.order_bl.OrderUtil;
import businesslogicservice.hotel_blservice.Hotel_BLService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import po.*;
import util.CalculateDays;
import util.OrderStatus;
import util.ResultMessage;
import util.WorkerPosition;
import vo.*;

import java.rmi.RemoteException;
import java.sql.Timestamp;
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
     * 更改剩余房间数量，change 值纯属一个标记值，因为担心传来的ordervo的订单状态，如果是生成订单后，该标记位为－1，结束订单时标记位为1
     *
     * @param orderVO
     * @param change
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeAvailableRoom(OrderVO orderVO, int change) throws RemoteException {
        long oneDay = 1000 * 60 * 60 * 24;
        Timestamp startTime = orderVO.estimatedCheckinTime;
        Timestamp endTime = orderVO.estimatedCheckoutTime;
        String[] rooms = orderVO.rooms;
        long days = (endTime.getTime() - startTime.getTime()) / oneDay;//算共住多少天
        List<Timestamp> list = new ArrayList<Timestamp>();
        for (int i = 0; i < days; i++) {
            list.add(new Timestamp(startTime.getTime() + i * oneDay));
        }
        for (int j = 0; j < list.size(); j++) {//从开始到结束每一天循环
            DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService_stub.getDailyRoomInfo(orderVO.hotelID, list.get(j));
            List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
            for (int k = 0; k < rooms.length; k++) {//将订单中的房间类型每一个与酒店的房间类型对比
                for (int m = 0; m < roomPOList.size(); m++) {
                    RoomPO roomPO = roomPOList.get(m);
                    if (rooms[k].equals(roomPO.getRoomType()))
                        roomPO.setLeftRooms(roomPO.getLeftRooms() + change);
                }
            }
            hotel_dataService_stub.updateDailyRoomInfo(dailyRoomInfoPO);
        }
        return ResultMessage.Hotel_changeAvailableRoomSuccess;
    }

    /**
     * 更改预定过的房间数量，change 值纯属一个标记值，因为担心传来的ordervo的订单状态，如果是生成订单后，该标记位为1，执行订单时为－1
     *
     * @param orderVO
     * @param change
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeReservedRoom(OrderVO orderVO, int change) throws RemoteException {
        long oneDay = 1000 * 60 * 60 * 24;
        Timestamp startTime = orderVO.estimatedCheckinTime;
        Timestamp endTime = orderVO.estimatedCheckoutTime;
        String[] rooms = orderVO.rooms;
        long days = (endTime.getTime() - startTime.getTime()) / oneDay;//算共住多少天
        List<Timestamp> list = new ArrayList<Timestamp>();
        for (int i = 0; i < days; i++) {
            list.add(new Timestamp(startTime.getTime() + i * oneDay));
        }
        for (int j = 0; j < list.size(); j++) {//从开始到结束每一天循环
            DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService_stub.getDailyRoomInfo(orderVO.hotelID, list.get(j));
            List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
            for (int k = 0; k < rooms.length; k++) {//将订单中的房间类型每一个与酒店的房间类型对比
                for (int m = 0; m < roomPOList.size(); m++) {
                    RoomPO roomPO = roomPOList.get(m);
                    if (rooms[k].equals(roomPO.getRoomType()))
                        roomPO.setReservedRooms(roomPO.getLeftRooms() + change);
                }
            }
            hotel_dataService_stub.updateDailyRoomInfo(dailyRoomInfoPO);
        }
        return ResultMessage.Hotel_changeReservedRoomSuccess;
    }

    /**
     * 更改入住中的房间数量，change 值纯属一个标记值，因为担心传来的ordervo的订单状态，如果是执行订单后，该标记位为－1，结束订单时为1
     *
     * @param orderVO
     * @param change
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeOccupiedRoom(OrderVO orderVO, int change) throws RemoteException {
        long oneDay = 1000 * 60 * 60 * 24;
        Timestamp startTime = orderVO.estimatedCheckinTime;
        Timestamp endTime = orderVO.estimatedCheckoutTime;
        String[] rooms = orderVO.rooms;
        long days = (endTime.getTime() - startTime.getTime()) / oneDay;//算共住多少天
        List<Timestamp> list = new ArrayList<Timestamp>();
        for (int i = 0; i < days; i++) {
            list.add(new Timestamp(startTime.getTime() + i * oneDay));
        }
        for (int j = 0; j < list.size(); j++) {//从开始到结束每一天循环
            DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService_stub.getDailyRoomInfo(orderVO.hotelID, list.get(j));
            List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
            for (int k = 0; k < rooms.length; k++) {//将订单中的房间类型每一个与酒店的房间类型对比
                for (int m = 0; m < roomPOList.size(); m++) {
                    RoomPO roomPO = roomPOList.get(m);
                    if (rooms[k].equals(roomPO.getRoomType()))
                        roomPO.setOccupiedRooms(roomPO.getLeftRooms() + change);
                }
            }
            hotel_dataService_stub.updateDailyRoomInfo(dailyRoomInfoPO);
        }
        return ResultMessage.Hotel_changeOccupiedRoomSuccess;
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
                hotelVO.star, 0, hotelVO.license, picUrl, null)))
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
