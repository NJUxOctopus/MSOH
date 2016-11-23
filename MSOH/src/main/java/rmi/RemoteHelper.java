package rmi;

import dataservice.clerk_dataservice.Clerk_DataService;
import dataservice.customer_dataservice.Customer_DataService;
import dataservice.hotel_dataservice.Hotel_DataService;
import dataservice.manager_dataservice.Manager_DataService;
import dataservice.marketer_dataservice.Marketer_DataService;
import dataservice.member_dataservice.Member_DataService;
import dataservice.memberlevel_dataservice.MemberLevel_DataService;
import dataservice.order_dataservice.Order_DataService;
import dataservice.promotion_dataservice.Promotion_DataService;

import java.rmi.Remote;

/**
 * Created by zqh on 2016/11/13.
 */
public class RemoteHelper {
    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();

    public static RemoteHelper getInstance() {
        return remoteHelper;
    }

    private RemoteHelper() {
    }

    public void setRemote(Remote remote) {
        this.remote = remote;
    }

    public Clerk_DataService getClerkDataService() {
        return (Clerk_DataService) remote;
    }

    public Customer_DataService getCustomerDataService() {
        return (Customer_DataService) remote;
    }

    public Hotel_DataService getHotelDataService() {
        return (Hotel_DataService) remote;
    }

    public Manager_DataService getManagerDataService() {
        return (Manager_DataService) remote;
    }

    public Marketer_DataService getMarketerDataService() {
        return (Marketer_DataService) remote;
    }

    public Member_DataService getMemberDataService() {
        return (Member_DataService) remote;
    }

    public MemberLevel_DataService getMemberLevelDataService() {
        return (MemberLevel_DataService) remote;
    }

    public Order_DataService getOrderDataService() {
        return (Order_DataService) remote;
    }

    public Promotion_DataService getPromotionDataService() {
        return (Promotion_DataService) remote;
    }
}
