package businesslogic.bl_Factory;

import businesslogic.clerk_bl.ClerkUtil;
import businesslogic.customer_bl.CustomerUtil;
import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogic.manager_bl.ManagerUtil;
import businesslogic.marketer_bl.MarketerUtil;
import businesslogic.member_bl.MemberUtil;
import businesslogic.promotion_bl.Promotion;
import businesslogic.promotion_bl.PromotionUtil;

/**
 * Created by Pxr on 16/12/13.
 */
public abstract class Abstract_BLFactory {
    public abstract HotelUtil createHotelUtil();

    public abstract Hotel createHotel();

    public abstract Promotion createPromotion();

    public abstract CustomerUtil createCustomerUtil();

    public abstract PromotionUtil createPromotionUtil();

    public abstract ManagerUtil createManagerUtil();

    public abstract MarketerUtil createMarketerUtil();

    public abstract ClerkUtil createClerkUtil();

    public abstract MemberUtil createMemberUtil();

}
