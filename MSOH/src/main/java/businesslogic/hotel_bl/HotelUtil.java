package businesslogic.hotel_bl;

import businesslogicservice.hotelUtil_blservice.HotelUtil_BLService;
import dataservice.hotel_dataservice.City_DataService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

/**
 * Created by apple on 16/11/10.
 */
public class HotelUtil implements HotelUtil_BLService {
    City_DataService city_dataService = RemoteHelper.getInstance().getCityDataService();

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
            return null;
        for (int i = 0; i < commentPOs.size(); i++) {
            CommentPO commentPO = commentPOs.get(i);
            commentVOList.add(new CommentVO(commentPO.getScore(), commentPO.getComment(), commentPO.getCustomerName(),
                    commentPO.getCustomerID(), commentPO.getHotelName(), commentPO.getHotelID(), commentPO.getOrderID(), commentPO.getCommentTime()));
        }
        return commentVOList;
    }


    Hotel_DataService_Stub hotel_dataService_stub = new Hotel_DataService_Stub();

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
            return null;
        for (int i = 0; i < hotelPOList.size(); i++) {
            HotelPO hotelPO = hotelPOList.get(i);
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                    hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID(),
                    new java.sql.Timestamp(System.currentTimeMillis())), getComment(hotelPO.getHotelID())));
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
            return new DailyRoomInfoVO(dailyRoomInfoPO.getHotelID(), dailyRoomInfoPO.getDate(), null);
        List<RoomVO> roomVOList = new ArrayList<RoomVO>();
        for (int i = 0; i < roomPOList.size(); i++) {
            RoomPO roomPO = roomPOList.get(i);
            roomVOList.add(new RoomVO(roomPO.getHotelID(), roomPO.getRoomType(), roomPO.getOccupiedRooms(), roomPO.getReservedRooms(),
                    roomPO.getLeftRooms(), roomPO.getPrice()));
        }
        return new DailyRoomInfoVO(dailyRoomInfoPO.getHotelID(), dailyRoomInfoPO.getDate(), roomVOList);
    }

    public List<HotelVO> sortByPrice(List<HotelVO> list) throws RemoteException {
        return null;
    }

    public List<HotelVO> sortByStar(List<HotelVO> list) throws RemoteException {
        return null;
    }

    public List<HotelVO> sortByScore(List<HotelVO> list) throws RemoteException {
        return null;
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
                hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID(),
                new java.sql.Timestamp(System.currentTimeMillis())), getComment(hotelPO.getHotelID()));
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
                    hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID(),
                    new java.sql.Timestamp(System.currentTimeMillis())), getComment(hotelPO.getHotelID())));
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
        if (area.equals(""))
            return null;
        List<HotelPO> hotelPOs = hotel_dataService_stub.getHotels();
        if (hotelPOs == null || hotelPOs.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < hotelPOs.size(); i++) {
            HotelPO hotelPO = hotelPOs.get(i);
            if (hotelPO.getArea().equals(area)) {
                String[] infra = hotelPO.getInfra().split(";");
                String[] picUrl = hotelPO.getPicUrls().split(";");
                String[] roomType = hotelPO.getHotelRoomType().split(";");
                hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                        infra, roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerkID(),
                        hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID(),
                        new java.sql.Timestamp(System.currentTimeMillis())), getComment(hotelPO.getHotelID())));
            }
        }
        return hotelVOList;
    }

    /**
     * 按照星级筛选酒店，得到所有酒店后星级大于要求的都符合
     *
     * @param star
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filterByStar(String star) throws RemoteException {
        if (star.equals(""))
            return getAll();
        List<HotelVO> allHotels = getAll();
        if (allHotels == null || allHotels.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).star >= Integer.parseInt(star)) {
                hotelVOList.add(allHotels.get(i));
            }
        }
        return hotelVOList;
    }

    /**
     * 按照分数区间筛选酒店
     *
     * @param low
     * @param high
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filterByScore(String low, String high) throws RemoteException {
        if (low.equals("") && high.equals(""))
            return getAll();
        List<HotelVO> allHotels = getAll();
        if (allHotels == null || allHotels.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < allHotels.size(); i++) {
            HotelVO hotelVO = allHotels.get(i);
            if (low.equals(""))
                if (hotelVO.score <= Double.parseDouble(high))
                    hotelVOList.add(hotelVO);
            if (high.equals(""))
                if (hotelVO.score >= Double.parseDouble(low))
                    hotelVOList.add(hotelVO);
                else if (hotelVO.score <= Double.parseDouble(high) && hotelVO.score >= Double.parseDouble(low))
                    hotelVOList.add(hotelVO);
        }
        return hotelVOList;
    }

    /**
     * 按照酒店名筛选酒店
     *
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filterByName(String name) throws RemoteException {
        if (name.equals(""))
            return getAll();
        List<HotelVO> allHotels = getAll();
        if (allHotels == null || allHotels.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).hotelName.contains(name)) {
                hotelVOList.add(allHotels.get(i));
            }
        }
        return hotelVOList;
    }

    /**
     * 按照城市名筛选酒店
     *
     * @param city
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filterByCity(String city) throws RemoteException {
        if (city.equals(""))
            return getAll();
        List<HotelVO> allHotels = getAll();
        if (allHotels == null || allHotels.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).hotelAddress.contains(city)) {
                hotelVOList.add(allHotels.get(i));
            }
        }
        return hotelVOList;
    }

    /**
     * 按照时间筛选酒店
     *
     * @param timestamp1
     * @param timestamp2
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filterByDate(Timestamp timestamp1, Timestamp timestamp2) throws RemoteException {
        if (timestamp1 == null && timestamp2 == null)
            return getAll();
        List<HotelVO> allHotels = getAll();
        if (allHotels == null || allHotels.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < allHotels.size(); i++) {
            HotelVO hotelVO = allHotels.get(i);
            DailyRoomInfoVO dailyRoomInfoVO = hotelVO.dailyRoomInfo;
            //TODO
        }
        return hotelVOList;
    }

    /**
     * 按照商圈筛选酒店
     *
     * @param area
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filterByArea(String area) throws RemoteException {
        if (area.equals(""))
            return getAll();
        List<HotelVO> allHotels = getAll();
        if (allHotels == null || allHotels.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).area.equals(area)) {
                hotelVOList.add(allHotels.get(i));
            }
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
     * @param city
     * @param timestamp1
     * @param timestamp2
     * @param area
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> filter(String star, String name, String low, String high, String city, Timestamp timestamp1, Timestamp timestamp2,
                                String area) throws RemoteException {
        List<HotelVO> hotelVOList = filterByStar(star);
        hotelVOList.retainAll(filterByName(name));
        hotelVOList.retainAll(filterByScore(low, high));
        hotelVOList.retainAll(filterByCity(city));
        hotelVOList.retainAll(filterByDate(timestamp1, timestamp2));
        hotelVOList.retainAll(filterByArea(area));
        return hotelVOList;
    }

    /**
     * @param address
     * @param area
     * @param timestamp1
     * @param timestamp2
     * @param star
     * @param low
     * @param high
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> searchHotel(String address, String area, Timestamp timestamp1, Timestamp timestamp2,
                                     String star, String low, String high) throws RemoteException {
        List<HotelVO> hotelVOList = filterByCity(address);
        hotelVOList.retainAll(filterByArea(area));
        hotelVOList.retainAll(filterByDate(timestamp1, timestamp2));
        hotelVOList.retainAll(filterByStar(star));
        hotelVOList.retainAll(filterByScore(low, high));
        if (hotelVOList.isEmpty())
            return null;
        return hotelVOList;
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
