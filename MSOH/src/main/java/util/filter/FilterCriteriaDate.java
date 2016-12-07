package util.filter;

import vo.DailyRoomInfoVO;
import vo.HotelVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class FilterCriteriaDate implements FilterCriteria {
    Timestamp firstDate;
    Timestamp secondDate;

    public FilterCriteriaDate(Timestamp firstDate, Timestamp secondDate) {
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) {
        if (firstDate == null && secondDate == null)
            return list;
        if (list == null || list.isEmpty())
            return null;
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        for (HotelVO hotelVO:list) {
            DailyRoomInfoVO dailyRoomInfoVO = hotelVO.dailyRoomInfo;
            //TODO
        }
        return hotelVOList;
    }
}
