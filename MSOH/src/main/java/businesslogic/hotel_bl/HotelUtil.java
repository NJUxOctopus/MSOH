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
    Hotel_DataService hotel_dataService_stub = RemoteHelper.getInstance().getHotelDataService();
    Date date1 = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    Timestamp timestamp = Timestamp.valueOf(sdf.format(date1));

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
        HotelPO hotelPO = hotel_dataService_stub.findHotelByID(hotelID);
        if (hotelPO == null)
            return null;
        List<CommentPO> commentPOs = hotel_dataService_stub.getCommentByHotel(hotelID);
        List<CommentVO> commentVOList = new ArrayList<CommentVO>();
        if (commentPOs == null || commentPOs.isEmpty())
            return new ArrayList<CommentVO>();
        for (int i = 0; i < commentPOs.size(); i++) {
            CommentPO commentPO = commentPOs.get(i);
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
        List<HotelPO> hotelPOList = hotel_dataService_stub.getHotels();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        if (hotelPOList == null || hotelPOList.isEmpty())
            return new ArrayList<HotelVO>();
        for (int i = 0; i < hotelPOList.size(); i++) {
            HotelPO hotelPO = hotelPOList.get(i);
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                    hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID())));
        }
        return hotelVOList;
    }

    /**
     * 得到酒店某一天的房间信息
     *
     * @param hotelID
     * @param date
     * @return
     */
    public DailyRoomInfoVO getDailyRoomInfo(String hotelID, Date date) throws RemoteException {
        DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService_stub.getDailyRoomInfo(hotelID, date);
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
        HotelPO hotelPO = hotel_dataService_stub.findHotelByID(ID);
        if (hotelPO == null)
            return null;
        String[] infra = hotelPO.getInfra().split(";");
        String[] picUrl = hotelPO.getPicUrls().split(";");
        String[] roomType = hotelPO.getHotelRoomType().split(";");
        return new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID()));
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
        List<HotelPO> hotelPOs = hotel_dataService_stub.findHotelByName(name);
        if (hotelPOs == null || hotelPOs.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < hotelPOs.size(); i++) {
            HotelPO hotelPO = hotelPOs.get(i);
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                    hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID())));
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
        List<HotelPO> hotelPOs = hotel_dataService_stub.getHotelByArea(area);
        if (hotelPOs == null || hotelPOs.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelPO hotelPO : hotelPOs) {
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                    hotelPO.getHotelID(), null, getComment(hotelPO.getHotelID())));
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
        DailyRoomInfoPO dailyRoomInfoPO = hotel_dataService_stub.getDailyRoomInfo(hotelID, timestamp);
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
        //System.out.print(getAll().size());
        List<HotelVO> hotelVOList = getByArea(area);
        FilterCriteria filterCriteriaDate = new FilterCriteriaDate(timestamp1, timestamp2);
        FilterCriteria filterCriteriaStar = new FilterCriteriaStar(star);
        FilterCriteria filterCriteriaScore = new FilterCriteriaScore(low, high);
        return new AndSearchCriteria(filterCriteriaDate, filterCriteriaStar, filterCriteriaScore).meetCriteria(hotelVOList);
    }

    /**
     * @param timestamp1
     * @param timestamp2
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> searchByDate(Timestamp timestamp1, Timestamp timestamp2) throws RemoteException {
        FilterCriteria searchByDate = new FilterCriteriaDate(timestamp1, timestamp2);
        return searchByDate.meetCriteria(getAll());
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
        for (int i = 0; i < hotelVOList.size(); i++) {
            if (hotelVOList.get(i).clerkID.equals(clerkID))
                return hotelVOList.get(i);
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
