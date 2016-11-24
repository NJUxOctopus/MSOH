package po;

import util.MemberType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
/**
 * 
 * @author zqh
 *
 */
public class PromotionPO implements Serializable{
	// 策略制定者名称
	private String framerName;
	// 策略制定日期
	private Timestamp frameDate;
	// 策略名称
	private String promotionName;
	// 目标客户
	private MemberType targetUser;
	// 目标商圈
	private String targetArea;
	// 目标酒店
	private List<String> targetHotel;
	// 策略生效时间
	private Timestamp startTime;
	// 策略过期时间
	private Timestamp endTime;
	// 策略折扣
	private double discount;
	// 策略生效所需最少房间数
	private int minRoom;
	// 策略ID（编号）
	private String promotionID;
	
	public PromotionPO(){}

	public PromotionPO(String framerName, Timestamp frameDate, String promotionName, MemberType targetUser,
			String targetArea, List<String> targetHotel, Timestamp startTime, Timestamp endTime, double discount, int minRoom, String promotionID) {
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
		this.promotionID=promotionID;
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

	public List<String> getTargetHotel() {
		return targetHotel;
	}

	public void setTargetHotel(List<String> targetHotel) {
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
	
	public String getPromotionID(){
		return promotionID;
	}
	
	public void setPromotionID(String promotionID){
		this.promotionID=promotionID;
	}
}
