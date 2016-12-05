package dataservice.hotel_dataservice;

import po.CommentPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author zqh
 */
public interface Hotel_DataService extends Remote {
    // 新增酒店
    public boolean addHotel(HotelPO po) throws RemoteException;

    // 更新酒店信息
    public boolean modifyHotel(HotelPO po) throws RemoteException;

    // 删除酒店
    public boolean deleteHotel(HotelPO po) throws RemoteException;

    // 得到所有酒店信息
    public List<HotelPO> getHotels() throws IOException, ClassNotFoundException;

    // 根据酒店ID查找酒店
    public HotelPO findHotelByID(String hotelID) throws RemoteException;

    // 根据酒店名字查找酒店
    public List<HotelPO> findHotelByName(String hotelName) throws IOException, ClassNotFoundException;

    // 新增酒店房间
    public boolean addRoom(RoomPO po) throws RemoteException;

    // 更新酒店房间信息
    public boolean modifyRoom(RoomPO po) throws RemoteException;

    // 删除酒店房间
    public boolean deleteRoom(RoomPO po) throws RemoteException;

    // 获得房间信息
    public RoomPO getRoom(String hotelID, String roomName) throws RemoteException;

    // 获取某酒店的所有房间
    public List<RoomPO> getHotelRooms(String hotelID) throws IOException, ClassNotFoundException;

    // 获得某酒店的每日房间信息
    public DailyRoomInfoPO getDailyRoomInfo(String hotelID, Timestamp date) throws RemoteException;

    // 新增某酒店的每日房间信息
    public boolean addDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException;

    // 删除某酒店的每日房间信息
    public boolean deleteDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException;

    // 更新某酒店的每日房间信息
    public boolean updateDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException;

    // 新增评价
    public boolean addComment(CommentPO po) throws RemoteException;

    // 根据酒店获得评价
    public List<CommentPO> getCommentByHotel(String hotelID) throws IOException, ClassNotFoundException;

    // 根据订单获得评价
    public CommentPO getCommentByOrder(String orderID) throws RemoteException;

}
