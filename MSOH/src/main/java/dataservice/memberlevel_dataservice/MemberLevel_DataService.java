package dataservice.memberlevel_dataservice;

import po.MemberLevelPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/18.
 */
public interface MemberLevel_DataService extends Remote{
    // 更新会员等级制度
    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException;
    // 获取会员等级制度
    public MemberLevelPO getMemberLevel() throws RemoteException;
}
