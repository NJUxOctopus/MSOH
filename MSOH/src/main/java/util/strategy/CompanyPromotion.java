package util.strategy;

import businesslogic.customer_bl.CustomerUtil;
import businesslogic.member_bl.MemberUtil;
import util.MemberType;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;

/**
 * Created by Pxr on 16/12/8.
 */
public class CompanyPromotion implements Strategy {
    String companyName;

    public CompanyPromotion(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Boolean usePromotion(OrderVO orderVO) throws RemoteException {
        CustomerUtil customerUtil = new CustomerUtil();
        MemberUtil memberUtil = new MemberUtil();
        if (!customerUtil.getSingle(orderVO.customerID).memberType.equals(MemberType.ENTREPRISE))
            return false;
        if (memberUtil.getSingle(orderVO.customerID).companyName.equals(companyName))
            return true;
        return false;
    }
}
