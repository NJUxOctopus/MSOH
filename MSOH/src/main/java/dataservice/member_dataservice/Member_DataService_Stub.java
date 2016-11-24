package dataservice.member_dataservice;

import po.MemberLevelPO;
import po.MemberPO;
import util.MemberType;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author zqh
 */
public class Member_DataService_Stub implements Member_DataService {

    public boolean addMember(MemberPO po) throws RemoteException {
        return false;
    }

    public boolean deleteMember(MemberPO po) throws RemoteException {
        return false;
    }

    public boolean updateMember(MemberPO po) throws RemoteException {
        return false;
    }

    public MemberPO findMemberByID(String ID) throws RemoteException {
        if (ID.equals("320200000000000000"))
            return new MemberPO(ID, MemberType.NONMEMBER, 0, null, null);
        else if (ID.equals("12345678"))
            return new MemberPO(ID, MemberType.ENTREPRISE, 1, null, "NJU");
        else
            return null;
    }

    public List<MemberPO> findAllMemebers() throws RemoteException {
        return null;
    }
}
