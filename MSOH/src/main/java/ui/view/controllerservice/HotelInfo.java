package ui.view.controllerservice;

import vo.HotelVO;

import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface HotelInfo {
    public List<HotelVO> sortByPrice(List<HotelVO> list);

    public List<HotelVO> sortByStar(List<HotelVO> list);

    public List<HotelVO> sortByScore(List<HotelVO> list);

    public List<HotelVO> searchHotel (HotelVO hotelVO);
}
