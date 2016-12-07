package util.filter;

import vo.HotelVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class FilterCriteriaCity implements FilterCriteria {
    String city;

    public FilterCriteriaCity(String city) {
        this.city = city;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) {
        if (city.equals(""))
            return list;
        if (list == null || list.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelVO hotelVO : list) {
            if (hotelVO.hotelAddress.contains(city)) {
                hotelVOList.add(hotelVO);
            }
        }
        return hotelVOList;
    }
}
