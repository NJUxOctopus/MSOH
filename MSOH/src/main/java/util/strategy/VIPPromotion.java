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
 * Created by Pxr on 16/12/16.
 */
public class VIPPromotion implements Strategy {
    private Abstract_BLFactory abstract_blFactory = new Default_BLFactory();
    private CustomerUtil customerUtil = abstract_blFactory.createCustomerUtil();
    private MemberUtil memberUtil = abstract_blFactory.createMemberUtil();
    private int memberLevel;

    public VIPPromotion(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    @Override
    public Boolean usePromotion(OrderVO orderVO) throws RemoteException {
        if (!customerUtil.getSingle(orderVO.customerID).memberType.equals(MemberType.NORMAL))
            return false;
        if (memberUtil.getSingle(orderVO.customerID).level>=memberLevel)
            return true;
        return false;
    }
}
