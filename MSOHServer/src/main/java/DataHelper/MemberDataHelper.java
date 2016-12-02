package DataHelper;

import po.MemberPO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface MemberDataHelper {
    public boolean addMember(MemberPO po);

    public boolean deleteMember(MemberPO po);

    public boolean updateMember(MemberPO po);

    public MemberPO findMemberByID(String ID);

    public List<MemberPO> findAllMembers();
}
