package util.filter;

import businesslogic.bl_Factory.Abstract_BLFactory;
import businesslogic.bl_Factory.Default_BLFactory;
import businesslogic.hotel_bl.HotelUtil;
import util.CalculateDays;
import vo.HotelVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/9.
 */
public class FilterCriteriaDate implements FilterCriteria {
    private Timestamp firstDate;
    private Timestamp secondDate;
    private HotelUtil hotelUtil;

    public FilterCriteriaDate(Timestamp timestamp1, Timestamp timestamp2,HotelUtil hotelUtil) {
        this.firstDate = timestamp1;
        this.secondDate = timestamp2;
        this.hotelUtil = hotelUtil;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) throws RemoteException {
        if (firstDate == null && secondDate == null)
            return list;
        if (list == null || list.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        long oneDay = 1000 * 60 * 60 * 24;
        long days = (secondDate.getTime() - firstDate.getTime()) / oneDay;//算共住多少天
        //System.out.print(days);
        List<Timestamp> timestamps = new ArrayList<Timestamp>();
        for (int i = 0; i < days; i++) {
            timestamps.add(new Timestamp(firstDate.getTime() + i * oneDay));
        }
        boolean hasRoom = false;
        for (HotelVO hotelVO : list) {
            for (Timestamp timestamp : timestamps) {
                List<RoomVO> roomVOList = hotelUtil.getDailyRoomInfo(hotelVO.hotelID, timestamp).room;
                for (RoomVO roomVO : roomVOList) {
                    if (roomVO.leftRooms >= 0)
                        hasRoom = true;
                }
            }
            if (hasRoom) {
                hasRoom = false;
                hotelVOList.add(hotelVO);
            }
        }
        return hotelVOList;
    }
}
