package util.strategy;

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

    @Override
    public Boolean usePromotion(OrderVO orderVO) throws RemoteException {
        CustomerUtil customerUtil = new CustomerUtil();
        MemberUtil memberUtil = new MemberUtil();
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
