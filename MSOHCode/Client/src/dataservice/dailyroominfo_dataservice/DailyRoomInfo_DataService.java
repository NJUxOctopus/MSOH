package dataservice.dailyroominfo_dataservice;

import java.util.Date;
import java.util.List;

import po.DailyRoomInfoPO;
import util.ResultMessage;

public interface DailyRoomInfo_DataService {
	public DailyRoomInfoPO getDailyRoomInfo (Date date);
	
	public ResultMessage setDailyRoomInfo(List<DailyRoomInfoPO> l);
	
}
