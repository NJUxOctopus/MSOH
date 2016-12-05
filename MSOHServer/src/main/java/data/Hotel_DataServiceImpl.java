package data;

import DataHelper.CommentDataHelper;
import DataHelper.DataFactory;
import DataHelper.HotelDataHelper;
import DataHelper.RoomDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.hotel_dataservice.Hotel_DataService;
import po.CommentPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;
import util.CopyUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zqh on 2016/12/5.
 */
public class Hotel_DataServiceImpl implements Hotel_DataService {
    private DataFactory dataFactory;

    private static Hotel_DataServiceImpl hotel_dataService;

    private HotelDataHelper hotelDataHelper;

    private CommentDataHelper commentDataHelper;

    private RoomDataHelper roomDataHelper;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return hotelDataService的实例
     */
    public static Hotel_DataServiceImpl getInstance() {
        if (hotel_dataService == null) {
            hotel_dataService = new Hotel_DataServiceImpl();
        }
        return hotel_dataService;
    }

    private Hotel_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        hotelDataHelper = dataFactory.getHotelDataHelper();
        commentDataHelper = dataFactory.getCommentDataHelper();
        roomDataHelper = dataFactory.getRoomDataHelper();
    }

    /**
     * 新增酒店
     * @param po
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean addHotel(HotelPO po) throws RemoteException {
        // 自动生成酒店ID
        String hotelID = generateHotelID();
        // 设置酒店ID
        po.setHotelID(hotelID);

        return hotelDataHelper.addHotel(po);
    }

    /**
     * 修改酒店信息
     * @param po
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean modifyHotel(HotelPO po) throws RemoteException {
        return hotelDataHelper.modifyHotel(po);
    }

    /**
     * 删除酒店
     * @param po
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean deleteHotel(HotelPO po) throws RemoteException {
        return hotelDataHelper.deleteHotel(po);
    }

    /**
     * 获得所有酒店
     * @return 所有酒店列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<HotelPO> getHotels() throws IOException, ClassNotFoundException {
        List<HotelPO> hotelList=hotelDataHelper.getHotels();

        if(hotelList.isEmpty()||hotelList==null){
            return null;
        }

        List<HotelPO> list= CopyUtil.deepCopy(hotelList);

        return list;
    }

    /**
     * 根据ID查找酒店
     * @param hotelID
     * @return 与酒店ID匹配的酒店信息
     * @throws RemoteException
     */
    public HotelPO findHotelByID(String hotelID) throws RemoteException {
        HotelPO hotel=hotelDataHelper.getHotelByID(hotelID);

        if(hotel==null){
            return null;
        }

        return (HotelPO)hotel.clone();
    }

    /**
     * 根据酒店名称查找酒店
     * @param hotelName
     * @return 与酒店名称匹配的所有酒店
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<HotelPO> findHotelByName(String hotelName) throws IOException, ClassNotFoundException {
        List<HotelPO> hotelList=hotelDataHelper.getHotelByName(hotelName);

        if(hotelList.isEmpty()||hotelList==null){
            return null;
        }

        List<HotelPO> list=CopyUtil.deepCopy(hotelList);

        return list;
    }

    public boolean addRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public boolean modifyRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public boolean deleteRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public RoomPO getRoom(String hotelID, String roomName) throws RemoteException {
        return null;
    }

    public List<RoomPO> getHotelRooms(String hotelID) throws IOException, ClassNotFoundException {
        return null;
    }

    public DailyRoomInfoPO getDailyRoomInfo(String hotelID, Timestamp date) throws RemoteException {
        return null;
    }

    public boolean addDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        return false;
    }

    public boolean deleteDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        return false;
    }

    public boolean updateDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        return false;
    }

    /**
     * 新增评价
     * @param po
     * @return
     * @throws RemoteException
     */
    public boolean addComment(CommentPO po) throws RemoteException {
        return commentDataHelper.addComment(po);
    }

    /**
     * 根据酒店ID获得酒店评价
     * @param hotelID
     * @return 该酒店的所有评价列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<CommentPO> getCommentByHotel(String hotelID) throws IOException, ClassNotFoundException {
        List<CommentPO> commentList=commentDataHelper.getCommentsByHotel(hotelID);

        if(commentList.isEmpty()||commentList==null){
            return null;
        }

        List<CommentPO> list=CopyUtil.deepCopy(commentList);

        return list;
    }

    /**
     * 根据订单获得评价
     * @param orderID
     * @return
     * @throws RemoteException
     */
    public CommentPO getCommentByOrder(String orderID) throws RemoteException {
        CommentPO comment=commentDataHelper.getCommentByOrder(orderID);

        if(comment==null){
            return null;
        }

        return (CommentPO)comment.clone();
    }

    /**
     * 生成酒店ID
     * 格式：20161205
     * @return 酒店ID
     */
    private String generateHotelID() {
        Date today = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String hotelID = dateFormat.format(today);

        return hotelID;
    }
}
