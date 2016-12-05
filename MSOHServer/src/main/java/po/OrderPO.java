package po;

import org.hibernate.annotations.Type;
import util.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zqh
 */
@Entity
@Table(name = "myorder", schema = "msoh_database")
public class OrderPO implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    // 数据库自动生成的订单号，仅供数据库使用
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myorder_id_seq")
    @SequenceGenerator(name = "myorder_id_seq", sequenceName = "myorder_id_seq", allocationSize = 1)
    @Column(name = "_autoID")
    private int _autoID;
    // 客户姓名
    // 订单号
    @Column(name = "orderID")
    private String orderID;
    @Column(name = "customerName")
    private String customerName;
    // 客户联系方式
    @Column(name = "customerPhone")
    private String phone;
    // 客户ID
    @Column(name = "customerID")
    private String customerID;
    // 酒店ID
    @Column(name = "hotelID")
    private String hotelID;
    // 酒店名称
    @Column(name = "hotelName")
    private String hotelName;
    // 预计check in时间
    @Column(name = "estimatedCheckInTime")
    private Timestamp estimatedCheckInTime;
    // 实际check in时间
    @Column(name = "actualCheckInTime")
    private Timestamp actualCheckInTime;
    // 预计check out时间
    @Column(name = "estimatedCheckOutTime")
    private Timestamp estimatedCheckOutTime;
    // 实际check out时间
    @Column(name = "actualCheckOutTime")
    private Timestamp actualCheckOutTime;
    // 最晚执行时间
    @Column(name = "latestExecutedTime")
    private Timestamp latestExecutedTime;
    // 所订客房（分号隔开）
    @Column(name = "rooms")
    private String rooms;
    // 入住人数
    @Column(name = "numOfCustomers")
    private int numOfCustomers;
    // 是否有儿童
    @Column(name = "haveChildren")
    private boolean haveChildren;
    // 备注（特殊要求，如：“需要三双拖鞋”）
    @Column(name = "remarks")
    private String remarks;
    // 所用策略ID
    @Column(name = "promotionID")
    private int promotionID;
    // 所用策略名称
    @Column(name = "promotionName")
    private String promotionName;
    // 最初价格
    @Column(name = "initialPrice")
    private double initialPrice;
    // 折后价格
    @Column(name = "finalPrice")
    private double finalPrice;
    // 订单状态
    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderPO() {

    }

    public OrderPO(String customerName, String phone, String customerID, String hotelID, String hotelName,
                   Timestamp estimatedCheckinTime, Timestamp actualCheckinTime, Timestamp estimatedCheckoutTime, Timestamp actualCheckoutTime,
                   Timestamp latestExecutedTime, String rooms, int numOfCustomers, boolean haveChildren, String remarks,
                   int promotionID, String promotionName, double initialPrice, double finalPrice, OrderStatus orderType) {
        this.customerName = customerName;
        this.phone = phone;
        this.customerID = customerID;
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.estimatedCheckInTime = estimatedCheckinTime;
        this.actualCheckInTime = actualCheckinTime;
        this.estimatedCheckOutTime = estimatedCheckoutTime;
        this.actualCheckOutTime = actualCheckoutTime;
        this.latestExecutedTime = latestExecutedTime;
        this.rooms = rooms;
        this.numOfCustomers = numOfCustomers;
        this.haveChildren = haveChildren;
        this.remarks = remarks;
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.orderStatus = orderType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Timestamp getEstimatedCheckInTime() {
        return estimatedCheckInTime;
    }

    public void setEstimatedCheckInTime(Timestamp estimatedCheckinTime) {
        this.estimatedCheckInTime = estimatedCheckinTime;
    }

    public Timestamp getActualCheckInTime() {
        return actualCheckInTime;
    }

    public void setActualCheckInTime(Timestamp actualCheckinTime) {
        this.actualCheckInTime = actualCheckinTime;
    }

    public Timestamp getEstimatedCheckOutTime() {
        return estimatedCheckOutTime;
    }

    public void setEstimatedCheckOutTime(Timestamp estimatedCheckoutTime) {
        this.estimatedCheckOutTime = estimatedCheckoutTime;
    }

    public Timestamp getActualCheckOutTime() {
        return actualCheckOutTime;
    }

    public void setActualCheckOutTime(Timestamp actualCheckoutTime) {
        this.actualCheckOutTime = actualCheckoutTime;
    }

    public Timestamp getLatestExecutedTime() {
        return latestExecutedTime;
    }

    public void setLatestExecutedTime(Timestamp latestExecutedTime) {
        this.latestExecutedTime = latestExecutedTime;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public int getNumOfCustomers() {
        return numOfCustomers;
    }

    public void setNumOfCustomers(int numOfCustomers) {
        this.numOfCustomers = numOfCustomers;
    }

    public boolean isHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(boolean haveChildren) {
        this.haveChildren = haveChildren;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderType) {
        this.orderStatus = orderType;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    @Override
    public Object clone() {
        OrderPO orderPO = null;
        try {
            orderPO = (OrderPO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return orderPO;
    }
}