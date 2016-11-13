package dataservice.hotel_dataservice;

import java.util.ArrayList;
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
 * @author ���ߺ�
 *
 */
public class Hotel_DataService_Stub implements Hotel_DataService{

	public ResultMessage add(HotelPO po) {
		// TODO Auto-generated method stub
		if(po.getHotelID().equals("123456"))
			return ResultMessage.Hotel_HotelAlreadyExist;
		else
			return ResultMessage.Manager_AddHotelSuccess;
	}

	public ResultMessage modify(HotelPO po) {
		// TODO Auto-generated method stub
		if(po.getHotelID().equals("123456"))
			return ResultMessage.Manager_ChangeHotelInfoSuccess;
		else
			return ResultMessage.Hotel_HotelNotExist;
	}

	public ResultMessage delete(HotelPO po) {
		// TODO Auto-generated method stub
		if(po.getHotelID().equals("123456"))
			return ResultMessage.Manager_DeleteHotelSuccess;
		else
			return ResultMessage.Hotel_HotelNotExist;
	}

	public List<HotelPO> find(String address, String area, Date expected_date_of_arrival,
			Date expected_date_of_departure, int star, double score) {
		// TODO Auto-generated method stub
		ArrayList<HotelPO> hotelList=new ArrayList<HotelPO>();
		hotelList.add(new HotelPO());
		return hotelList;
	}

	public HotelPO getHotel(String id) {
		// TODO Auto-generated method stub
		if(id.equals("123456"))
			return new HotelPO();
		else
			return null;
	}

	public ResultMessage addRoomType(RoomPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.Hotel_AddRoomSuccess;
	}

	public ResultMessage modifyRoomType(RoomPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.Hotel_ModifyRoomSuccess;
	}

	public ResultMessage deleteRoomType(RoomPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.Hotel_DeleteRoomSuccess;
	}

	public double getRoomPrice(String roomName,RoomPO po) {
		// TODO Auto-generated method stub
		return po.getPrice();
	}

	public String getRoomType(RoomPO po) {
		// TODO Auto-generated method stub
		return po.getRoomType();
	}

	public RoomPO getRoom(String hotelID,String roomName) {
		return null;
	}

	public DailyRoomInfoPO getDailyRoomInfo(Date date) {
		// TODO Auto-generated method stub
		return new DailyRoomInfoPO();
	}

	public ResultMessage setDailyRoomInfo(List<DailyRoomInfoPO> list) {
		// TODO Auto-generated method stub
		return ResultMessage.Hotel_setDailyRoomInfoSuccess;
	}

	public ResultMessage addComment(CommentPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.Hotel_addCommentSuccess;
	}

	public List<CommentPO> getCommentPO(String hotelID) {
		// TODO Auto-generated method stub
		ArrayList<CommentPO> commentList=new ArrayList<CommentPO>();
		commentList.add(new CommentPO());
		return commentList;
	}

	public ResultMessage addToListOfHotelReservedByCustomer(HotelPO hotelPO, CustomerPO customerPO) {
		// TODO Auto-generated method stub
		return ResultMessage.Hotel_addToListOfHotelReservedByCustomerSuccess;
	}

}
