package businesslogic.member_bl;

import businesslogicservice.memberUtil_blservice.MemberUtil_BLService;
import dataservice.member_dataservice.Member_DataService_Stub;
import po.MemberPO;
import rmi.RemoteHelper;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class MemberUtil implements MemberUtil_BLService {
    Member_DataService_Stub member_dataService_stub = new Member_DataService_Stub();
    public MemberVO getSingle(String customerID) throws RemoteException{
        if(customerID.equals(""))
            return null;
        MemberPO memberPO = member_dataService_stub.findMemberByID(customerID);
        if (memberPO==null)
            return null;

        return new MemberVO(memberPO.getID(),memberPO.getMemberType(),memberPO.getLevel(),memberPO.getBirthday(),
                memberPO.getCompanyName());
    }
}
