package util.filter;

import vo.HotelVO;

import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public interface FilterCriteria {
    public List<HotelVO> meetCriteria(List<HotelVO> list);
}
