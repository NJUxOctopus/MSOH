package dataservice.memberlevel_dataservice;

import po.MemberLevelPO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Pxr on 16/11/19.
 */
public class MemberLevel_DataService_Stub implements MemberLevel_DataService {
    public boolean addMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    public boolean deleteMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    public MemberLevelPO getMemberLevel() throws RemoteException {
        int a[] = {10,20,30};
        return new MemberLevelPO("pxr",new Timestamp(System.currentTimeMillis()),3,a);
    }
}
