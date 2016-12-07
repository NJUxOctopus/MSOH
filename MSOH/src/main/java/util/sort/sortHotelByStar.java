package util.sort;

import vo.HotelVO;

import java.util.Comparator;

/**
 * Created by Pxr on 16/12/7.
 */
public class sortHotelByStar implements Comparator<HotelVO> {

    @Override
    public int compare(HotelVO o1, HotelVO o2) {
        return o1.star-o2.star;
    }
}
