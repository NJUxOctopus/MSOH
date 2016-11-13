package dataservice.hotel_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
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
public interface Hotel_DataService extends Remote{
	public ResultMessage add(HotelPO po) throws RemoteException;
	
	public ResultMessage modify (HotelPO po) throws RemoteException;
	
	public ResultMessage delete (HotelPO po) throws RemoteException;
	
	public List<HotelPO> find(String address, String area, Date expected_date_of_arrival, Date expected_date_of_departure, int star, double score) throws RemoteException;
	
	public HotelPO getHotel(String id) throws RemoteException;

	public ResultMessage addRoomType(RoomPO po) throws RemoteException;
	
	public ResultMessage modifyRoomType(RoomPO po) throws RemoteException;
	
	public ResultMessage deleteRoomType(RoomPO po) throws RemoteException;
	
	public double getRoomPrice(RoomPO po) throws RemoteException;
	
	public String getRoomType(RoomPO po) throws RemoteException;
	
	public DailyRoomInfoPO getDailyRoomInfo(Date date) throws RemoteException;
	
	public ResultMessage setDailyRoomInfo(List<DailyRoomInfoPO> list) throws RemoteException;
	
	public ResultMessage addComment(CommentPO po) throws RemoteException;
	
	public List<CommentPO> getCommentPO(String hotelID) throws RemoteException;
	
	public ResultMessage addToListOfHotelReservedByCustomer(HotelPO hotelPO,CustomerPO customerPO) throws RemoteException;
	
	
}
