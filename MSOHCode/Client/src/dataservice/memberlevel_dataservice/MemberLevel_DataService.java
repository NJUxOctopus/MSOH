package dataservice.memberlevel_dataservice;

import po.MemberLevelPO;
import util.ResultMessage;

public interface MemberLevel_DataService {
	public ResultMessage add(MemberLevelPO po);
	
	public ResultMessage modify(MemberLevelPO po);
	
	public int getGradeNum (MemberLevelPO po);
	
}
