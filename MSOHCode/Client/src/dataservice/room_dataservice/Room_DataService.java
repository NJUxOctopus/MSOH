package dataservice.room_dataservice;

import po.RoomPO;
import util.ResultMessage;

public interface Room_DataService {
	public ResultMessage addType (RoomPO po);
	
	public ResultMessage modify (RoomPO po);
	
	public ResultMessage delete (RoomPO po);
	
	public double getPrice (RoomPO po);
	
	public String getType (String id);
}
