package dataservice.member_dataservice;

import po.MemberLevelPO;
import po.MemberPO;
import util.MemberType;
import util.ResultMessage;
/**
 * 
 * @author Ç®¿ÂÓî
 *
 */
public class Member_DataService_Stub implements Member_DataService{

	@Override
	public ResultMessage add(MemberPO po) {
		if(po.getID().equals("320581199810101111")){
			return ResultMessage.Member_AddMemberAlreadyExist;
		}else{
			return ResultMessage.Member_AddMemberSuccess;
		}
	}

	@Override
	public ResultMessage upgrade(int grade) {
		// TODO Auto-generated method stub
		return ResultMessage.Member_Upgrade;
	}

	@Override
	public ResultMessage degrade(int grade) {
		// TODO Auto-generated method stub
		return ResultMessage.Member_Degrade;
	}

	@Override
	public int getGrade(MemberPO po) {
		// TODO Auto-generated method stub
		return po.getLevel();
	}

	@Override
	public ResultMessage delete(MemberPO po) {
		if(po.getID().equals("320581199810101111")){
			return ResultMessage.Manager_DeleteMemberSuccess;
		}else{
			return ResultMessage.Member_MemberNotExist;
		}
	}

	@Override
	public MemberType getType(MemberPO po) {
		// TODO Auto-generated method stub
		return po.getMemberType();
	}

	@Override
	public MemberPO getMember(String id) {
		if(id.equals("320581199810101111"))
			return new MemberPO();
		else
			return null;
	}

	@Override
	public ResultMessage addMemberLevel(MemberLevelPO po) {
		return ResultMessage.MemberLevel_AddMemberLevelSuccess;
	}

	@Override
	public ResultMessage modifyMemberLevel(MemberLevelPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.MemberLevel_ModifyMemberLevelSuccess;
	}

	@Override
	public int getNumberOfMemberLevel(MemberLevelPO po) {
		// TODO Auto-generated method stub
		return po.getNum();
	}

}
