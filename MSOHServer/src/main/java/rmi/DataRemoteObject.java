package rmi;

import data.clerk_dataserviceImpl.Clerk_DataServiceImpl;
import data.customer_dataserviceImpl.Customer_DataServiceImpl;
import data.hotel_dataserviceImpl.City_DataServiceImpl;
import data.hotel_dataserviceImpl.Hotel_DataServiceImpl;
import data.manager_dataserviceImpl.Manager_DataServiceImpl;
import data.marketer_dataserviceImpl.Marketer_DataServiceImpl;
import data.member_dataserviceImpl.Company_DataServiceImpl;
import data.member_dataserviceImpl.Member_DataServiceImpl;
import data.memberlevel_dataserviceImpl.MemberLevel_DataServiceImpl;
import data.order_dataserviceImpl.Order_DataServiceImpl;
import data.promotion_dataserviceImpl.Promotion_DataServiceImpl;
import dataservice.clerk_dataservice.Clerk_DataService;
import dataservice.customer_dataservice.Customer_DataService;
import dataservice.hotel_dataservice.City_DataService;
import dataservice.hotel_dataservice.Hotel_DataService;
import dataservice.manager_dataservice.Manager_DataService;
import dataservice.marketer_dataservice.Marketer_DataService;
import dataservice.member_dataservice.Company_DataService;
import dataservice.member_dataservice.Member_DataService;
import dataservice.memberlevel_dataservice.MemberLevel_DataService;
import dataservice.order_dataservice.Order_DataService;
import dataservice.promotion_dataservice.Promotion_DataService;
import po.*;
import util.OrderStatus;
import util.PromotionType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zqh on 2016/11/13.
 */
public class DataRemoteObject extends UnicastRemoteObject implements Clerk_DataService, Customer_DataService,City_DataService, Hotel_DataService, Manager_DataService, Marketer_DataService, MemberLevel_DataService, Member_DataService, Order_DataService, Promotion_DataService,Company_DataService {


    private Customer_DataService customer_dataService;
    private Hotel_DataService hotel_dataService;
    private Manager_DataService manager_dataService;
    private Order_DataService order_dataService;
    private Clerk_DataService clerk_dataService;
    private Promotion_DataService promotion_dataService;
    private Marketer_DataService marketer_dataService;
    private Member_DataService member_dataService;
    private MemberLevel_DataService memberLevel_dataService;
    private City_DataService city_dataService;
    private Company_DataService company_dataService;

    protected DataRemoteObject() throws RemoteException {
        clerk_dataService = Clerk_DataServiceImpl.getInstance();
        manager_dataService = Manager_DataServiceImpl.getInstance();
        marketer_dataService = Marketer_DataServiceImpl.getInstance();
        order_dataService = Order_DataServiceImpl.getInstance();
        marketer_dataService = Marketer_DataServiceImpl.getInstance();
        memberLevel_dataService = MemberLevel_DataServiceImpl.getInstance();
        member_dataService = Member_DataServiceImpl.getInstance();
        customer_dataService = Customer_DataServiceImpl.getInstance();
        hotel_dataService= Hotel_DataServiceImpl.getInstance();
        city_dataService= City_DataServiceImpl.getInstance();
        promotion_dataService= Promotion_DataServiceImpl.getInstance();
        company_dataService= Company_DataServiceImpl.getInstance();
    }

    /**
     * ClerkDataService的DataRemoteObject
     */
    public boolean addClerk(ClerkPO clerkPO) throws RemoteException {
        return clerk_dataService.addClerk(clerkPO);
    }

    public boolean modifyClerk(ClerkPO clerkPO) throws RemoteException {
        return clerk_dataService.modifyClerk(clerkPO);
    }

    public List<ClerkPO> findClerkByName(String name) throws IOException, ClassNotFoundException {
        return clerk_dataService.findClerkByName(name);
    }

    public ClerkPO findClerkByID(String id) throws RemoteException {
        return clerk_dataService.findClerkByID(id);
    }

    public List<ClerkPO> findAllClerks() throws IOException, ClassNotFoundException {
        return clerk_dataService.findAllClerks();
    }

    public boolean deleteClerk(ClerkPO clerkPO) throws RemoteException {
        return clerk_dataService.deleteClerk(clerkPO);
    }

    /**
     * CustomerDataService的DataRemoteObject
     */
    public boolean addCustomer(CustomerPO customerPO) throws RemoteException {
        return customer_dataService.addCustomer(customerPO);
    }

    public boolean modifyCustomer(CustomerPO customerPO) throws RemoteException {
        return customer_dataService.modifyCustomer(customerPO);
    }

    public boolean deleteCustomer(CustomerPO customerPO) throws RemoteException {
        return customer_dataService.deleteCustomer(customerPO);
    }

    public List<CustomerPO> findCustomerByName(String customerName) throws IOException, ClassNotFoundException {
        return customer_dataService.findCustomerByName(customerName);
    }

    public CustomerPO findCustomerByID(String customerID) throws RemoteException {
        return customer_dataService.findCustomerByID(customerID);
    }

    public List<CustomerPO> findAllCustomers() throws IOException, ClassNotFoundException {
        return customer_dataService.findAllCustomers();
    }

    public List<HotelPO> getCustomerReservedHotel(String ID) throws IOException, ClassNotFoundException {
        return customer_dataService.getCustomerReservedHotel(ID);
    }

    public boolean addCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return customer_dataService.addCreditRecord(creditRecordPO);
    }

    public boolean deleteCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return customer_dataService.deleteCreditRecord(creditRecordPO);
    }

    public List<CreditRecordPO> findCreditRecordByID(String ID) throws IOException, ClassNotFoundException {
        return customer_dataService.findCreditRecordByID(ID);
    }

    /**
     * CityDataService的DataRemoteObject
     */
    public List<String> getAllCities() throws RemoteException {
        return city_dataService.getAllCities();
    }

    public List<String> getAreaByCity(String city) throws RemoteException {
        return city_dataService.getAreaByCity(city);
    }

    /**
     * HotelDataService的DataRemoteObject
     */
    public boolean addHotel(HotelPO po) throws IOException,ClassNotFoundException {
        return hotel_dataService.addHotel(po);
    }

    public boolean modifyHotel(HotelPO po) throws RemoteException {
        return hotel_dataService.modifyHotel(po);
    }

    public boolean deleteHotel(HotelPO po) throws RemoteException {
        return hotel_dataService.deleteHotel(po);
    }

    public List<HotelPO> getHotels() throws IOException, ClassNotFoundException {
        return hotel_dataService.getHotels();
    }

    public HotelPO findHotelByID(String hotelID) throws RemoteException {
        return hotel_dataService.findHotelByID(hotelID);
    }

    public List<HotelPO> findHotelByName(String hotelName) throws IOException, ClassNotFoundException {
        return hotel_dataService.findHotelByName(hotelName);
    }

    public List<HotelPO> getHotelByArea(String areaName) throws IOException, ClassNotFoundException {
        return hotel_dataService.getHotelByArea(areaName);
    }

    public DailyRoomInfoPO getDailyRoomInfo(String hotelID, Timestamp date) throws RemoteException {
        return hotel_dataService.getDailyRoomInfo(hotelID,date);
    }

    public boolean addDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        return hotel_dataService.addDailyRoomInfo(dailyRoomInfoPO);
    }

    public boolean deleteDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        return hotel_dataService.deleteDailyRoomInfo(dailyRoomInfoPO);
    }

    public boolean updateDailyRoomInfo(DailyRoomInfoPO dailyRoomInfoPO) throws RemoteException {
        return hotel_dataService.updateDailyRoomInfo(dailyRoomInfoPO);
    }

    public boolean addComment(CommentPO po) throws RemoteException {
        return hotel_dataService.addComment(po);
    }

    public List<CommentPO> getCommentByHotel(String hotelID) throws IOException, ClassNotFoundException {
        return hotel_dataService.getCommentByHotel(hotelID);
    }

    public CommentPO getCommentByOrder(String orderID) throws RemoteException {
        return hotel_dataService.getCommentByOrder(orderID);
    }

    /**
     * ManagerDataService的DataRemoteObject
     */
    public boolean modifyManager(ManagerPO managerPO) throws RemoteException {
        return manager_dataService.modifyManager(managerPO);
    }

    public ManagerPO findManagerByID(String ID) throws RemoteException {
        return manager_dataService.findManagerByID(ID);
    }

    public List<ManagerPO> findAllManagers() throws IOException, ClassNotFoundException {
        return manager_dataService.findAllManagers();
    }

    public List<ManagerPO> findManagerByName(String name) throws IOException, ClassNotFoundException {
        return manager_dataService.findManagerByName(name);
    }

    /**
     * MarketerDataService的DataRemoteObject
     */
    public boolean addMarketer(MarketerPO marketerPO) throws RemoteException {
        return marketer_dataService.addMarketer(marketerPO);
    }

    public boolean modifyMarketer(MarketerPO marketerPO) throws RemoteException {
        return marketer_dataService.modifyMarketer(marketerPO);
    }

    public List<MarketerPO> findMarketerByName(String name) throws IOException, ClassNotFoundException {
        return marketer_dataService.findMarketerByName(name);
    }

    public MarketerPO findMarketerByID(String id) throws RemoteException {
        return marketer_dataService.findMarketerByID(id);
    }

    public List<MarketerPO> findAllMarketers() throws IOException, ClassNotFoundException {
        return marketer_dataService.findAllMarketers();
    }

    public boolean deleteMarketer(MarketerPO marketerPO) throws RemoteException {
        return marketer_dataService.deleteMarketer(marketerPO);
    }

    /**
     * MemberDataService的DataRemoteObject
     */
    public boolean addMember(MemberPO po) throws RemoteException {
        return member_dataService.addMember(po);
    }

    public boolean deleteMember(MemberPO po) throws RemoteException {
        return member_dataService.deleteMember(po);
    }

    public boolean updateMember(MemberPO po) throws RemoteException {
        return member_dataService.updateMember(po);
    }

    public MemberPO findMemberByID(String ID) throws RemoteException {
        return member_dataService.findMemberByID(ID);
    }

    public List<MemberPO> findAllMembers() throws IOException, ClassNotFoundException {
        return member_dataService.findAllMembers();
    }

    /**
     * MemberLevelDataService的DataRemoteObject
     */
    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return memberLevel_dataService.updateMemberLevel(memberLevelPO);
    }

    public MemberLevelPO getMemberLevel() throws RemoteException {
        return memberLevel_dataService.getMemberLevel();
    }

    /**
     * OrderDataService的DataRemoteObject
     */
    public boolean addOrder(OrderPO po) throws IOException, ClassNotFoundException {
        return order_dataService.addOrder(po);
    }

    public List<OrderPO> getAllOrders() throws IOException, ClassNotFoundException {
        return order_dataService.getAllOrders();
    }

    public OrderPO getOrderByOrderID(String orderID) throws RemoteException {
        return order_dataService.getOrderByOrderID(orderID);
    }

    public List<OrderPO> findOrderByCustomerID(String customerID) throws IOException, ClassNotFoundException {
        return order_dataService.findOrderByCustomerID(customerID);
    }

    public List<OrderPO> findOrderByHotelID(String hotelID) throws IOException, ClassNotFoundException {
        return order_dataService.findOrderByHotelID(hotelID);
    }

    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) throws IOException, ClassNotFoundException {
        return order_dataService.findOrderByOrderStatus(orderStatus);
    }

    public boolean updateOrder(OrderPO orderPO) throws RemoteException {
        return order_dataService.updateOrder(orderPO);
    }

    public boolean deleteOrder(OrderPO orderPO) throws RemoteException {
        return order_dataService.deleteOrder(orderPO);
    }

    /**
     * PromotionDataService的DataRemoteObject
     */
    public boolean addPromotion(PromotionPO po) throws RemoteException {
        return promotion_dataService.addPromotion(po);
    }

    public PromotionPO getPromotion(int promotionID) throws RemoteException {
        return promotion_dataService.getPromotion(promotionID);
    }

    public List<PromotionPO> getAllWebPromotions() throws IOException, ClassNotFoundException {
        return promotion_dataService.getAllWebPromotions();
    }

    public List<PromotionPO> getPromotionByHotelID(String hotelID) throws IOException, ClassNotFoundException {
        return promotion_dataService.getPromotionByHotelID(hotelID);
    }

    public boolean deletePromotion(PromotionPO promotionPO) throws RemoteException {
        return promotion_dataService.deletePromotion(promotionPO);
    }

    public boolean modifyPromotion(PromotionPO promotionPO) throws RemoteException {
        return promotion_dataService.modifyPromotion(promotionPO);
    }

    public List<PromotionPO> getPromotionByPromotionType(PromotionType promotionType) throws IOException, ClassNotFoundException {
        return promotion_dataService.getPromotionByPromotionType(promotionType);
    }

    /**
     * CompanyDataService的DataRemoteObject
     */
    public List<String> getAllCompanies() throws IOException, ClassNotFoundException {
        return company_dataService.getAllCompanies();
    }
}
