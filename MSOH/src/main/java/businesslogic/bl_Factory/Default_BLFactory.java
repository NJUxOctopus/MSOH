package businesslogic.bl_Factory;

import businesslogic.clerk_bl.ClerkUtil;
import businesslogic.customer_bl.Customer;
import businesslogic.customer_bl.CustomerUtil;
import businesslogic.hotel_bl.Hotel;
import businesslogic.hotel_bl.HotelUtil;
import businesslogic.manager_bl.ManagerUtil;
import businesslogic.marketer_bl.MarketerUtil;
import businesslogic.member_bl.Member;
import businesslogic.member_bl.MemberLevel;
import businesslogic.member_bl.MemberUtil;
import businesslogic.order_bl.Order;
import businesslogic.promotion_bl.Promotion;
import businesslogic.promotion_bl.PromotionUtil;

/**
 * Created by Pxr on 16/12/13.
 */
public class Default_BLFactory extends Abstract_BLFactory {
    @Override
    public Promotion createPromotion() {
        return new Promotion();
    }

    @Override
    public HotelUtil createHotelUtil() {
        return new HotelUtil();
    }

    @Override
    public CustomerUtil createCustomerUtil() {
        return new CustomerUtil();
    }

    @Override
    public Hotel createHotel() {
        return new Hotel();
    }

    @Override
    public Order createOrder() {
        return new Order();
    }

    @Override
    public ManagerUtil createManagerUtil() {
        return new ManagerUtil();
    }

    @Override
    public PromotionUtil createPromotionUtil() {
        return new PromotionUtil();
    }

    @Override
    public MarketerUtil createMarketerUtil() {
        return new MarketerUtil();
    }

    @Override
    public ClerkUtil createClerkUtil() {
        return new ClerkUtil();
    }

    @Override
    public MemberUtil createMemberUtil() {
        return new MemberUtil();
    }

    @Override
    public Member createMember() {
        return new Member();
    }

    @Override
    public Customer createCustomer() {
        return new Customer();
    }

    @Override
    public MemberLevel createMemberLevel() {
        return new MemberLevel();
    }
}
