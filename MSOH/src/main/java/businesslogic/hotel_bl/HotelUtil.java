package businesslogic.hotel_bl;

import businesslogicservice.hotelUtil_blservice.HotelUtil_BLService;
import dataservice.hotel_dataservice.Hotel_DataService_Stub;
import po.CommentPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;
import vo.CommentVO;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class HotelUtil implements HotelUtil_BLService {
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
        List<CommentPO> commentPOs = hotelPO.getComment();
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


    /**
     * 该方法主要是把酒店的dailyRoomInfo从po转成vo
     *
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<DailyRoomInfoVO> getDailyRoomInfo(String hotelID) throws RemoteException {
        if (hotelID.equals(""))
            return null;
        HotelPO hotelPO = hotel_dataService_stub.findHotelByID(hotelID);
        if (hotelPO == null)
            return null;
        List<DailyRoomInfoPO> dailyRoomInfoPOs = hotelPO.getDailyRoomInfo();
        List<DailyRoomInfoVO> dailyRoomInfoVOList = new ArrayList<DailyRoomInfoVO>();
        if (dailyRoomInfoPOs == null || dailyRoomInfoPOs.isEmpty())
            return null;
        for (int i = 0; i < dailyRoomInfoPOs.size(); i++) {
            DailyRoomInfoPO dailyRoomInfoPO = dailyRoomInfoPOs.get(i);
            RoomPO[] roomPOs = (RoomPO[]) dailyRoomInfoPO.getRoom().toArray();
            List<RoomVO> roomVOList = new ArrayList<RoomVO>();
            for (int j = 0; j < roomPOs.length; j++) {
                RoomPO roomPO = roomPOs[j];
                roomVOList.add(new RoomVO(roomPO.getHotelID(), roomPO.getRoomType(), roomPO.getOccupiedRooms(),
                        roomPO.getReservedRooms(), roomPO.getLeftRooms(), roomPO.getPrice()));
            }
            dailyRoomInfoVOList.add(new DailyRoomInfoVO(dailyRoomInfoPO.getHotelID(), dailyRoomInfoPO.getDate(), roomVOList));
        }
        return dailyRoomInfoVOList;
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
                    infra,roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
                    hotelPO.getClerk().getPhone(), hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID()), getComment(hotelPO.getHotelID())));
        }
        return hotelVOList;
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
                infra, roomType,hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
                hotelPO.getClerk().getPhone(), hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID()), getComment(hotelPO.getHotelID()));
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
                    infra, roomType,hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
                    hotelPO.getClerk().getPhone(), hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID()), getComment(hotelPO.getHotelID())));
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
        List<HotelPO> hotelPOs = hotel_dataService_stub.getHotelByArea(area);
        if (hotelPOs == null || hotelPOs.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (int i = 0; i < hotelPOs.size(); i++) {
            HotelPO hotelPO = hotelPOs.get(i);
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            String[] roomType = hotelPO.getHotelRoomType().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra,roomType, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
                    hotelPO.getClerk().getPhone(), hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID()), getComment(hotelPO.getHotelID())));
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
            List<DailyRoomInfoVO> dailyRoomInfoVOList = hotelVO.dailyRoomInfo;
            if (dailyRoomInfoVOList != null) {
                for (int j = 0; j < dailyRoomInfoVOList.size(); j++) {
                    DailyRoomInfoVO dailyRoomInfoVO = dailyRoomInfoVOList.get(j);
                    //TODO
                }
            }
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
     * 得到酒店某个名称的房间的信息
     *
     * @param hotelID
     * @param roomName
     * @return
     * @throws RemoteException
     */
    public RoomVO getRoomByName(String hotelID, String roomName) throws RemoteException {
        if (hotelID.equals("") || roomName.equals(""))
            return null;
        List<RoomPO> roomPOList = hotel_dataService_stub.getHotelRooms(hotelID);
        if (roomPOList == null || roomPOList.isEmpty())
            return null;
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


}
