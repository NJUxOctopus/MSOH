package businesslogic.hotel_bl;

import businesslogicservice.hotel_blservice.Hotel_BLService;
import dataservice.hotel_dataservice.Hotel_DataService;
import po.CommentPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;
import rmi.RemoteHelper;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class Hotel implements Hotel_BLService {
    private Hotel_DataService hotel_dataService = RemoteHelper.getInstance().getHotelDataService();
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    Timestamp timestamp1 = Timestamp.valueOf(sdf.format(date));

    /**
     * 录入房间
     *
     * @param dailyRoomInfoVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage addDailyRoomInfo(DailyRoomInfoVO dailyRoomInfoVO) throws RemoteException {
        if (hotel_dataService.findHotelByID(dailyRoomInfoVO.hotelID) == null)
            return ResultMessage.Hotel_HotelNotExist;
        long oneDay = 1000 * 60 * 60 * 24;
        List<Timestamp> timestampList = new ArrayList<Timestamp>();
        timestampList.add(dailyRoomInfoVO.date);
        for (int i = 1; i < 30; i++) {
            timestampList.add(new Timestamp(dailyRoomInfoVO.date.getTime() + i * oneDay));
        }
        for (Timestamp timestamp : timestampList) {
            if (hotel_dataService.addDailyRoomInfo(new DailyRoomInfoPO(dailyRoomInfoVO.hotelID, timestamp, null))) ;
            else
                return ResultMessage.Fail;
        }
        return ResultMessage.Hotel_AddRoomSuccess;
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
        for (Timestamp timestamp : list) {
            DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService.getDailyRoomInfo(orderVO.hotelID, timestamp);
            List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
            for (String room : rooms) {//将订单中的房间类型每一个与酒店的房间类型对比
                for (RoomPO roomPO : roomPOList) {
                    if (room.equals(roomPO.getRoomType()))
                        roomPO.setLeftRooms(roomPO.getLeftRooms() + change);
                }
            }
            hotel_dataService.updateDailyRoomInfo(dailyRoomInfoPO);
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
        for (Timestamp timestamp : list) {
            DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService.getDailyRoomInfo(orderVO.hotelID, timestamp);
            List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
            for (String room : rooms) {//将订单中的房间类型每一个与酒店的房间类型对比
                for (RoomPO roomPO : roomPOList) {
                    if (room.equals(roomPO.getRoomType()))
                        roomPO.setReservedRooms(roomPO.getReservedRooms() + change);
                }
            }
            hotel_dataService.updateDailyRoomInfo(dailyRoomInfoPO);
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
        for (Timestamp timestamp : list) {
            DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService.getDailyRoomInfo(orderVO.hotelID, timestamp);
            List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
            for (String room : rooms) {//将订单中的房间类型每一个与酒店的房间类型对比
                for (RoomPO roomPO : roomPOList) {
                    if (room.equals(roomPO.getRoomType()))
                        roomPO.setOccupiedRooms(roomPO.getOccupiedRooms() + change);
                }
            }
            hotel_dataService.updateDailyRoomInfo(dailyRoomInfoPO);
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
        int commentNum = hotel_dataService.getCommentByHotel(orderVO.hotelID).size();
        double score = (commentNum * hotel_dataService.findHotelByID(orderVO.hotelID).getScore() + commentVO.score) / (commentNum + 1);
        if (hotel_dataService.addComment(new CommentPO(score, commentVO.comment, orderVO.customerName, orderVO.customerID
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
        if (hotelVO.area.equals("") || hotelVO.hotelName.equals("") || hotelVO.hotelAddress.equals("")
                || hotelVO.intro.equals("") || hotelVO.city.equals("") || hotelVO.star == -1)
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
        if (hotel_dataService.addHotel(new HotelPO(hotelVO.hotelName, hotelVO.hotelAddress, hotelVO.area, hotelVO.intro, infra, roomType,
                hotelVO.star, 0, hotelVO.license, picUrl, null))) {
            addDailyRoomInfo(new DailyRoomInfoVO(hotelVO.hotelID, timestamp1, null));
            return ResultMessage.Hotel_addHotelSuccess;
        } else
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
        HotelPO hotelPO = hotel_dataService.findHotelByID(hotelVO.hotelID);

        if (hotelPO == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (hotelVO.hotelName.equals("") || hotelVO.hotelAddress.equals("") || hotelVO.area.equals("")
                || hotelVO.intro.equals(""))
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
        if (hotel_dataService.modifyHotel(hotelPO))
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
        if (hotel_dataService.findHotelByID(hotelID) == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (hotel_dataService.deleteHotel(hotel_dataService.findHotelByID(hotelID)))
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
        DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService.getDailyRoomInfo(dailyRoomInfoVO.hotelID, dailyRoomInfoVO.date);
        List<RoomVO> roomVOList = dailyRoomInfoVO.room;
        List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
        int change = 0;
        for (RoomPO roomPO : roomPOList) {
            if (roomPO.getRoomType().equals(roomVOList.get(0).roomType)) {
                change = roomVOList.get(0).leftRooms - roomPO.getLeftRooms();
                roomPO.setLeftRooms(roomVOList.get(0).leftRooms);
            }
        }
        hotel_dataService.updateDailyRoomInfo(dailyRoomInfoPO);
        long oneDay = 1000 * 60 * 60 * 24;
        for (int i = 1; i < 30; i++) {
            Timestamp timestamp = new Timestamp(dailyRoomInfoVO.date.getTime() + i * oneDay);
            DailyRoomInfoPO temp = hotel_dataService.getDailyRoomInfo(dailyRoomInfoVO.hotelID, timestamp);
            List<RoomPO> list = temp.getRoom();
            for (RoomPO roomPO : list) {
                if (roomPO.getRoomType().equals(roomVOList.get(0).roomType)) {
                    roomPO.setLeftRooms(roomPO.getLeftRooms() + change);
                }
            }
            hotel_dataService.updateDailyRoomInfo(dailyRoomInfoPO);
        }
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
        HotelPO hotelPO = hotel_dataService.findHotelByID(clerkVO.hotelID);
        if (hotelPO == null)
            return ResultMessage.Hotel_HotelNotExist;
        if (!hotelPO.getClerkID().equals(""))
            return ResultMessage.Hotel_HasClerk;
        hotelPO.setClerkID(clerkVO.ID);
        return ResultMessage.Clerk_AddClerkSuccess;
    }
}
