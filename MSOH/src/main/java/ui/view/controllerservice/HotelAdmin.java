package ui.view.controllerservice;

import util.ResultMessage;
import vo.HotelVO;

/**
 * Created by zqh on 2016/11/10.
 */
public interface HotelAdmin {
    public HotelVO findByID(String ID);

    public HotelVO findByName(String name);

    public HotelVO findByAddress(String address);

    public ResultMessage addHotel(HotelVO hotelVO);

    public ResultMessage deleteHotel(HotelVO hotelVO);

    public ResultMessage updateHotelInfo(HotelVO hotelVO);

}
