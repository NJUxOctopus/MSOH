package businesslogicservice.hotel_blservice;

import java.util.ArrayList;
import java.util.List;

import util.ResultMessage;
import vo.CommentVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;

/**
 * 
 * @author ST 2016/10/16
 *
 */
public class Hotel_BLService_Driver {

	public void drive(Hotel_BLService hotel_BLService) {

		RoomVO roomVO = new RoomVO();
		List<RoomVO> room = new ArrayList<RoomVO>();
		HotelVO hotelVO = new HotelVO();
		List<HotelVO> hotel = new ArrayList<HotelVO>();
		CommentVO commentVO = new CommentVO();
		OrderVO orderVO = new OrderVO();

		ResultMessage result1 = hotel_BLService.addRoom(roomVO);
		if (result1 == ResultMessage.Blank)
			System.out.println("Information Blank!");
		else if (result1 == ResultMessage.Hotel_AddRoomSuccess)
			System.out.println("Add Room Success!");

		room = hotel_BLService.getRoom("123456");
		if (room == null)
			System.out.println("Find Fail!");
		else
			System.out.println("Success!");

		
		

	}

}
