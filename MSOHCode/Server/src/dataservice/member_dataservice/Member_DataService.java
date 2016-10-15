package dataservice.member_dataservice;

import po.MemberLevelPO;
import po.MemberPO;
import util.MemberType;
import util.ResultMessage;
/**
 * 
 * @author åX¿ÂÓî
 *
 */
public interface Member_DataService {
	public ResultMessage add(MemberPO po);
	
	public ResultMessage upgrade (int grade);
	
	public ResultMessage degrade (int grade);
	
	public int getGrade (MemberPO po);

	public ResultMessage delete(MemberPO po);
	
	public MemberType getType (MemberPO po);
	
	public MemberPO getMember (String id);
	
	public ResultMessage addMemberLevel(MemberLevelPO po);
	
	public ResultMessage modifyMemberLevel(MemberLevelPO po);
	
	public int getNumberOfMemberLevel(MemberLevelPO po);
	
	
}
