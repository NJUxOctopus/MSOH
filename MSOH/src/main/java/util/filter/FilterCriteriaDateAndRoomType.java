package util.filter;

import businesslogic.hotel_bl.HotelUtil;
import util.CalculateDays;
import vo.DailyRoomInfoVO;
import vo.HotelVO;
import vo.RoomVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class FilterCriteriaDateAndRoomType implements FilterCriteria {
    Timestamp firstDate;
    Timestamp secondDate;
    String roomType;
    int roomNum;

    public FilterCriteriaDateAndRoomType(Timestamp firstDate, Timestamp secondDate, String roomType, int roomNum) {
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.roomType = roomType;
        this.roomNum = roomNum;
    }

    @Override
    public List<HotelVO> meetCriteria(List<HotelVO> list) throws RemoteException {
        if (firstDate == null && secondDate == null)
            return list;
        if (list == null || list.isEmpty())
            return new ArrayList<HotelVO>();
        List<HotelVO> hotelVOList = new ArrayList<HotelVO>();
        HotelUtil hotelUtil = new HotelUtil();
        CalculateDays calculateDays = new CalculateDays(firstDate, secondDate);
        long oneDay = 1000 * 60 * 60 * 24;
        long days = (firstDate.getTime() - secondDate.getTime()) / oneDay + 1;//算共住多少天
        List<Timestamp> timestamps = new ArrayList<Timestamp>();
        for (int i = 0; i < days; i++) {
            timestamps.add(new Timestamp(firstDate.getTime() + i * oneDay));
        }
        for (HotelVO hotelVO : list) {
            for (Timestamp timestamp : timestamps) {
                List<RoomVO> roomVOList = hotelUtil.getDailyRoomInfo(hotelVO.hotelID, timestamp).room;
                for (RoomVO roomVO : roomVOList) {
                    if (roomVO.roomType.equals(roomType) && roomVO.leftRooms >= roomNum)
                        hotelVOList.add(hotelVO);
                }
            }
        }
        return hotelVOList;
    }
}
