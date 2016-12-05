package ui.controller;

import businesslogic.hotel_bl.HotelUtil;
import businesslogicservice.hotelUtil_blservice.HotelUtil_BLService;
import ui.view.controllerservice.HotelInfo;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        Timestamp checkInTimestamp ;
        Timestamp checkOutTimestamp;
        Date checkInDate;
        Date checkOutDate;
        try {
            checkInDate = sf.parse(hotelVO.checkInTime);
            checkOutDate = sf.parse(hotelVO.checkOutTime);
            checkInTimestamp = new Timestamp(checkInDate.getTime());
            checkOutTimestamp = new Timestamp(checkOutDate.getTime());
            System.out.println(hotelVO.city);
            System.out.println(hotelVO.area);
            System.out.println(checkInTimestamp);
            System.out.println(checkOutTimestamp);
            System.out.println(hotelVO.star + "");
            System.out.println(hotelVO.score + "");


            return hotelUtil_blService.searchHotel(hotelVO.city, hotelVO.area, checkInTimestamp, checkOutTimestamp, hotelVO.star + "", hotelVO.score + "", "5");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
