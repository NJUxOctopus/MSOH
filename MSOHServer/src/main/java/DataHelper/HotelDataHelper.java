package DataHelper;

import po.HotelPO;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface HotelDataHelper {
    public boolean addHotel(HotelPO hotelPO);

    public boolean modifyHotel(HotelPO hotelPO);

    public boolean deleteHotel(HotelPO hotelPO);

    public List<HotelPO> getHotels();

    public HotelPO getHotelByID(String ID);

    public List<HotelPO> getHotelByName(String hotelName);

    public List<HotelPO> getHotelByArea(String areaName);
}
