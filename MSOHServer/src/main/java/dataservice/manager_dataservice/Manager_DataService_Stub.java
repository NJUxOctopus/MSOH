package dataservice.manager_dataservice;

import po.ManagerPO;
import util.ResultMessage;
/**
 * 
 * @author 钱柯宇
 *
 */
public class Manager_DataService_Stub implements Manager_DataService{
	public ResultMessage modify (ManagerPO managerPO){
		// TODO Auto-generated method stub
		if(managerPO.getID().equals("320581190001012019")){
			return ResultMessage.Manager_ChangeManagerInfoSuccess;
		}else{
			return ResultMessage.Manager_ManagerNotExist;
		}
	}
}
