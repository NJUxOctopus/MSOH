package po;

import data.Hotel_DataServiceImpl;
import dataservice.hotel_dataservice.Hotel_DataService;
import org.junit.Test;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/12/6.
 */
public class HotelTest {
    @Test
    public void testSaveHotel() throws IOException, ClassNotFoundException {
        Hotel_DataService hotel_dataService = Hotel_DataServiceImpl.getInstance();

        List<HotelPO> list = hotel_dataService.getHotelByArea("新街口地区");

        for (HotelPO hotelPO : list) {
            System.out.println(hotelPO.getHotelName());
        }

        HotelPO hotelPO = hotel_dataService.findHotelByID("10000012");
        if (hotelPO == null) {
            System.out.println("No Hotel");
        } else {
            System.out.println(hotelPO.getHotelName() + " " + hotelPO.getHotelAddress() + " " + hotelPO.getIntro());
        }


    }
}
