package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public interface MemberLevel_BLService {
    public ResultMessage modifyMemberLevel(MemberLevelVO memberLevelVO)throws RemoteException;

    public MemberLevelVO getMemberLevel()throws RemoteException;
}
