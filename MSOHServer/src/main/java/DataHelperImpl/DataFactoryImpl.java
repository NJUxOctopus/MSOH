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
        return null;
    }

    public OrderDataHelper getOrderDataHelper() {
        return null;
    }

    public PromotionDataHelper getPromotionDataHelper() {
        return null;
    }
}
