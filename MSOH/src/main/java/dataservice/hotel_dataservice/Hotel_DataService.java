package dataservice.hotel_dataservice;

import po.CommentPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.Timestamp;
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
    public List<HotelPO> getHotels() throws RemoteException;

    // 根据酒店ID查找酒店
    public HotelPO findHotelByID(String hotelID) throws RemoteException;

    // 根据酒店名字查找酒店
    public List<HotelPO> findHotelByName(String hotelName) throws RemoteException;

    // 获得某酒店的每日房间信息
    public DailyRoomInfoPO getDailyRoomInfo(String hotelID, java.sql.Timestamp timestamp) throws RemoteException;

    // 新增某酒店的每日房间信息
    public boolean addDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException;

    // 更新某酒店的每日房间信息
    public boolean updateDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException;

    // 新增评价
    public boolean addComment(CommentPO po) throws RemoteException;

    // 根据酒店获得评价
    public List<CommentPO> getCommentByHotel(String hotelID) throws RemoteException;

    // 根据商圈获得酒店
    public List<HotelPO> getHotelByArea(String areaName) throws RemoteException;

}