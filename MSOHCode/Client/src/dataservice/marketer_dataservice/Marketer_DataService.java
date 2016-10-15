package dataservice.marketer_dataservice;


import po.MarketerPO;
import util.ResultMessage;

public interface Marketer_DataService {
	public ResultMessage add(MarketerPO po);
	
	public ResultMessage modify (MarketerPO po);
	
	public MarketerPO findByMarketerName (String name);
	
	public MarketerPO findByMarketerID (String id);
	
	public ResultMessage delete(MarketerPO po);
}
