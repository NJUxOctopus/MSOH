package businesslogic.hotel_bl;

import dataservice.hotel_dataservice.Hotel_DataService;
import util.filter.*;
import util.sort.sortHotelByScore;
import util.sort.sortHotelByStar;
import businesslogicservice.hotel_blservice.HotelUtil_BLService;
import dataservice.hotel_dataservice.City_DataService;
import po.CommentPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;
import rmi.RemoteHelper;
import util.sort.sortRoomByNum;
import vo.CommentVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Timestamp;

/**
 * Created by apple on 16/11/10.
 */
public class HotelUtil implements HotelUtil_BLService {
    City_DataService city_dataService = RemoteHelper.getInstance().getCityDataService();
    Hotel_DataService hotel_dataService = RemoteHelper.getInstance().getHotelDataService();

    /**
     * 该方法主要是把酒店的评论的po改成vo
     *
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<CommentVO> getComment(String hotelID) throws RemoteException {
        if (hotelID.equals(""))
            return null;
        HotelPO hotelPO = hotel_dataService.findHotelByID(hotelID);
        if (hotelPO == null)
            return null;
        List<CommentPO> commentPOs = hotel_dataService.getCommentByHotel(hotelID);
        List<CommentVO> commentVOList = new ArrayList<CommentVO>();
        if (commentPOs == null || commentPOs.isEmpty())
            return new ArrayList<CommentVO>();
        for (CommentPO commentPO : commentPOs) {
            commentVOList.add(new CommentVO(commentPO.getScore(), commentPO.getComment(), commentPO.getCustomerName(),
                    commentPO.getCustomerID(), commentPO.getHotelName(), commentPO.getHotelID(), commentPO.getOrderID(), commentPO.getCommentTime()));
        }
        return commentVOList;
    }

    /**
     * 得到所有酒店
     *
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> getAll() throws RemoteException {
        List<HotelPO> hotelPOList = hotel_dataService.getHotels();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        if (hotelPOList == null || hotelPOList.isEmpty())
            return new ArrayList<HotelVO>();
        for (HotelPO hotelPO : hotelPOList) {
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            HotelVO hotelVO = new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                    hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID()));
            hotelVO.city=hotelVO.hotelAddress.split(" ")[0];
            hotelVOList.add(hotelVO);
        }
        return hotelVOList;
    }

    /**
     * 得到酒店某一天的房间信息
     *
     * @param hotelID
     * @param timestamp
     * @return
     */
    public DailyRoomInfoVO getDailyRoomInfo(String hotelID, Timestamp timestamp) throws RemoteException {
        DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService.getDailyRoomInfo(hotelID, timestamp);
        List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
        if (roomPOList == null || roomPOList.isEmpty())
            return new DailyRoomInfoVO(dailyRoomInfoPO.getHotelID(), dailyRoomInfoPO.getDate(), new ArrayList<RoomVO>());
        List<RoomVO> roomVOList = new ArrayList<RoomVO>();
        for (int i = 0; i < roomPOList.size(); i++) {
            RoomPO roomPO = roomPOList.get(i);
            roomVOList.add(new RoomVO(roomPO.getHotelID(), roomPO.getRoomType(), roomPO.getOccupiedRooms(), roomPO.getReservedRooms(),
                    roomPO.getLeftRooms(), roomPO.getPrice()));
        }
        return new DailyRoomInfoVO(dailyRoomInfoPO.getHotelID(), dailyRoomInfoPO.getDate(), roomVOList);
    }

    /**
     * 按照星级排序（正序）
     *
     * @param list
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> sortByStar(List<HotelVO> list) throws RemoteException {
        Comparator<HotelVO> comparator = new sortHotelByStar();
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * 按照分数排序（正序）
     *
     * @param list
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> sortByScore(List<HotelVO> list) throws RemoteException {
        Comparator<HotelVO> comparator = new sortHotelByScore();
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * 按照ID搜索酒店
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public HotelVO getByID(String ID) throws RemoteException {
        if (ID.equals(""))
            return null;
        HotelPO hotelPO = hotel_dataService.findHotelByID(ID);
        if (hotelPO == null)
            return null;
        String[] infra = hotelPO.getInfra().split(";");
        String[] picUrl = hotelPO.getPicUrls().split(";");
        String[] roomType = hotelPO.getHotelRoomType().split(";");
        HotelVO hotelVO = new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID()));
        hotelVO.city=hotelVO.hotelAddress.split(" ")[0];
        return hotelVO;
    }

    /**
     * 按照名字搜索酒店列表
     *
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> getByName(String name) throws RemoteException {
        if (name.equals(""))
            return null;
        List<HotelPO> hotelPOs = hotel_dataService.findHotelByName(name);
        if (hotelPOs == null || hotelPOs.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelPO hotelPO : hotelPOs) {
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            HotelVO hotelVO = new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                    hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID()));
            hotelVO.city=hotelVO.hotelAddress.split(" ")[0];
            hotelVOList.add(hotelVO);
        }
        return hotelVOList;
    }

    /**
     * 按照商圈搜索酒店
     *
     * @param area
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> getByArea(String area) throws RemoteException {
        List<HotelPO> hotelPOs = hotel_dataService.getHotelByArea(area);
        if (hotelPOs == null || hotelPOs.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelPO hotelPO : hotelPOs) {
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            HotelVO hotelVO = new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                    hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID()));
            hotelVO.city=hotelVO.hotelAddress.split(" ")[0];
            hotelVOList.add(hotelVO);
        }
        return hotelVOList;
    }


    /**
     * @param hotelID
     * @param roomName
     * @param timestamp
     * @return
     * @throws RemoteException
     */
    public RoomVO getRoomByName(String hotelID, String roomName, Timestamp timestamp) throws RemoteException {
        if (hotelID.equals("") || roomName.equals(""))
            return null;
        DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService.getDailyRoomInfo(hotelID, timestamp);
        if (dailyRoomInfoPO == null)
            return null;
        List<RoomPO> roomPOList = dailyRoomInfoPO.getRoom();
        for (int i = 0; i < roomPOList.size(); i++) {
            RoomPO roomPO = roomPOList.get(i);
            if (roomName.equals(roomPO.getRoomType()))
                return new RoomVO(roomPO.getHotelID(), roomPO.getRoomType(), roomPO.getOccupiedRooms(), roomPO.getReservedRooms(),
                        roomPO.getLeftRooms(), roomPO.getPrice());
        }
        return null;
    }

    /**
     * 得到一段时间内最少房间数量的房间信息
     *
     * @param hotelID
     * @param roomName
     * @param timestamp1
     * @param timestamp2
     * @return
     */
    public RoomVO getBewteenDate(String hotelID, String roomName, Timestamp timestamp1, Timestamp timestamp2)throws RemoteException {
        long oneDay = 1000 * 60 * 60 * 24;
        long days = (timestamp2.getTime() - timestamp1.getTime()) / oneDay;//算共住多少天
        List<Timestamp> list = new ArrayList<Timestamp>();
        for (int i = 0; i < days - 1; i++) {//除了最后一天，获得所有的房间信息
            list.add(new Timestamp(timestamp1.getTime() + i * oneDay));
        }
        List<RoomVO> roomVOList = new ArrayList<RoomVO>();
        for (Timestamp temp : list) {
            roomVOList.add(getRoomByName(hotelID, roomName, temp));
        }
        Comparator<RoomVO> comparator = new sortRoomByNum();
        Collections.sort(roomVOList,comparator);
        //将房间剩余数量从小到大排序，最后返回第一个房间
        return roomVOList.get(0);
    }

    /**
     * 多条件筛选,不断取交集
     *
     * @param star
     * @param low
     * @param high
     * @param timestamp1
     * @param timestamp2
     * @param area
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filter(String star, String name, String low, String high, Timestamp timestamp1, Timestamp timestamp2,
                                String roomType, int roomNum, String area) throws RemoteException {
        List<HotelVO> hotelVOList = getByArea(area);
        FilterCriteria filterCriteriaDate = new FilterCriteriaDateAndRoomType(timestamp1, timestamp2, roomType, roomNum);
        FilterCriteria filterCriteriaStar = new FilterCriteriaStar(star);
        FilterCriteria filterCriteriaScore = new FilterCriteriaScore(low, high);
        return new AndSearchCriteria(filterCriteriaDate, filterCriteriaStar, filterCriteriaScore).meetCriteria(hotelVOList);
    }

    /**
     * @param area
     * @param timestamp1
     * @param timestamp2
     * @param star
     * @param low
     * @param high
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> searchHotel(String area, Timestamp timestamp1, Timestamp timestamp2,
                                     String star, String low, String high) throws RemoteException {
        List<HotelVO> hotelVOList = getByArea(area);
        FilterCriteria filterCriteriaDate = new FilterCriteriaDate(timestamp1, timestamp2);
        FilterCriteria filterCriteriaStar = new FilterCriteriaStar(star);
        FilterCriteria filterCriteriaScore = new FilterCriteriaScore(low, high);
        return new AndSearchCriteria(filterCriteriaDate, filterCriteriaStar, filterCriteriaScore).meetCriteria(hotelVOList);
    }

    /**
     * 通过酒店工作人员的ID获得他所工作的酒店
     *
     * @param clerkID
     * @return
     * @throws RemoteException
     */
    public HotelVO getHotelByClerkID(String clerkID) throws RemoteException {
        if (clerkID.equals(""))
            return null;
        List<HotelVO> hotelVOList = getAll();
        if (hotelVOList == null || hotelVOList.isEmpty())
            return null;
        for (HotelVO hotelVO : hotelVOList) {
            if (hotelVO.clerkID.equals(clerkID))
                return hotelVO;
        }
        return null;
    }

    /**
     * 得到所有的城市信息
     *
     * @return
     * @throws RemoteException
     */
    public List<String> getAllCities() throws RemoteException {
        return city_dataService.getAllCities();
    }

    /**
     * 得到该城市的所有商圈
     *
     * @param city
     * @return
     * @throws RemoteException
     */
    public List<String> getAreaByCity(String city) throws RemoteException {
        return city_dataService.getAreaByCity(city);
    }


}
