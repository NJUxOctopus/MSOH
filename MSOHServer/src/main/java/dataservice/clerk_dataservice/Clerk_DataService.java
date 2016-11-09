package dataservice.clerk_dataservice;

import java.util.List;

import po.ClerkPO;
import util.ResultMessage;
/**
 * 
 * @author ÂXø¬”Ó
 *
 */
public interface Clerk_DataService {
	public ResultMessage add(ClerkPO clerkPO);
	
	public ResultMessage modify (ClerkPO clerkPO);
	
	public List<ClerkPO> findByClerkName (String name);
	
	public List<ClerkPO> findByClerkID (String id);
	
	public ResultMessage delete(ClerkPO clerkPO);
}
