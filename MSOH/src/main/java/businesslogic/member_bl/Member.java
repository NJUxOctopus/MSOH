package businesslogic.member_bl;

import businesslogicservice.member_blservice.Member_BLService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import dataservice.member_dataservice.Member_DataService_Stub;
import po.MemberPO;
import rmi.RemoteHelper;
import util.MemberType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class Member implements Member_BLService {
    Member_DataService_Stub member_dataService_stub = new Member_DataService_Stub();
    Customer_DataService_Stub customer_dataService_stub = new Customer_DataService_Stub();

    /**
     * 会员注册
     *
     * @param memberVO
     * @param customerID
     * @return
     * @throws RemoteException
     */
    public ResultMessage signUp(MemberVO memberVO, String customerID) throws RemoteException {
        if (customer_dataService_stub.findCustomerByID(customerID).getMemberType().equals(MemberType.NONMEMBER)) {//首先用户必须是非会员
            if (memberVO.memberType.equals(MemberType.ENTREPRISE)) {//若注册为企业会员
                if (memberVO.companyName.equals(""))//企业名输入不能为空
                    return ResultMessage.Blank;
                else {
                    member_dataService_stub.addMember(new MemberPO(customerID, memberVO.memberType,
                            memberVO.level, memberVO.birthday, memberVO.companyName));
                    return ResultMessage.Member_EnterpriseSignupSuccess;
                }
            } else {//若注册为普通会员
                if (memberVO.birthday == null)//会员生日不能为空
                    return ResultMessage.Blank;
                else {
                    member_dataService_stub.addMember(new MemberPO(customerID, memberVO.memberType,
                            memberVO.level, memberVO.birthday, memberVO.companyName));
                    return ResultMessage.Member_NormalSignupSuccess;
                }
            }
        } else
            return ResultMessage.Member_AddMemberAlreadyExist;
    }
}
