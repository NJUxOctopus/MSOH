package dataservice.creditrecord_datasevice;

import po.CreditRecordPO;
import util.ResultMessage;

public interface CreditRecord_DataService {
	public ResultMessage add (CreditRecordPO po);
	
	public int getCurrentCredit (String user_id);
}
