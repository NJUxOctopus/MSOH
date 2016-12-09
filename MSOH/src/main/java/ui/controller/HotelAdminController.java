package ui.controller;

import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.hotel_blservice.HotelUtil_BLService;
import businesslogicservice.hotel_blservice.Hotel_BLService;
import ui.view.controllerservice.HotelAdmin;
import util.ResultMessage;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by zqh on 2016/11/10.
 */
public class HotelAdminController implements HotelAdmin {

    private HotelUtil_BLService hotelUtil_blService;

    private Hotel_BLService hotel_blService;

    public HotelAdminController() {
        hotelUtil_blService = new HotelUtil();
        hotel_blService = new Hotel();
    }

    /**
     * 按照ID搜索酒店
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public HotelVO findByID(String ID) throws RemoteException {
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

    /**
     * 修改酒店信息
     *
     * @param hotelVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage updateHotelInfo(HotelVO hotelVO) throws RemoteException {
        return hotel_blService.modifyHotel(hotelVO);
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
        return hotelUtil_blService.getRoomByName(hotelID, roomType, timestamp);
    }

    /**
     * 得到所有的城市信息
     *
     * @return
     * @throws RemoteException
     */
    public List<String> getAllCities() throws RemoteException {
        return hotelUtil_blService.getAllCities();
    }

    /**
     * 得到该城市的所有商圈
     *
     * @param city
     * @return
     * @throws RemoteException
     */
    public List<String> getAreaByCity(String city) throws RemoteException {
        return hotelUtil_blService.getAreaByCity(city);
    }
}
