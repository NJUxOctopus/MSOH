package dataservice.hotel_dataservice;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.Timestamp;
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
 * @author zqh
 *
 */
public class Hotel_DataService_Stub implements Hotel_DataService{

	public boolean addHotel(HotelPO po) throws RemoteException {
		return false;
	}

	public boolean modifyHotel(HotelPO po) throws RemoteException {
		return false;
	}

	public boolean deleteHotel(HotelPO po) throws RemoteException {
		return false;
	}

	public List<HotelPO> getHotels() throws RemoteException {
		return null;
	}

	public HotelPO findHotelByID(String hotelID) throws RemoteException {
		return null;
	}

	public List<HotelPO> findHotelByName(String hotelName) throws RemoteException {
		return null;
	}

//	public boolean addRoom(RoomPO po) throws RemoteException {
//		return false;
//	}
//
//	public boolean modifyRoom(RoomPO po) throws RemoteException {
//		return false;
//	}
//
//	public boolean deleteRoom(RoomPO po) throws RemoteException {
//		return false;
//	}
//
//	public RoomPO getRoom(String hotelID, String roomName) throws RemoteException {
//		return null;
//	}
//
//	public List<RoomPO> getHotelRooms(String hotelID) throws RemoteException {
//		return null;
//	}

	public DailyRoomInfoPO getDailyRoomInfo(String hotelID, Date date) throws RemoteException {
		return null;
	}

	@Override
	public boolean addDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
		return false;
	}

	@Override
	public boolean deleteDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
		return false;
	}

	@Override
	public boolean updateDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
		return false;
	}

	@Override
	public List<HotelPO> getHotelByArea(String areaName) throws IOException, ClassNotFoundException {
		return null;
	}

	public boolean addComment(CommentPO po) throws RemoteException {
		return false;
	}

	public List<CommentPO> getCommentByHotel(String hotelID) throws RemoteException {
		return null;
	}

	public CommentPO getCommentByOrder(String orderID) throws RemoteException {
		return null;
	}

}
