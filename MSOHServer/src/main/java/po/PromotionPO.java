package po;

import util.MemberType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zqh
 */
@Entity
@Table(name = "promotion", schema = "msoh_database")
public class PromotionPO implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    // 策略制定者名称
    @Column(name = "framerName")
    private String framerName;
    // 策略制定日期
    @Column(name = "frameDate")
    private Timestamp frameDate;
    // 策略名称
    @Column(name = "promotionName")
    private String promotionName;
    // 目标客户
    @Column(name = "targetUser")
    @Enumerated(EnumType.STRING)
    private MemberType targetUser;
    // 目标商圈
    @Column(name = "targetArea")
    private String targetArea;
    // 目标酒店ID
    @Column(name = "targetHotel")
    private String targetHotel;
    // 策略生效时间
    @Column(name = "startTime")
    private Timestamp startTime;
    // 策略过期时间
    @Column(name = "endTime")
    private Timestamp endTime;
    // 策略折扣
    @Column(name = "discount")
    private double discount;
    // 策略生效所需最少房间数
    @Column(name = "minRoom")
    private int minRoom;
    // 策略ID（编号）
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_id_seq")
    @SequenceGenerator(name = "promotion_id_seq", sequenceName = "promotion_id_seq", allocationSize = 1)
    @Column(name = "promotionID")
    private int promotionID;

    public PromotionPO() {
    }

    public PromotionPO(String framerName, Timestamp frameDate, String promotionName, MemberType targetUser,
                       String targetArea, String targetHotel, Timestamp startTime, Timestamp endTime, double discount, int minRoom) {
        this.framerName = framerName;
        this.frameDate = frameDate;
        this.promotionName = promotionName;
        this.targetUser = targetUser;
        this.targetArea = targetArea;
        this.targetHotel = targetHotel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
        this.minRoom = minRoom;
    }

    public String getFramerName() {
        return framerName;
    }

    public void setFramerName(String framerName) {
        this.framerName = framerName;
    }

    public Timestamp getFrameDate() {
        return frameDate;
    }

    public void setFrameDate(Timestamp frameDate) {
        this.frameDate = frameDate;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public MemberType getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(MemberType targetUser) {
        this.targetUser = targetUser;
    }

    public String getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(String targetArea) {
        this.targetArea = targetArea;
    }

    public String getTargetHotel() {
        return targetHotel;
    }

    public void setTargetHotel(String targetHotel) {
        this.targetHotel = targetHotel;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getMinRoom() {
        return minRoom;
    }

    public void setMinRoom(int minRoom) {
        this.minRoom = minRoom;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    @Override
    public Object clone() {
        PromotionPO promotionPO = null;
        try {
            promotionPO = (PromotionPO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return promotionPO;
    }
}
