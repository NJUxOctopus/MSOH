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
 * @author ßLÇßº­
 *
 */
public class Hotel_DataService_Dirver {
	List<DailyRoomInfoPO> driList;
	CommentPO commentPO;
	CustomerPO customerPO;
	
	public void dirve(Hotel_DataService hotel_DataService){
		HotelPO hotelPO=new HotelPO();
		RoomPO roomPO=new RoomPO("123456", "±ê¼ä", 5, 4, 4, 280);

		
		
		ResultMessage result1=hotel_DataService.add(hotelPO);
		if(result1==ResultMessage.Hotel_HotelAlreadyExist)
			System.out.println("Add Hotel Failure!");
		if(result1==ResultMessage.Manager_AddHotelSuccess)
			System.out.println("Add Hotel Success!");
		
		
		ResultMessage result2=hotel_DataService.modify(hotelPO);
		if(result2==ResultMessage.Manager_ChangeHotelInfoSuccess)
			System.out.println("Modify HotelInfo Success!");
		if(result2==ResultMessage.Hotel_HotelNotExist)
			System.out.println("Modify HotelInfo Failure! : Hotel Not Exist!");
		
		
		ResultMessage result3=hotel_DataService.delete(hotelPO);
		if(result3==ResultMessage.Manager_DeleteHotelSuccess)
			System.out.println("Delete Hotel Success!");
		if(result3==ResultMessage.Hotel_HotelNotExist)
			System.out.println("Delete Hotel Failure! : Hotel Not Exist!");
		
		
		List<HotelPO> hotelList=hotel_DataService.find("Nanjing", "MaQun", new Date(), new Date(), 5, 98);
		if(!hotelList.isEmpty()){
			System.out.println("Find Hotel Successfully!");
		}else{
			System.out.println("Find Hotel Failure!");
		}
		
		
		HotelPO hotelPO1=hotel_DataService.getHotel("123456");
		if(!(hotelPO1==null)){
			System.out.println("Get Hotel Success!");
		}else{
			System.out.println("Get Hotel Failure!");
		}
		
		
		ResultMessage result4=hotel_DataService.addRoomType(roomPO);
		if(result4==ResultMessage.Hotel_AddRoomSuccess)
			System.out.println("Add Room Success!");
		
		
		ResultMessage result5=hotel_DataService.modifyRoomType(roomPO);
		if(result5==ResultMessage.Hotel_ModifyRoomSuccess){
			System.out.println("Modify Room Successfully! ");
		}
		
		
		ResultMessage result6=hotel_DataService.deleteRoomType(roomPO);
		if(result6==ResultMessage.Hotel_DeleteRoomSuccess){
			System.out.println("Delete Room Successfully! ");
		}
		
		
		double roomPrice=hotel_DataService.getRoomPrice(roomPO);
		if(roomPrice==roomPO.getPrice()){
			System.out.println("Get room price successfully! ");
		}else{
			System.out.println("Get room price failure! ");
		}
		
		
		String roomType=hotel_DataService.getRoomType(roomPO);
		if(roomType==roomPO.getRoomType()){
			System.out.println("Get room type successfully! ");
		}else{
			System.out.println("Get room type failure! ");
		}
		
		
		DailyRoomInfoPO driPO=hotel_DataService.getDailyRoomInfo(new Date());
		if(driPO==null){
			System.out.println("Get Daily Room Info Failure!");
		}else{
			System.out.println("Get Daily Room Info Success!");
		}
		
		
		ResultMessage result7=hotel_DataService.setDailyRoomInfo(driList);
		if(result7==ResultMessage.Hotel_setDailyRoomInfoSuccess){
			System.out.println("Set Daily Room Info Success!");
		}
		
		
		ResultMessage result8=hotel_DataService.addComment(commentPO);
		if(result8==ResultMessage.Hotel_addCommentSuccess){
			System.out.println("Add Comment Successfully!");
		}
		
		List<CommentPO> commentList=hotel_DataService.getCommentPO("123456");
		if(!commentList.isEmpty()){
			System.out.println("Get Comment Successfully!");
		}else{
			System.out.println("Get Comment Failure!");
		}
		
		ResultMessage result9=hotel_DataService.addToListOfHotelReservedByCustomer(hotelPO, customerPO);
		if(result9==ResultMessage.Hotel_addToListOfHotelReservedByCustomerSuccess){
			System.out.println("Add to List Of Hotel Reserved by Customers Success! ");
		}
	}
	
}
