package util.sort;

import vo.HotelVO;

import java.util.Comparator;

/**
 * Created by Pxr on 16/12/7.
 */
public class sortHotelByScore implements Comparator<HotelVO> {

    public int compare(HotelVO o1, HotelVO o2) {
        if(o1.score<o2.score)
            return -1;
        else if(o1.score==o2.score)
            return 0;
        else
            return 1;
    }
}
