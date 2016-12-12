package businesslogicservice.member_blservice;

import vo.MemberVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface MemberUtil_BLService {
    public MemberVO getSingle(String customerID) throws RemoteException;

    public List<String> getCompany() throws RemoteException;
}
