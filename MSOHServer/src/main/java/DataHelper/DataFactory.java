package DataHelper;

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

/**
 * Created by zqh on 2016/11/20.
 */
public interface DataFactory {
    public ClerkDataHelper getClerkDataHelper();

    public CustomerDataHelper getCustomerDataHelper();

    public HotelDataHelper getHotelDataHelper();

    public ManagerDataHelper getManagerDataHelper();

    public MarketerDataHelper getMarketerDataHelper();

    public MemberDataHelper getMemberDataHelper();

    public MemberLevelDataHelper getMemberLevelDataHelper();

    public OrderDataHelper getOrderDataHelper();

    public PromotionDataHelper getPromotionDataHelper();

    public CreditRecordDataHelper getCreditRecordDataHelper();

    public CommentDataHelper getCommentDataHelper();

    public CityDataHelper getCityDataHelper();

    public RoomDataHelper getRoomDataHelper();

    public CompanyDataHelper getCompanyDataHelper();

}
