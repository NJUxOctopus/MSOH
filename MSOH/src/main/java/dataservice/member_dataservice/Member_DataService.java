package dataservice.member_dataservice;

import po.MemberPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author zqh
 *
 */
public interface Member_DataService extends Remote{
	// 新增会员信息
	public boolean addMember(MemberPO po) throws RemoteException;
	// 删除客户会员身份
	public boolean deleteMember(MemberPO po) throws RemoteException;
	// 更新会员信息
	public boolean updateMember(MemberPO po) throws RemoteException;
	// 根据ID获取会员信息
	public MemberPO findMemberByID(String ID) throws RemoteException;
	// 得到所有会员
	public List<MemberPO> findAllMemebers() throws RemoteException;

}
