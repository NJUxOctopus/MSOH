package util.strategy;

import businesslogic.bl_Factory.Abstract_BLFactory;
import businesslogic.bl_Factory.Default_BLFactory;
import businesslogic.customer_bl.CustomerUtil;
import businesslogic.member_bl.MemberUtil;
import util.CalculateDays;
import util.MemberType;
import vo.OrderPriceVO;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;

/**
 * Created by Pxr on 16/12/7.
 */
public class BirthdayPromotion implements Strategy {
    private Abstract_BLFactory abstract_blFactory = new Default_BLFactory();
    private CustomerUtil customerUtil = abstract_blFactory.createCustomerUtil();
    private MemberUtil memberUtil = abstract_blFactory.createMemberUtil();
    /**
     * 判断生日特惠能否使用
     * @param orderVO
     * @return
     * @throws RemoteException
     */
    @Override
    public Boolean usePromotion(OrderVO orderVO) throws RemoteException {
        if (!customerUtil.getSingle(orderVO.customerID).memberType.equals(MemberType.NORMAL))
            return false;
        Timestamp startTime = orderVO.estimatedCheckinTime;
        Timestamp endTime = orderVO.estimatedCheckoutTime;
        if (memberUtil.getSingle(orderVO.customerID).birthday.after(startTime) &&
                memberUtil.getSingle(orderVO.customerID).birthday.before(endTime))
            return true;
        return false;
    }
}
