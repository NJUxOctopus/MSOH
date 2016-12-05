package DataHelperImpl;

import DataHelper.*;

/**
 * 获得DataHelper
 * <p>
 * Created by zqh on 2016/11/27.
 */
public class DataFactoryImpl implements DataFactory {

    public ClerkDataHelper getClerkDataHelper() {
        ClerkDataHelper clerkDataHelper = new ClerkDataHelperSQLImpl();
        return clerkDataHelper;
    }

    public CustomerDataHelper getCustomerDataHelper() {
        CustomerDataHelper customerDataHelper = new CustomerDataHelperSQLImpl();
        return customerDataHelper;
    }

    public HotelDataHelper getHotelDataHelper() {
        HotelDataHelper hotelDataHelper = new HotelDataHelperSQLImpl();
        return hotelDataHelper;
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
        MemberDataHelper memberDataHelper = new MemberDataHelperSQLImpl();
        return memberDataHelper;
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

    public CreditRecordDataHelper getCreditRecordDataHelper() {
        CreditRecordDataHelper creditRecordDataHelper = new CreditRecordDataHelperSQLImpl();
        return creditRecordDataHelper;
    }

    public CommentDataHelper getCommentDataHelper() {
        CommentDataHelper commentDataHelper = new CommentDataHelperSQLImpl();
        return commentDataHelper;
    }

    public CityDataHelper getCityDataHelper() {
        CityDataHelper cityDataHelper=new CityDataHelperSQLImpl();
        return cityDataHelper;
    }

    public RoomDataHelper getRoomDataHelper() {
        RoomDataHelper roomDataHelper=new RoomDataHelperSQLImpl();
        return roomDataHelper;
    }
}
