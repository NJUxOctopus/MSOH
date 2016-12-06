package po;

import data.Hotel_DataServiceImpl;
import dataservice.hotel_dataservice.Hotel_DataService;
import org.junit.Test;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/12/6.
 */
public class HotelTest {
    @Test
    public void testSaveHotel() throws IOException,ClassNotFoundException{
        Hotel_DataService hotel_dataService=Hotel_DataServiceImpl.getInstance();

        HotelPO hotelPO=new HotelPO("南京夜泊秦淮君亭酒店","南京 秦淮区 大石坝街2号，近长白街路口","夫子庙地区","南京夜泊秦淮君亭酒店依秦淮河畔而建，与古桃叶渡口隔河相望；距地铁三号线3号出口步行约5分钟，步行就可抵达夫子庙核心景区大成殿、江南贡院、画舫码头、老门东景区及东水关明城墙景区等。酒店以东晋为时代背景，王献之书画为主题，设有文化特色主题客房24间。温馨雅致的公共空间里糅合了自由挥洒的书画文化，艺韵深远的六朝故事以及令人神往的爱情传说。","停车场;WIFI;中餐厅","大床房;双床房;套房",5,0,"2016001","F:/2016001.png","320581190011223111");

//        hotelPO.setHotelID("20161206");
        hotel_dataService.addHotel(hotelPO);
//        HotelPO hotel=hotel_dataService.findHotelByID("20161206");
//
//        hotel_dataService.deleteHotel(hotelPO);

//        System.out.println(hotel_dataService.findHotelByID("20161206").getArea());


    }
}
