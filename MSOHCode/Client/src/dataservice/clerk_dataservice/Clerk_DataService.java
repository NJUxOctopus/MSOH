package dataservice.clerk_dataservice;

import java.util.List;

import po.ClerkPO;
import util.ResultMessage;

public interface Clerk_DataService {
	public ResultMessage add(ClerkPO po);
	
	public ResultMessage modify (ClerkPO po);
	
	public List<ClerkPO> findByClerkName (String name);
	
	public List<ClerkPO> findByClerkID (String id);
	
	public ResultMessage delete(ClerkPO po);
}
