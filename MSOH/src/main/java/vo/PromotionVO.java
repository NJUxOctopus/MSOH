package vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import util.MemberType;
import util.PromotionType;
/**
 * @author pxr
 */
public class PromotionVO implements Serializable {

    public String framerName;

    public Timestamp frameDate;

    public String promotionName;

    public MemberType targetUser;

    public String targetArea;

    public String[] targetHotel;

    public Timestamp startTime;

    public Timestamp endTime;

    public double discount;

    public int minRoom;

    public String promotionID;

    public PromotionType promotionType;

    public String companyName;

    public int memberLevel;

    public PromotionVO() {
    }

    public PromotionVO(double discount, String promotionName) {
        this.discount = discount;
        this.promotionName = promotionName;
    }

    public PromotionVO(String framerName, Timestamp frameDate, String promotionName, MemberType targetUser,
                       String targetArea, String[] targetHotel, Timestamp startTime, Timestamp endTime, double discount,
                       int minRoom, String promotionID, PromotionType promotionType,String companyName) {
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
        this.promotionID = promotionID;
        this.promotionType = promotionType;
        this.companyName = companyName;
    }

    /**
     * 制定酒店生日特惠、多订多惠、节日特惠时用到的构造方法
     * @param framerName
     * @param frameDate
     * @param promotionName
     * @param targetUser
     * @param targetHotel
     * @param startTime
     * @param endTime
     * @param discount
     * @param minRoom
     * @param promotionType
     */
    public PromotionVO(String framerName, Timestamp frameDate, String promotionName, MemberType targetUser,
                       String[] targetHotel, Timestamp startTime, Timestamp endTime, double discount,
                       int minRoom, PromotionType promotionType) {
        this.framerName = framerName;
        this.frameDate = frameDate;
        this.promotionName = promotionName;
        this.targetUser = targetUser;
        this.targetHotel = targetHotel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
        this.minRoom = minRoom;
        this.promotionType = promotionType;
    }

    /**
     * 制定酒店合作企业优惠时用到的构造方法
     * @param framerName
     * @param frameDate
     * @param promotionName
     * @param targetUser
     * @param targetHotel
     * @param startTime
     * @param endTime
     * @param discount
     * @param minRoom
     * @param promotionType
     * @param companyName
     */
    public PromotionVO(String framerName, Timestamp frameDate, String promotionName, MemberType targetUser,
                       String[] targetHotel, Timestamp startTime, Timestamp endTime, double discount,
                       int minRoom, PromotionType promotionType,String companyName) {
        this.framerName = framerName;
        this.frameDate = frameDate;
        this.promotionName = promotionName;
        this.targetUser = targetUser;
        this.targetHotel = targetHotel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
        this.minRoom = minRoom;
        this.promotionType = promotionType;
        this.companyName = companyName;
    }

}
