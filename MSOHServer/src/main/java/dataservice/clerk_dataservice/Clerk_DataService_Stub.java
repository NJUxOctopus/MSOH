package dataservice.clerk_dataservice;

import java.util.ArrayList;
import java.util.List;

import po.ClerkPO;
import util.ResultMessage;

/**
 * 
 * @author ÷‹«ﬂ∫≠
 *
 */
public class Clerk_DataService_Stub implements Clerk_DataService{

	@Override
	public ResultMessage add(ClerkPO clerkPO) {
		// TODO Auto-generated method stub
		if(clerkPO.getID().equals("320581190001012016")){
			return ResultMessage.Manager_AddClerkAlreadyExist;
		}else{
			return ResultMessage.Manager_AddClerkSuccess;
		}
	}

	@Override
	public ResultMessage modify(ClerkPO clerkPO) {
		// TODO Auto-generated method stub
		if(clerkPO.getID().equals("320581190001012016")){
			return ResultMessage.Manager_ChangeClerkInfoSuccess;
		}else{
			return ResultMessage.Clerk_ClerkNotExist;
		}
	}

	@Override
	public List<ClerkPO> findByClerkName(String name) {
		// TODO Auto-generated method stub
		ArrayList<ClerkPO> clerk=new ArrayList<ClerkPO>();
		clerk.add(new ClerkPO());
		return clerk;
	}

	@Override
	public List<ClerkPO> findByClerkID(String id) {
		// TODO Auto-generated method stub
		ArrayList<ClerkPO> clerk=new ArrayList<ClerkPO>();
		clerk.add(new ClerkPO());
		return clerk;
	}

	@Override
	public ResultMessage delete(ClerkPO clerkPO) {
		// TODO Auto-generated method stub
		if(clerkPO.getID().equals("320581190001012016")){
			return ResultMessage.Manager_DeleteClerkSuccess;
		}else{
			return ResultMessage.Clerk_ClerkNotExist;
		}
	}

}
