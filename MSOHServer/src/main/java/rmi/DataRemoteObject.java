package rmi;

import dataservice.clerk_dataservice.Clerk_DataService;
import dataservice.customer_dataservice.Customer_DataService;
import dataservice.hotel_dataservice.Hotel_DataService;
import dataservice.manager_dataservice.Manager_DataService;
import dataservice.marketer_dataservice.Marketer_DataService;
import dataservice.member_dataservice.Member_DataService;
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
public class DataRemoteObject extends UnicastRemoteObject implements Clerk_DataService,Customer_DataService,Hotel_DataService,Manager_DataService,Marketer_DataService,Member_DataService,Order_DataService,Promotion_DataService{


    private Customer_DataService customer_dataService;
    private Hotel_DataService hotel_dataService;
    private Manager_DataService manager_dataService;
    private Marketer_DataService marketer_dataService;
    private Member_DataService member_dataService;
    private Order_DataService order_dataService;
    private Clerk_DataService clerk_dataService;
    private Promotion_DataService promotion_dataService;

    protected DataRemoteObject() throws RemoteException{
        // TODO 初始化
    }



    // TODO 修改data层的方法，围绕增删改查提供
    // TODO 方法后添加异常抛出


    public boolean modifyManager(ManagerPO po) throws RemoteException {
        return false;
    }

    public boolean addMember(MemberPO po) throws RemoteException {
        return false;
    }

    public boolean addClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }

    public boolean addMarketer(MarketerPO marketerPO) throws RemoteException {
        return false;
    }

    public boolean addPromotion(PromotionPO po) throws RemoteException {
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

    public ManagerPO findManager(String ID) throws RemoteException {
        return null;
    }

    public boolean addHotel(HotelPO po) throws RemoteException {
        return false;
    }

    public boolean modifyClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }

    public PromotionPO getPromotion(String promotionID) throws RemoteException {
        return null;
    }

    public List<OrderPO> getAllOrders() throws RemoteException {
        return null;
    }

    public boolean modifyMarketer(MarketerPO marketerPO) throws RemoteException {
        return false;
    }

    public boolean modifyCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    public boolean updateMember(MemberPO po) throws RemoteException {
        return false;
    }

    public boolean modifyHotel(HotelPO po) throws RemoteException {
        return false;
    }

    public OrderPO getOrderByOrderID(String OrderID) throws RemoteException {
        return null;
    }

    public List<ClerkPO> findClerkByName(String name) throws RemoteException {
        return null;
    }

    public List<PromotionPO> getAllPromotions() throws RemoteException {
        return null;
    }

    public List<MarketerPO> findMarketerByName(String name) throws RemoteException {
        return null;
    }

    public boolean deleteCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    public MemberPO findMemberByID(String ID) throws RemoteException {
        return null;
    }

    public boolean deleteHotel(HotelPO po) throws RemoteException {
        return false;
    }

    public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }

    public List<OrderPO> findOrderByCustomerID(String customerID) throws RemoteException {
        return null;
    }

    public ClerkPO findClerkByID(String id) throws RemoteException {
        return null;
    }

    public MarketerPO findMarketerByID(String id) throws RemoteException {
        return null;
    }

    public List<MemberPO> findAllMemebers() throws RemoteException {
        return null;
    }

    public List<HotelPO> findHotels(String address, String area, Date expected_date_of_arrival, Date expected_date_of_departure, int star, double score) throws RemoteException {
        return null;
    }

    public List<CustomerPO> findCustomerByName(String customerName) throws RemoteException {
        return null;
    }

    public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException {
        return false;
    }

    public List<ClerkPO> findAllClerks() throws RemoteException {
        return null;
    }

    public List<OrderPO> findOrderByHotelID(String hotelID) throws RemoteException {
        return null;
    }

    public List<MarketerPO> findAllMarketers() throws RemoteException {
        return null;
    }

    public boolean deleteClerk(ClerkPO clerkPO) throws RemoteException {
        return false;
    }

    public CustomerPO findCustomerByID(String customerID) throws RemoteException {
        return null;
    }

    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) throws RemoteException {
        return null;
    }

    public boolean deleteMarketer(MarketerPO marketerPO) throws RemoteException {
        return false;
    }

    public HotelPO findHotelByID(String hotelID) throws RemoteException {
        return null;
    }

    public boolean updateOrder(OrderPO orderPO) throws RemoteException {
        return false;
    }

    public List<CustomerPO> findAllCustomers() throws RemoteException {
        return null;
    }

    public List<HotelPO> findHotelByName(String hotelName) throws RemoteException {
        return null;
    }

    public boolean deleteOrder(OrderPO orderPO) throws RemoteException {
        return false;
    }

    public List<HotelPO> getCustomerReservedHotel(String ID) throws RemoteException {
        return null;
    }

    public boolean addRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public boolean modifyRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public boolean deleteRoom(RoomPO po) throws RemoteException {
        return false;
    }

    public RoomPO getRoom(String hotelID, String roomName) throws RemoteException {
        return null;
    }

    public List<RoomPO> getHotelRooms(String hotelID) throws RemoteException {
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
}
