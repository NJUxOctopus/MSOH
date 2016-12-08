package DataHelperImpl;

import DataHelper.*;
import DataHelper.clerkDataHelper.ClerkDataHelper;
import DataHelper.customerDataHelper.CreditRecordDataHelper;
import DataHelper.customerDataHelper.CustomerDataHelper;
import DataHelper.hotelDataHelper.CityDataHelper;
import DataHelper.hotelDataHelper.CommentDataHelper;
import DataHelper.hotelDataHelper.HotelDataHelper;
import DataHelper.hotelDataHelper.RoomDataHelper;
import DataHelper.managerDataHelper.ManagerDataHelper;
import DataHelper.marketerDataHelper.MarketerDataHelper;
import DataHelper.memberDataHelper.CompanyDataHelper;
import DataHelper.memberDataHelper.MemberDataHelper;
import DataHelper.memberLevelDataHelper.MemberLevelDataHelper;
import DataHelper.orderDataHelper.OrderDataHelper;
import DataHelper.promotionDataHelper.PromotionDataHelper;
import DataHelperImpl.clerkDataHelperImpl.ClerkDataHelperSQLImpl;
import DataHelperImpl.customerDataHelperImpl.CreditRecordDataHelperSQLImpl;
import DataHelperImpl.customerDataHelperImpl.CustomerDataHelperSQLImpl;
import DataHelperImpl.hotelDataHelperImpl.CityDataHelperSQLImpl;
import DataHelperImpl.hotelDataHelperImpl.CommentDataHelperSQLImpl;
import DataHelperImpl.hotelDataHelperImpl.HotelDataHelperSQLImpl;
import DataHelperImpl.hotelDataHelperImpl.RoomDataHelperSQLImpl;
import DataHelperImpl.managerDataHelperImpl.ManagerDataHelperSQLImpl;
import DataHelperImpl.marketerDataHelperImpl.MarketerDataHelperSQLImpl;
import DataHelperImpl.memberDataHelperImpl.CompanyDataHelperSQLImpl;
import DataHelperImpl.memberDataHelperImpl.MemberDataHelperSQLImpl;
import DataHelperImpl.memberLevelDataHelperImpl.MemberLevelDataHelperSQLImpl;
import DataHelperImpl.orderDataHelperImpl.OrderDataHelperSQLImpl;
import DataHelperImpl.promotionDataHelperImpl.PromotionDataHelperSQLImpl;

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
        CityDataHelper cityDataHelper = new CityDataHelperSQLImpl();
        return cityDataHelper;
    }

    public RoomDataHelper getRoomDataHelper() {
        RoomDataHelper roomDataHelper = new RoomDataHelperSQLImpl();
        return roomDataHelper;
    }

    public CompanyDataHelper getCompanyDataHelper() {
        CompanyDataHelper companyDataHelper=new CompanyDataHelperSQLImpl();
        return companyDataHelper;
    }
}
