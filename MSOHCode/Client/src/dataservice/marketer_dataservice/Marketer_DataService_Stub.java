package dataservice.marketer_dataservice;

import java.util.ArrayList;
import java.util.List;

import po.MarketerPO;
import util.ResultMessage;
import util.WorkerPosition;

/**
 * 
 * @author Ç®¿ÂÓî
 *
 */
public class Marketer_DataService_Stub implements Marketer_DataService{
	@Override
	public ResultMessage add(MarketerPO marketerPO){
		if(marketerPO.getID().equals("320581199810101111")){
			return ResultMessage.Manager_AddMarketerAlreadyExist;
		}else{
			return ResultMessage.Manager_AddMarketerSuccess;
		}
	}

	@Override
	public ResultMessage modify(MarketerPO marketerPO) {
		if(marketerPO.getID().equals("320581199810101111")){
				return ResultMessage.Manager_ChangeMarketerInfoSuccess;
			}else{
				return ResultMessage.Marketer_MarketerNotExist;
			}
	}

	@Override
	public List<MarketerPO> findByMarketerName(String name) {
		ArrayList<MarketerPO> marketer=new ArrayList<MarketerPO>();
		marketer.add(new MarketerPO());
		return marketer;
	}

	@Override
	public List<MarketerPO> findByMarketerID(String id) {
		ArrayList<MarketerPO> marketer=new ArrayList<MarketerPO>();
		marketer.add(new MarketerPO());
		return marketer;
	}

	@Override
	public List<MarketerPO> findByPosition(WorkerPosition position) {
		ArrayList<MarketerPO> marketer=new ArrayList<MarketerPO>();
		marketer.add(new MarketerPO());
		return marketer;
	}

	@Override
	public ResultMessage delete(MarketerPO marketerPO) {
		if(marketerPO.getID().equals("320581199810101111")){
			return ResultMessage.Manager_DeleteMarketerSuccess;
		}else{
			return ResultMessage.Marketer_MarketerNotExist;
		}
	}
}