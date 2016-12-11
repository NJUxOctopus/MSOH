package po;

import data.hotel_dataserviceImpl.Hotel_DataServiceImpl;
import dataservice.hotel_dataservice.Hotel_DataService;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/10.
 */
public class RoomTest {
    @Test
    public void testAddRoom() throws RemoteException{
        Hotel_DataService hotelDataService= Hotel_DataServiceImpl.getInstance();

        Timestamp ts=Timestamp.valueOf("2017-01-08 00:00:00");

        RoomPO room1=new RoomPO("10000006","大床房",0,0,350,898,ts);
        RoomPO room2=new RoomPO("10000006","单人房",0,0,90,699,ts);
        RoomPO room3=new RoomPO("10000006","标间",0,0,700,848,ts);

        List<RoomPO> roomList=new ArrayList<RoomPO>();

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);

        DailyRoomInfoPO dailyRoomInfoPO=new DailyRoomInfoPO("10000006",ts,roomList);

        hotelDataService.addDailyRoomInfo(dailyRoomInfoPO);

        // 尝试查找DailyRoomInfo
//        DailyRoomInfoPO dailyRoomInfoPO=hotelDataService.getDailyRoomInfo("10000006",ts);
//
//        HotelPO hotelPO=hotelDataService.findHotelByID(dailyRoomInfoPO.getHotelID());
//
//        System.out.print(hotelPO.getHotelName());
    }
}
