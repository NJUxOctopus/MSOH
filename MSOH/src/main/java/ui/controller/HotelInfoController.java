package ui.controller;

import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.hotel_blservice.HotelUtil_BLService;
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
        Timestamp checkIn ;
        Timestamp checkOut ;


        return hotelUtil_blService.searchHotel(hotelVO.city,hotelVO.area, null, null, hotelVO.star + "", hotelVO.score + "", 5 + "");
    }

    public List<String> getAllCities() throws RemoteException{
        return hotelUtil_blService.getAllCities();
    }

    public List<String> getAreaByCity(String city) throws RemoteException{
        return hotelUtil_blService.getAreaByCity(city);
    }
}
