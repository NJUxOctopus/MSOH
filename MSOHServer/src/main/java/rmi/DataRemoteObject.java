package rmi;

import data.Clerk_DataServiceImpl;
import data.Manager_DataServiceImpl;
import data.Marketer_DataServiceImpl;
import dataservice.clerk_dataservice.Clerk_DataService;
import dataservice.customer_dataservice.Customer_DataService;
import dataservice.hotel_dataservice.Hotel_DataService;
import dataservice.manager_dataservice.Manager_DataService;
import dataservice.marketer_dataservice.Marketer_DataService;
import dataservice.member_dataservice.Member_DataService;
import dataservice.memberlevel_dataservice.MemberLevel_DataService;
import dataservice.order_dataservice.Order_DataService;
import dataservice.promotion_dataservice.Promotion_DataService;
import po.*;
import util.OrderStatus;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 * Created by zqh on 2016/11/13.
 */
public class DataRemoteObject extends UnicastRemoteObject implements Clerk_DataService,Customer_DataService,Hotel_DataService,Manager_DataService,Marketer_DataService,MemberLevel_DataService,Member_DataService,Order_DataService,Promotion_DataService{


    private Customer_DataService customer_dataService;
    private Hotel_DataService hotel_dataService;
    private Manager_DataService manager_dataService;
    private Order_DataService order_dataService;
    private Clerk_DataService clerk_dataService;
    private Promotion_DataService promotion_dataService;
    private Marketer_DataService marketer_dataService;
    private Member_DataService member_dataService;

    protected DataRemoteObject() throws RemoteException{
        clerk_dataService= Clerk_DataServiceImpl.getInstance();
        manager_dataService= Manager_DataServiceImpl.getInstance();
        marketer_dataService= Marketer_DataServiceImpl.getInstance();
        // TODO 初始化
    }

    // TODO 方法后添加异常抛出


    public void addClerk(ClerkPO clerkPO) throws RemoteException {
        clerk_dataService.addClerk(clerkPO);
    }

    public void modifyClerk(ClerkPO clerkPO) throws RemoteException {
        clerk_dataService.modifyClerk(clerkPO);
    }

    public List<ClerkPO> findClerkByName(String name) throws RemoteException {
        return clerk_dataService.findClerkByName(name);
    }

    public ClerkPO findClerkByID(String id) throws RemoteException {
        return clerk_dataService.findClerkByID(id);
    }

    public List<ClerkPO> findAllClerks() throws RemoteException {
        return clerk_dataService.findAllClerks();
    }

    //

    public boolean addMember(MemberPO po) throws RemoteException {
        return false;
    }

    public void modifyManager(ManagerPO managerPO) throws RemoteException {

    }

    public void addMarketer(MarketerPO marketerPO) throws RemoteException {

    }

    public boolean addPromotion(PromotionPO po) throws RemoteException {
        return false;
    }

    public boolean addMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    public boolean addOrder(OrderPO po) throws RemoteException {
        return false;
    }

    public boolean addCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    public boolean deleteMember(MemberPO po) throws RemoteException {
        return false;
    }

    public boolean addHotel(HotelPO po) throws RemoteException {
        return false;
    }

    public void modifyMarketer(MarketerPO marketerPO) throws RemoteException {

    }

    public PromotionPO getPromotion(String promotionID) throws RemoteException {
        return null;
    }

    public List<OrderPO> getAllOrders() throws RemoteException {
        return null;
    }

    public ManagerPO findManagerByID(String ID) throws RemoteException {
        return null;
    }

    public boolean deleteMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    public boolean updateMember(MemberPO po) throws RemoteException {
        return false;
    }

    public boolean modifyCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    public boolean modifyHotel(HotelPO po) throws RemoteException {
        return false;
    }

    public OrderPO getOrderByOrderID(String OrderID) throws RemoteException {
        return null;
    }

    public List<PromotionPO> getAllPromotions() throws RemoteException {
        return null;
    }

    public List<ManagerPO> findAllManagers() throws RemoteException {
        return null;
    }

    public List<MarketerPO> findMarketerByName(String name) throws RemoteException {
        return null;
    }

    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return false;
    }

    public MemberPO findMemberByID(String ID) throws RemoteException {
        return null;
    }

    public boolean deleteHotel(HotelPO po) throws RemoteException {
        return false;
    }

    public boolean deleteCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }

    public List<OrderPO> findOrderByCustomerID(String customerID) throws RemoteException {
        return null;
    }

    public List<ManagerPO> findManagerByName(String name) throws RemoteException {
        return null;
    }

    public MarketerPO findMarketerByID(String id) throws RemoteException {
        return null;
    }

    public List<MemberPO> findAllMemebers() throws RemoteException {
        return null;
    }

    public List<HotelPO> getHotels() throws RemoteException {
        return null;
    }

    public MemberLevelPO getMemberLevel() throws RemoteException {
        return null;
    }

    public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }

    public List<OrderPO> findOrderByHotelID(String hotelID) throws RemoteException {
        return null;
    }

    public List<CustomerPO> findCustomerByName(String customerName) throws RemoteException {
        return null;
    }

    public HotelPO findHotelByID(String hotelID) throws RemoteException {
        return null;
    }

    public List<MarketerPO> findAllMarketers() throws RemoteException {
        return null;
    }

    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) throws RemoteException {
        return null;
    }

    public void deleteMarketer(MarketerPO marketerPO) throws RemoteException {

    }

    public CustomerPO findCustomerByID(String customerID) throws RemoteException {
        return null;
    }

    public List<HotelPO> findHotelByName(String hotelName) throws RemoteException {
        return null;
    }

    public boolean updateOrder(OrderPO orderPO) throws RemoteException {
        return false;
    }

    public boolean addRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public List<CustomerPO> findAllCustomers() throws RemoteException {
        return null;
    }

    public boolean deleteOrder(OrderPO orderPO) throws RemoteException {
        return false;
    }

    public boolean modifyRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public List<HotelPO> getCustomerReservedHotel(String ID) throws RemoteException {
        return null;
    }

    public boolean deleteRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public boolean addCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return false;
    }

    public RoomPO getRoom(String hotelID, String roomName) throws RemoteException {
        return null;
    }

    public boolean deleteCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return false;
    }

    public List<RoomPO> getHotelRooms(String hotelID) throws RemoteException {
        return null;
    }

    public List<CreditRecordPO> findCreditRecordByID(String ID) throws RemoteException {
        return null;
    }

    public DailyRoomInfoPO getDailyRoomInfo(String hotelID, Date date) throws RemoteException {
        return null;
    }

    public boolean setDailyRoomInfo(List<DailyRoomInfoPO> list) throws RemoteException {
        return false;
    }

    public boolean addComment(CommentPO po) throws RemoteException {
        return false;
    }

    public List<CommentPO> getCommentByHotel(String hotelID) throws RemoteException {
        return null;
    }

    public CommentPO getCommentByOrder(String orderID) throws RemoteException {
        return null;
    }

    public void deleteClerk(ClerkPO clerkPO) throws RemoteException {
        clerk_dataService.deleteClerk(clerkPO);
    }
}
