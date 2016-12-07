package util.filter;

import vo.HotelVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class FilterCriteriaArea implements FilterCriteria {
    String area;

    public FilterCriteriaArea(String area) {
        this.area = area;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) {
        if (area.equals(""))
            return list;
        if (list == null || list.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelVO hotelVO:list) {
            if (hotelVO.area.equals(area)) {
                hotelVOList.add(hotelVO);
            }
        }
        return hotelVOList;
    }
}
