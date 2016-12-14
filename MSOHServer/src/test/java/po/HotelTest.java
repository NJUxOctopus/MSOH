package po;

import data.hotel_dataserviceImpl.Hotel_DataServiceImpl;
import dataservice.hotel_dataservice.Hotel_DataService;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by zqh on 2016/12/6.
 */
public class HotelTest {
    @Test
    public void testSaveHotel() throws IOException, ClassNotFoundException {
        Hotel_DataService hotel_dataService = Hotel_DataServiceImpl.getInstance();

        List<HotelPO> list = hotel_dataService.getHotelByArea("新街口地区");

//        for (HotelPO hotelPO : list) {
//            System.out.println(hotelPO.getHotelName());
//        }

//        HotelPO hotelPO = hotel_dataService.findHotelByID("10000006");
//        if (hotelPO == null) {
//            System.out.println("No Hotel");
//        } else {
//            System.out.println(hotelPO.getHotelName() +" "+hotelPO.getClerkID()+ " " + hotelPO.getHotelAddress() + " " + hotelPO.getLicense());
//        }

//
        HotelPO hotel=new HotelPO("hdhdnd","dddd","dd","dd","ddd","dd",2,5,"2010006","f","F");
        System.out.print(hotel_dataService.addHotel(hotel));
    }
}
