package util.filter;

import vo.HotelVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class FilterCriteriaStar implements FilterCriteria {
    private String star;

    public FilterCriteriaStar(String star) {
        this.star = star;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) {
        if (star.equals("-1"))
            return list;
        if (list == null || list.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelVO hotelVO : list) {
            if (hotelVO.star >= Integer.parseInt(star)) {
                hotelVOList.add(hotelVO);
            }
        }
        return hotelVOList;
    }
}
