package businesslogicservice.memberUtil_blservice;

import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public interface MemberUtil_BLService {
    public MemberVO getSingle (String customerID)throws RemoteException;
}
