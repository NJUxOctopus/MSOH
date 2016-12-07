package ui.controller;

import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.hotelUtil_blservice.HotelUtil_BLService;
import ui.view.controllerservice.HotelInfo;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class HotelInfoController implements HotelInfo {
    private HotelUtil_BLService hotelUtil_blService;

    public HotelInfoController(){
        hotelUtil_blService = new HotelUtil();
    }
    public List<HotelVO> sortByPrice(List<HotelVO> list) {
        return null;
    }

    public List<HotelVO> sortByStar(List<HotelVO> list) {
        return null;
    }

    public List<HotelVO> sortByScore(List<HotelVO> list) {
        return null;
    }

    public List<HotelVO> searchHotel(HotelVO hotelVO) throws RemoteException {
        return hotelUtil_blService.searchHotel(hotelVO.city,hotelVO.toString(), Timestamp.valueOf(hotelVO.checkInTime + " 00:00:00"),Timestamp.valueOf(hotelVO.checkOutTime + " 00:00:00"), hotelVO.star + "", hotelVO.score + "", 5 + "");
    }
}
