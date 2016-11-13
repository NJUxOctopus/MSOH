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
import util.MemberType;
import util.OrderStatus;
import util.ResultMessage;
import util.WorkerPosition;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 * Created by zqh on 2016/11/13.
 */
public class DataRemoteObject extends UnicastRemoteObject implements Clerk_DataService,Customer_DataService,Hotel_DataService,Manager_DataService,Marketer_DataService,Member_DataService,Order_DataService,Promotion_DataService{

    private Clerk_DataService clerk_dataService;
    private Customer_DataService customer_dataService;
    private Hotel_DataService hotel_dataService;
    private Manager_DataService manager_dataService;
    private Marketer_DataService marketer_dataService;
    private Member_DataService member_dataService;
    private Order_DataService order_dataService;
    private Promotion_DataService promotion_dataService;

    protected DataRemoteObject() throws RemoteException{
        // TODO 初始化

    }
    //TODO 修改data层的方法，围绕增删改查提供
    //TODO 方法后添加异常抛出
    public ResultMessage modify(ManagerPO po) {
        return null;
    }

    public ResultMessage add(ClerkPO clerkPO) {
        return null;
    }

    public ResultMessage add(MemberPO po) {
        return null;
    }

    public ResultMessage add(CustomerPO customerPO) {
        return null;
    }

    public ResultMessage add(MarketerPO marketerPO) {
        return null;
    }

    public ResultMessage modify(ClerkPO clerkPO) {
        return null;
    }

    public ResultMessage add(OrderPO po) {
        return null;
    }

    public ResultMessage upgrade(int grade) {
        return null;
    }

    public ResultMessage add(PromotionPO po) {
        return null;
    }

    public ResultMessage modify(CustomerPO customerPO) {
        return null;
    }

    public ResultMessage modify(MarketerPO marketerPO) {
        return null;
    }

    public List<OrderPO> findByCustomerID(String customerID) {
        return null;
    }

    public List<ClerkPO> findByClerkName(String name) {
        return null;
    }

    public ResultMessage add(HotelPO po) {
        return null;
    }

    public ResultMessage degrade(int grade) {
        return null;
    }

    public String getTargetAera(PromotionPO po) {
        return null;
    }

    public List<CustomerPO> find(String id) {
        return null;
    }

    public List<ClerkPO> findByClerkID(String id) {
        return null;
    }

    public List<MarketerPO> findByMarketerName(String name) {
        return null;
    }

    public int getGrade(MemberPO po) {
        return 0;
    }

    public List<OrderPO> findByCustomerIDAndOrderStatus(String customerID, OrderStatus orderStatus) {
        return null;
    }

    public ResultMessage modify(HotelPO po) {
        return null;
    }

    public List<String> getTargetHotel(PromotionPO po) {
        return null;
    }

    public String getID(CustomerPO customerPO) {
        return null;
    }

    public ResultMessage delete(HotelPO po) {
        return null;
    }

    public ResultMessage delete(MemberPO po) {
        return null;
    }

    public ResultMessage delete(ClerkPO clerkPO) {
        return null;
    }

    public List<MarketerPO> findByMarketerID(String id) {
        return null;
    }

    public int getCurrentCredit(String customer_id) {
        return 0;
    }

    public MemberType getTargetUser(PromotionPO po) {
        return null;
    }

    public MemberType getType(MemberPO po) {
        return null;
    }

    public List<HotelPO> find(String address, String area, Date expected_date_of_arrival, Date expected_date_of_departure, int star, double score) {
        return null;
    }

    public List<OrderPO> findByHotelID(String hotelID) {
        return null;
    }

    public List<MarketerPO> findByPosition(WorkerPosition position) {
        return null;
    }

    public Date getStartTime(PromotionPO po) {
        return null;
    }

    public MemberPO getMember(String id) {
        return null;
    }

    public List<HotelPO> getReservedHotel(CustomerPO customerPO) {
        return null;
    }

    public List<OrderPO> findByOrderStatus(OrderStatus orderStatus) {
        return null;
    }

    public Date getEndTime(PromotionPO po) {
        return null;
    }

    public ResultMessage addMemberLevel(MemberLevelPO po) {
        return null;
    }

    public ResultMessage delete(MarketerPO marketerPO) {
        return null;
    }

    public double getDiscount(PromotionPO po) {
        return 0;
    }

    public double getPrice(OrderPO po) {
        return 0;
    }

    public HotelPO getHotel(String id) {
        return null;
    }

    public ResultMessage modifyMemberLevel(MemberLevelPO po) {
        return null;
    }

    public int getMinRoom(PromotionPO po) {
        return 0;
    }

    public ResultMessage changeOrderStatus(OrderPO po, OrderStatus condition) {
        return null;
    }

    public ResultMessage addRoomType(RoomPO po) {
        return null;
    }

    public int getNumberOfMemberLevel(MemberLevelPO po) {
        return 0;
    }

    public ResultMessage modifyRoomType(RoomPO po) {
        return null;
    }

    public OrderStatus getOrderStatus(OrderPO po) {
        return null;
    }

    public ResultMessage deleteRoomType(RoomPO po) {
        return null;
    }

    public ResultMessage setActualCheckinTime(OrderPO po, Date actualCheckinTime) {
        return null;
    }

    public double getRoomPrice(RoomPO po) {
        return 0;
    }

    public String getRoomType(RoomPO po) {
        return null;
    }

    public ResultMessage setActualCheckoutTime(OrderPO po, Date actualCheckoutTime) {
        return null;
    }

    public DailyRoomInfoPO getDailyRoomInfo(Date date) {
        return null;
    }

    public Date getLatestExecutedTime(OrderPO po) {
        return null;
    }

    public ResultMessage setDailyRoomInfo(List<DailyRoomInfoPO> list) {
        return null;
    }

    public ResultMessage addComment(CommentPO po) {
        return null;
    }

    public List<CommentPO> getCommentPO(String hotelID) {
        return null;
    }

    public ResultMessage addToListOfHotelReservedByCustomer(HotelPO hotelPO, CustomerPO customerPO) {
        return null;
    }
}
