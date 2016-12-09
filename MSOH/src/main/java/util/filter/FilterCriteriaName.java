package util.filter;

import vo.HotelVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class FilterCriteriaName implements FilterCriteria {
    String name;

    public FilterCriteriaName(String name) {
        this.name = name;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) {
        if (name.equals(""))
            return list;
        if (list == null || list.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelVO hotelVO : list) {
            if (hotelVO.hotelName.contains(name)) {
                hotelVOList.add(hotelVO);
            }
        }
        return hotelVOList;
    }
}
