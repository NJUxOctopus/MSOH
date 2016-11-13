package dataservice.member_dataservice;

import po.MemberLevelPO;
import po.MemberPO;
import util.MemberType;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author zqh
 *
 */
public interface Member_DataService extends Remote{
	public ResultMessage add(MemberPO po) throws RemoteException;
	
	public ResultMessage upgrade (int grade) throws RemoteException;
	
	public ResultMessage degrade (int grade) throws RemoteException;
	
	public int getGrade (MemberPO po) throws RemoteException;

	public ResultMessage delete(MemberPO po) throws RemoteException;
	
	public MemberType getType (MemberPO po) throws RemoteException;
	
	public MemberPO getMember (String id) throws RemoteException;
	
	public ResultMessage addMemberLevel(MemberLevelPO po) throws RemoteException;
	
	public ResultMessage modifyMemberLevel(MemberLevelPO po) throws RemoteException;
	
	public int getNumberOfMemberLevel(MemberLevelPO po) throws RemoteException;
	
	
}
