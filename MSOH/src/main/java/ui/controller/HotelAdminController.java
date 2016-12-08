package ui.controller;

import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.hotel_blservice.HotelUtil_BLService;
import ui.view.controllerservice.HotelAdmin;
import util.ResultMessage;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zqh on 2016/11/10.
 */
public class HotelAdminController implements HotelAdmin {

    private HotelUtil_BLService hotelUtil_blService;

    public HotelAdminController() {
        hotelUtil_blService = new HotelUtil();
    }

    public HotelVO findByID(String ID) {
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
        return hotelUtil_blService.getByID(ID);
    }

    public HotelVO findByName(String name) {
        return null;
    }

    public HotelVO findByAddress(String address) {
        return null;
    }

    public ResultMessage addHotel(HotelVO hotelVO) {
        return null;
    }

    public ResultMessage deleteHotel(HotelVO hotelVO) {
        return null;
    }

    public ResultMessage updateHotelInfo(HotelVO hotelVO) {
        return null;
    }

    /**
     * 通过酒店工作人员的ID获得他所工作的酒店
     *
     * @param clerkID
     * @return
     * @throws RemoteException
     */
    public HotelVO findByClerkID(String clerkID) throws RemoteException {
        return hotelUtil_blService.getHotelByClerkID(clerkID);
    }

    /**
     * 得到酒店某一天的房间信息
     *
     * @param hotelID
     * @param date
     * @return
     */
    public DailyRoomInfoVO getDailyRoomInfo(String hotelID, Date date) throws RemoteException {
        return hotelUtil_blService.getDailyRoomInfo(hotelID, date);
    }

    /**
     * 根据酒店ID时间和房间类型得到房间信息
     *
     * @param hotelID
     * @param roomType
     * @param timestamp
     * @return
     * @throws RemoteException
     */
    public RoomVO getRoomInfo(String hotelID, String roomType, Timestamp timestamp) throws RemoteException {
        return hotelUtil_blService.getRoomInfo(hotelID, roomType, timestamp);
    }
}
