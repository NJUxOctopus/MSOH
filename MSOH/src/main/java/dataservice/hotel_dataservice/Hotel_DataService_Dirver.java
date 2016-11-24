package dataservice.hotel_dataservice;

import java.util.Date;
import java.util.List;

import po.CommentPO;
import po.CustomerPO;
import po.DailyRoomInfoPO;
import po.HotelPO;
import po.RoomPO;
import util.ResultMessage;
/**
 * 
 * @author zqh
 *
 */
public class Hotel_DataService_Dirver {
	List<DailyRoomInfoPO> driList;
	CommentPO commentPO;
	CustomerPO customerPO;
	
	public void dirve(Hotel_DataService hotel_DataService){
		HotelPO hotelPO=new HotelPO();
		RoomPO roomPO=new RoomPO("123456", "���", 5, 4, 4, 280);

		
		

	}
	
}
