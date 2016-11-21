package dataservice.memberlevel_dataservice;

import po.MemberLevelPO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/19.
 */
public class MemberLevel_DataService_Stub implements MemberLevel_DataService{
    @Override
    public boolean addMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    @Override
    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    @Override
    public MemberLevelPO getMemberLevel() throws RemoteException {
        return null;
    }
}
