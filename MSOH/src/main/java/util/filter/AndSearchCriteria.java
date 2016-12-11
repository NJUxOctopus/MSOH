package util.filter;

import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Pxr on 16/12/9.
 */
public class AndSearchCriteria implements FilterCriteria {

    public FilterCriteria filterCriteria;
    public FilterCriteria otherCriteria;
    public FilterCriteria thirdCriteria;

    public AndSearchCriteria(FilterCriteria filterCriteria, FilterCriteria otherCriteria, FilterCriteria thirdCriteria) {
        this.filterCriteria = filterCriteria;
        this.otherCriteria = otherCriteria;
        this.thirdCriteria = thirdCriteria;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) throws RemoteException {
        List<HotelVO> hotelVOList = filterCriteria.meetCriteria(list);
        return thirdCriteria.meetCriteria(otherCriteria.meetCriteria(hotelVOList));
    }
}
