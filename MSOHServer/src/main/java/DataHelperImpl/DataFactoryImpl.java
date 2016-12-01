package DataHelperImpl;

import DataHelper.*;

/**
 * Created by zqh on 2016/11/27.
 */
public class DataFactoryImpl implements DataFactory {

    public ClerkDataHelper getClerkDataHelper() {
        ClerkDataHelper clerkDataHelper = new ClerkDataHelperSQLImpl();
        return clerkDataHelper;
    }

    public CustomerDataHelper getCustomerDataHelper() {
        return null;
    }

    public HotelDataHelper getHotelDataHelper() {
        return null;
    }

    public ManagerDataHelper getManagerDataHelper() {
        ManagerDataHelper managerDataHelper = new ManagerDataHelperSQLImpl();
        return managerDataHelper;
    }

    public MarketerDataHelper getMarketerDataHelper() {
        MarketerDataHelper marketerDataHelper = new MarketerDataHelperSQLImpl();
        return marketerDataHelper;
    }

    public MemberDataHelper getMemberDataHelper() {
        return null;
    }

    public MemberLevelDataHelper getMemberLevelDataHelper() {
        MemberLevelDataHelper memberLevelDataHelper = new MemberLevelDataHelperSQLImpl();
        return memberLevelDataHelper;
    }

    public OrderDataHelper getOrderDataHelper() {
        OrderDataHelper orderDataHelper = new OrderDataHelperSQLImpl();
        return orderDataHelper;
    }

    public PromotionDataHelper getPromotionDataHelper() {
        PromotionDataHelper promotionDataHelper = new PromotionDataHelperSQLImpl();
        return promotionDataHelper;
    }

    public CreditRecordDataHelper getCreditRecordDataHelper(){
        CreditRecordDataHelper creditRecordDataHelper=new CreditRecordDataHelperSQLImpl();
        return creditRecordDataHelper;
    }
}
