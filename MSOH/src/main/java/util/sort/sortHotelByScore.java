package util.sort;

import vo.HotelVO;

import java.util.Comparator;

/**
 * Created by Pxr on 16/12/7.
 */
public class sortHotelByScore implements Comparator<HotelVO> {

    public int compare(HotelVO o1, HotelVO o2) {
        return (int) (o1.score - o2.score);
    }
}
