package util.filter;

import vo.HotelVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class FilterCriteriaScore implements FilterCriteria {
    String low;
    String high;

    public FilterCriteriaScore(String low, String high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) {
        if (low.equals("-1.0"))
            return list;
        if (list == null || list.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelVO hotelVO : list) {
            if (hotelVO.score <= Double.parseDouble(high) && hotelVO.score >= Double.parseDouble(low))
                    hotelVOList.add(hotelVO);
        }
        return hotelVOList;
    }
}
