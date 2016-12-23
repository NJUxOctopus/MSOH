package util.strategy;

import businesslogic.bl_Factory.Abstract_BLFactory;
import businesslogic.bl_Factory.Default_BLFactory;
import businesslogic.customer_bl.CustomerUtil;
import businesslogic.member_bl.MemberUtil;
import util.MemberType;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/12/19.
 */
public class MemberLevelPromotion implements Strategy {

    private Abstract_BLFactory abstract_blFactory = new Default_BLFactory();
    private CustomerUtil customerUtil = abstract_blFactory.createCustomerUtil();

    @Override
    public Boolean usePromotion(OrderVO orderVO) throws RemoteException {
        if (customerUtil.getSingle(orderVO.customerID).memberType.equals(MemberType.NORMAL))
            return true;
        return false;
    }
}
