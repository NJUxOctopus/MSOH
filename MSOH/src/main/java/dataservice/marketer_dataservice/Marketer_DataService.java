package dataservice.marketer_dataservice;


import java.util.List;

import po.MarketerPO;
import util.ResultMessage;
import util.WorkerPosition;
/**
 * 
 * @author ÂXø¬”Ó
 *
 */
public interface Marketer_DataService {
	public ResultMessage add(MarketerPO marketerPO);
	
	public ResultMessage modify (MarketerPO marketerPO);
	
	public List<MarketerPO> findByMarketerName (String name);
	
	public List<MarketerPO> findByMarketerID (String id);
	
	public List<MarketerPO> findByPosition(WorkerPosition position);
	
	public ResultMessage delete(MarketerPO marketerPO);
}
