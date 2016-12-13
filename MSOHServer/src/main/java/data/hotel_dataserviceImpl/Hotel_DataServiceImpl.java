package data.hotel_dataserviceImpl;

import DataHelper.hotelDataHelper.CommentDataHelper;
import DataHelper.DataFactory;
import DataHelper.hotelDataHelper.HotelDataHelper;
import DataHelper.hotelDataHelper.RoomDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.hotel_dataservice.Hotel_DataService;
import po.CommentPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;
import util.CopyUtil;
import util.EncryptionUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/5.
 */
@SuppressWarnings(value = {"Duplicates"})
public class Hotel_DataServiceImpl implements Hotel_DataService {
    private DataFactory dataFactory;

    private static Hotel_DataServiceImpl hotel_dataService;

    private HotelDataHelper hotelDataHelper;

    private CommentDataHelper commentDataHelper;

    private RoomDataHelper roomDataHelper;

    private static final String key = "20162017";

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
     *
     * @param po
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean addHotel(HotelPO po) throws IOException, ClassNotFoundException {
        // 自动生成酒店ID
        String hotelID = generateHotelID();
        // 设置酒店ID
        po.setHotelID(hotelID);
        // 酒店工作人员ID加密
        String clerkID = EncryptionUtil.encode(key, po.getClerkID());
        po.setClerkID(clerkID);

        return hotelDataHelper.addHotel(po);
    }

    /**
     * 修改酒店信息
     *
     * @param hotelPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean modifyHotel(HotelPO hotelPO) throws RemoteException {
        // 数据库中的主键存在，否则更新不成功
        if (hotelPO.getHotelID() == null) {
            return false;
        }
        // 酒店工作人员ID加密
        String clerkID = EncryptionUtil.encode(key, hotelPO.getClerkID());
        hotelPO.setClerkID(clerkID);

        return hotelDataHelper.modifyHotel(hotelPO);
    }

    /**
     * 删除酒店
     *
     * @param hotelPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean deleteHotel(HotelPO hotelPO) throws RemoteException {
        // 数据库中的主键存在，否则删除不成功
        if (hotelPO.getHotelID() == null) {
            return false;
        }
        // 酒店工作人员ID加密
        String clerkID = EncryptionUtil.encode(key, hotelPO.getClerkID());
        hotelPO.setClerkID(clerkID);

        return hotelDataHelper.deleteHotel(hotelPO);
    }

    /**
     * 获得所有酒店
     *
     * @return 所有酒店列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<HotelPO> getHotels() throws IOException, ClassNotFoundException {
        List<HotelPO> hotelList = hotelDataHelper.getHotels();

        if (hotelList == null || hotelList.isEmpty()) {
            return new ArrayList<HotelPO>();
        } else {
            for (HotelPO hotel : hotelList) {
                // 酒店工作人员ID解密
                hotel.setClerkID(EncryptionUtil.decode(key, hotel.getClerkID()));
            }
        }


        List<HotelPO> list = CopyUtil.deepCopy(hotelList);

        return list;
    }

    /**
     * 根据ID查找酒店
     *
     * @param hotelID
     * @return 与酒店ID匹配的酒店信息
     * @throws RemoteException
     */
    public HotelPO findHotelByID(String hotelID) throws RemoteException {
        HotelPO hotel = hotelDataHelper.getHotelByID(hotelID);

        if (hotel == null) {
            return null;
        } else {
            // 酒店工作人员ID解密
            hotel.setClerkID(EncryptionUtil.decode(key, hotel.getClerkID()));
        }

        return (HotelPO) hotel.clone();
    }

    /**
     * 根据酒店名称查找酒店
     *
     * @param hotelName
     * @return 与酒店名称匹配的所有酒店
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<HotelPO> findHotelByName(String hotelName) throws IOException, ClassNotFoundException {
        List<HotelPO> hotelList = hotelDataHelper.getHotelByName(hotelName);

        if (hotelList == null || hotelList.isEmpty()) {
            return new ArrayList<HotelPO>();
        } else {
            for (HotelPO hotel : hotelList) {
                // 酒店工作人员ID解密
                hotel.setClerkID(EncryptionUtil.decode(key, hotel.getClerkID()));
            }
        }

        List<HotelPO> list = CopyUtil.deepCopy(hotelList);

        return list;
    }

    /**
     * 根据商圈获得酒店
     *
     * @param areaName
     * @return 在该商圈内的酒店
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<HotelPO> getHotelByArea(String areaName) throws IOException, ClassNotFoundException {
        List<HotelPO> hotelInThisArea = hotelDataHelper.getHotelByArea(areaName);

        if (hotelInThisArea == null || hotelInThisArea.isEmpty()) {
            return new ArrayList<HotelPO>();
        } else {
            for (HotelPO hotel : hotelInThisArea) {
                // 酒店工作人员ID解密
                hotel.setClerkID(EncryptionUtil.decode(key, hotel.getClerkID()));
            }
        }

        List<HotelPO> list = CopyUtil.deepCopy(hotelInThisArea);

        return list;
    }
//    public boolean addRoom(RoomPO po) throws RemoteException {
//        return roomDataHelper.addRoom(po);
//    }
//
//    public boolean modifyRoom(RoomPO po) throws RemoteException {
//        return roomDataHelper.modifyRoom(po);
//    }
//
//    public boolean deleteRoom(RoomPO po) throws RemoteException {
//        return roomDataHelper.deleteRoom(po);
//    }
//
//    public RoomPO getRoom(String hotelID, String roomName) throws RemoteException {
//        return null;
//    }
//
//    public List<RoomPO> getHotelRooms(String hotelID) throws IOException, ClassNotFoundException {
//        return null;
//    }

    /**
     * 根据酒店ID和日期，获得该酒店当日的房间信息
     *
     * @param hotelID
     * @param date
     * @return 该酒店当日的房间信息
     * @throws RemoteException
     */
    public DailyRoomInfoPO getDailyRoomInfo(String hotelID, Timestamp date) throws RemoteException {
        List<RoomPO> roomList = roomDataHelper.getRoomsByHotel(hotelID);

        if (roomList == null || roomList.isEmpty()) {
            return null;
        }

        List<RoomPO> list = new ArrayList<RoomPO>();

        for (RoomPO roomPO : roomList) {
            if (roomPO.getDate().equals(date)) {
                list.add(roomPO);
            }
        }

        DailyRoomInfoPO dailyRoomInfoPO = new DailyRoomInfoPO(hotelID, date, list);

        return (DailyRoomInfoPO) dailyRoomInfoPO.clone();
    }

    /**
     * 新增每日客房信息
     *
     * @param dailyRoomInfoPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean addDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        boolean isSuccess;

        for (RoomPO roomPO : dailyRoomInfoPO.getRoom()) {
            isSuccess = roomDataHelper.addRoom(roomPO);
            if (!isSuccess) {
                return false;
            }
        }

        return true;
    }

    /**
     * 删除每日客房信息
     *
     * @param dailyRoomInfoPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean deleteDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        boolean isSuccess;

        for (RoomPO roomPO : dailyRoomInfoPO.getRoom()) {
            isSuccess = roomDataHelper.deleteRoom(roomPO);
            if (!isSuccess) {
                return false;
            }
        }

        return true;
    }

    /**
     * 更新每日客房信息
     *
     * @param dailyRoomInfoPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean updateDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        boolean isSuccess;

        for (RoomPO roomPO : dailyRoomInfoPO.getRoom()) {

            isSuccess = roomDataHelper.modifyRoom(roomPO);
            if (!isSuccess) {
                return false;
            }
        }

        return true;
    }

    /**
     * 新增评价
     *
     * @param po
     * @return
     * @throws RemoteException
     */
    public boolean addComment(CommentPO po) throws RemoteException {
        // 客户姓名、ID加密
        String customerName = EncryptionUtil.encode(key, po.getCustomerName());
        String customerID = EncryptionUtil.encode(key, po.getCustomerID());
        po.setCustomerName(customerName);
        po.setCustomerID(customerID);

        return commentDataHelper.addComment(po);
    }

    /**
     * 根据酒店ID获得酒店评价
     *
     * @param hotelID
     * @return 该酒店的所有评价列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<CommentPO> getCommentByHotel(String hotelID) throws IOException, ClassNotFoundException {
        List<CommentPO> commentList = commentDataHelper.getCommentsByHotel(hotelID);

        if (commentList == null || commentList.isEmpty()) {
            return new ArrayList<CommentPO>();
        } else {
            for (CommentPO comment : commentList) {
                // 客户姓名、ID解密
                comment.setCustomerName(EncryptionUtil.decode(key, comment.getCustomerName()));
                comment.setCustomerID(EncryptionUtil.decode(key, comment.getCustomerID()));
            }
        }

        List<CommentPO> list = CopyUtil.deepCopy(commentList);

        return list;
    }

    /**
     * 根据订单获得评价
     *
     * @param orderID
     * @return
     * @throws RemoteException
     */
    public CommentPO getCommentByOrder(String orderID) throws RemoteException {
        CommentPO comment = commentDataHelper.getCommentByOrder(orderID);

        if (comment == null) {
            return null;
        }

        // 客户姓名、ID解密
        comment.setCustomerName(EncryptionUtil.decode(key, comment.getCustomerName()));
        comment.setCustomerID(EncryptionUtil.decode(key, comment.getCustomerID()));

        return (CommentPO) comment.clone();
    }


    /**
     * 生成酒店ID
     * 格式：20161205
     *
     * @return 酒店ID
     */
    private String generateHotelID() throws ClassNotFoundException, IOException {
        int id = 10000000;

        int size = 0;
        List<HotelPO> list = getHotels();

        if (list == null || list.isEmpty()) {
            size = 0;
        } else {
            size = list.size();
        }

        id += (size + 1);
        String hotelID = String.valueOf(id);

        return hotelID;
    }
}
