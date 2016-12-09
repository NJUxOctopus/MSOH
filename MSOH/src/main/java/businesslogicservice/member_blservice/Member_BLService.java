package businesslogicservice.member_blservice;

import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

import java.rmi.RemoteException;

public interface Member_BLService {

    public ResultMessage signUp(MemberVO memberVO) throws RemoteException;

    public ResultMessage changeGrade(String customerID) throws RemoteException;

}
