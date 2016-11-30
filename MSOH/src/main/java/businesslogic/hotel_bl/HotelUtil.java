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
        CommentPO[] commentPOs = (CommentPO[]) hotelPO.getComment().toArray();
        List<CommentVO> commentVOList = new ArrayList<CommentVO>();
        if (commentPOs.length == 0)
            return null;
        for (int i = 0; i < commentPOs.length; i++) {
            CommentPO commentPO = commentPOs[i];
            commentVOList.add(new CommentVO(commentPO.getScore(), commentPO.getComment(), commentPO.getCustomerName(),
                    commentPO.getCustomerID(), commentPO.getHotelName(), commentPO.getHotelID(), commentPO.getOrderID(), commentPO.getCommentTime()));
        }
        return commentVOList;
    }


    /**
     * 该方法主要是把酒店的dailyRoomInfo从po转成vo
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
        DailyRoomInfoPO[] dailyRoomInfoPOs = (DailyRoomInfoPO[]) hotelPO.getDailyRoomInfo().toArray();
        List<DailyRoomInfoVO> dailyRoomInfoVOList = new ArrayList<DailyRoomInfoVO>();
        if (dailyRoomInfoPOs.length == 0)
            return null;
        for (int i = 0; i < dailyRoomInfoPOs.length; i++) {
            DailyRoomInfoPO dailyRoomInfoPO = dailyRoomInfoPOs[i];
            RoomPO[] roomPOs = (RoomPO[])dailyRoomInfoPO.getRoom().toArray();
            List<RoomVO> roomVOList = new ArrayList<RoomVO>();
            for(int j=0;j<roomPOs.length;j++){
                RoomPO roomPO = roomPOs[j];
                roomVOList.add(new RoomVO(roomPO.getHotelID(),roomPO.getRoomType(),roomPO.getOccupiedRooms(),
                        roomPO.getReservedRooms(),roomPO.getLeftRooms(),roomPO.getPrice()));
            }
            dailyRoomInfoVOList.add(new DailyRoomInfoVO(dailyRoomInfoPO.getHotelID(), dailyRoomInfoPO.getDate(),roomVOList));
        }
        return dailyRoomInfoVOList;
    }

    Hotel_DataService_Stub hotel_dataService_stub = new Hotel_DataService_Stub();

    /**
     * 得到所有酒店
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> getAll() throws RemoteException {
        HotelPO[] hotelPOList = (HotelPO[]) hotel_dataService_stub.getHotels().toArray();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        if (hotelPOList.length == 0)
            return null;
        for (int i = 0; i < hotelPOList.length; i++) {
            HotelPO hotelPO = hotelPOList[i];
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
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
     * @param ID
     * @return
     * @throws RemoteException
     */
    public HotelVO getByID(String ID) throws RemoteException {
        if(ID.equals(""))
            return null;
        HotelPO hotelPO = hotel_dataService_stub.findHotelByID(ID);
        if(hotelPO==null)
            return null;
        String[] infra = hotelPO.getInfra().split(";");
        String[] picUrl = hotelPO.getPicUrls().split(";");
        return new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                infra, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
                hotelPO.getClerk().getPhone(), hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID()), getComment(hotelPO.getHotelID()));
    }

    /**
     * 按照名字搜索酒店列表
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> getByName(String name) throws RemoteException {
        if(name.equals(""))
            return null;
        HotelPO[] hotelPOs = (HotelPO[]) hotel_dataService_stub.findHotelByName(name).toArray();
        if(hotelPOs.length==0)
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for(int i=0;i<hotelPOs.length;i++){
            HotelPO hotelPO = hotelPOs[i];
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
                    hotelPO.getClerk().getPhone(), hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID()), getComment(hotelPO.getHotelID())));
        }
        return hotelVOList;
    }

    /**
     * 按照商圈搜索酒店
     * @param area
     * @return
     * @throws RemoteException
     */
    public List<HotelVO> getByArea(String area) throws RemoteException {
        if(area.equals(""))
            return null;
        HotelPO[] hotelPOs = (HotelPO[]) hotel_dataService_stub.getHotelByArea(area).toArray();
        if(hotelPOs.length==0)
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for(int i=0;i<hotelPOs.length;i++){
            HotelPO hotelPO = hotelPOs[i];
            String[] infra = hotelPO.getInfra().split(";");
            String[] picUrl = hotelPO.getPicUrls().split(";");
            hotelVOList.add(new HotelVO(hotelPO.getHotelName(), hotelPO.getHotelAddress(), hotelPO.getArea(), hotelPO.getIntro(),
                    infra, hotelPO.getStar(), hotelPO.getScore(), hotelPO.getLicense(), picUrl, hotelPO.getClerk().getName(),
                    hotelPO.getClerk().getPhone(), hotelPO.getHotelID(), getDailyRoomInfo(hotelPO.getHotelID()), getComment(hotelPO.getHotelID())));
        }
        return hotelVOList;
    }

    public RoomVO getRoomByName(String hotelID, String roomName) throws RemoteException {
        return null;
    }
}
