package businesslogicservice.hotelUtil_blservice;

import vo.HotelVO;

import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface HotelUtil_BLService {
    public List<HotelVO> getAll();

    public List<HotelVO> sortByPrice(List<HotelVO> list);

    public List<HotelVO> sortByStar(List<HotelVO> list);

    public List<HotelVO> sortByScore(List<HotelVO> list);

    public List<HotelVO> searchHotel (HotelVO hotelVO);

    public HotelVO getByID (String ID);

    public HotelVO getByName (String name);

    public HotelVO getByAddress (String address);
}
