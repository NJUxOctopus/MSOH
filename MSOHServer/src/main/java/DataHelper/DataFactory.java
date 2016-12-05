package DataHelper;

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

}
