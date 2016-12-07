package ui.controller;

import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.hotel_blservice.HotelUtil_BLService;
import ui.view.controllerservice.HotelAdmin;
import util.ResultMessage;
import vo.DailyRoomInfoVO;
import vo.HotelVO;

import java.rmi.RemoteException;
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
        hotelUtil_blService = new HotelUtil();
        HotelVO hotelVO = hotelUtil_blService.getHotelByClerkID(clerkID);
        return hotelVO;
    }

    /**
     * 得到酒店某一天的房间信息
     *
     * @param hotelID
     * @param date
     * @return
     */
    public DailyRoomInfoVO getDailyRoomInfo(String hotelID, Date date) throws RemoteException {
        hotelUtil_blService = new HotelUtil();
        DailyRoomInfoVO dailyRoomInfoVO = hotelUtil_blService.getDailyRoomInfo(hotelID, date);
        return dailyRoomInfoVO;
    }
}
