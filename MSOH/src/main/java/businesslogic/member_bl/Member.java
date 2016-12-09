package businesslogic.member_bl;

import businesslogic.customer_bl.CustomerUtil;
import businesslogicservice.member_blservice.Member_BLService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import dataservice.member_dataservice.Member_DataService_Stub;
import po.MemberPO;
import rmi.RemoteHelper;
import util.MemberType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.MemberLevelVO;
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
                    if (member_dataService_stub.addMember(new MemberPO(customerID, memberVO.memberType,
                            memberVO.level, memberVO.birthday, memberVO.companyName)))
                        return ResultMessage.Member_EnterpriseSignupSuccess;
                    else
                        return ResultMessage.Fail;
                }
            } else {//若注册为普通会员
                if (memberVO.birthday == null)//会员生日不能为空
                    return ResultMessage.Blank;
                else {
                    if (member_dataService_stub.addMember(new MemberPO(customerID, memberVO.memberType,
                            memberVO.level, memberVO.birthday, memberVO.companyName)))
                        return ResultMessage.Member_NormalSignupSuccess;
                    else
                        return ResultMessage.Fail;
                }
            }
        } else
            return ResultMessage.Member_AddMemberAlreadyExist;
    }

    /**
     * 会员等级的变化
     *
     * @param customerID
     * @return
     * @throws RemoteException
     */
    public ResultMessage changeGrade(String customerID) throws RemoteException {
        if (member_dataService_stub.findMemberByID(customerID) == null)//若该用户不是会员
            return ResultMessage.Customer_isNotMember;
        MemberPO memberPO = member_dataService_stub.findMemberByID(customerID);
        MemberLevel memberLevel = new MemberLevel();
        MemberLevelVO memberLevelVO = memberLevel.getMemberLevel();//获取会员等级制度
        CustomerUtil customerUtil = new CustomerUtil();
        int credit = customerUtil.getSingle(customerID).credit;//该信用值为用户当前信用值
        int[] boundraies = memberLevelVO.creditBoundaries;//会员等级界限
        int initLevel = memberPO.getLevel();//会员当前等级
        if (credit < boundraies[initLevel] && credit > boundraies[initLevel])//若会员信用值依旧在该等级的信用界限中，则等级不变
            return ResultMessage.levelNotChange;
        else {
            if (credit < boundraies[0])//若信用值低于最低要求，则等级为0级，但依旧是会员
                memberPO.setLevel(0);
            else {
                for (int i = 0; i < boundraies.length; i++) {
                    if (i != boundraies.length - 1) {
                        if (credit > boundraies[i] && credit < boundraies[i + 1])//若信用值在两者之间
                            memberPO.setLevel(i + 1);
                    } else if (credit >= i)
                        memberPO.setLevel(i + 1);//若信用值大于最高界限
                }
            }
            if (member_dataService_stub.updateMember(memberPO))
                return ResultMessage.levelChangeSuccess;
        }
        return ResultMessage.Fail;
    }
}
