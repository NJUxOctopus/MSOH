package util.strategy;

import businesslogic.customer_bl.CustomerUtil;
import businesslogic.hotel_bl.HotelUtil;
import po.PromotionPO;
import util.MemberType;
import vo.OrderVO;
import vo.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/12/7.
 */
public class NormalPromotion implements Strategy {
    MemberType memberType;
    int roomNum;

    public NormalPromotion(MemberType memberType, int roomNum) {
        this.memberType = memberType;
        this.roomNum = roomNum;
    }

    @Override
    public Boolean usePromotion(OrderVO orderVO) throws RemoteException {
        CustomerUtil customerUtil = new CustomerUtil();

        boolean memberTypeMeetReq = false;//用来判断会员类型是否符合
        if (memberType.equals(MemberType.NONMEMBER)) {
            //若要求为非会员，所有类型都满足
            memberTypeMeetReq = true;
        } else if (memberType.equals(customerUtil.getSingle(orderVO.customerID).memberType)) {
            memberTypeMeetReq = true;
        }

        if (roomNum >= orderVO.rooms.length &&  memberTypeMeetReq)
            return true;
        else
            return false;
    }

}
