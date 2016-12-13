package util.strategy;

import businesslogic.bl_Factory.Abstract_BLFactory;
import businesslogic.bl_Factory.Default_BLFactory;
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
    private Abstract_BLFactory abstract_blFactory = new Default_BLFactory();
    private CustomerUtil customerUtil = abstract_blFactory.createCustomerUtil();
    private MemberUtil memberUtil = abstract_blFactory.createMemberUtil();
    private String companyName;

    public CompanyPromotion(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Boolean usePromotion(OrderVO orderVO) throws RemoteException {
        if (!customerUtil.getSingle(orderVO.customerID).memberType.equals(MemberType.ENTREPRISE))
            return false;
        if (memberUtil.getSingle(orderVO.customerID).companyName.equals(companyName))
            return true;
        return false;
    }
}
