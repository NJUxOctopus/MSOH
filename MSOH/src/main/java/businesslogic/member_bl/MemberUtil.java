package businesslogic.member_bl;

import businesslogicservice.member_blservice.MemberUtil_BLService;
import dataservice.member_dataservice.Company_DataService;
import dataservice.member_dataservice.Member_DataService;
import po.MemberPO;
import rmi.RemoteHelper;
import vo.MemberVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class MemberUtil implements MemberUtil_BLService {
    private Member_DataService member_dataService = RemoteHelper.getInstance().getMemberDataService();
    private Company_DataService company_dataService = RemoteHelper.getInstance().getCompanyDataService();

    /**
     * 根据用户ID得到用户
     *
     * @param customerID
     * @return
     * @throws RemoteException
     */
    public MemberVO getSingle(String customerID) throws RemoteException {
        if (customerID.equals(""))
            //若ID为空
            return null;
        MemberPO memberPO = member_dataService.findMemberByID(customerID);
        if (memberPO == null)
            //若不存在该用户
            return null;

        return new MemberVO(memberPO.getID(), memberPO.getMemberType(), memberPO.getLevel(), memberPO.getBirthday(),
                memberPO.getCompanyName());
    }

    /**
     * @return
     * @throws RemoteException
     */
    public List<String> getCompany() throws RemoteException {
        return company_dataService.getAllCompanies();
    }
}
