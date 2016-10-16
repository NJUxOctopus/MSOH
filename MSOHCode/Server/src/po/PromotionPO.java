package po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import util.MemberType;
/**
 * 
 * @author 週沁涵
 *
 */
public class PromotionPO implements Serializable{
	// 制定者姓名
	private String framerName;
	// 制定日期
	private Date frameDate;
	// 策略名称
	private String promotionName;
	// 策略适用客户
	private MemberType targetUser;
	// 策略适用商圈
	private String targetArea;
	// 策略适用酒店
	private List<String> targetHotel;
	// 策略生效时间
	private Date startTime;
	// 策略过期时间
	private Date endTime;
	// 策略折扣
	private double discount;
	// 策略生效至少需要的房间数
	private int minRoom;
	// 策略ID
	private String promotionID;
	
	public PromotionPO(){}

	public PromotionPO(String framerName, Date frameDate, String promotionName, MemberType targetUser,
			String targetArea, List<String> targetHotel, Date startTime, Date endTime, double discount, int minRoom, String promotionID) {
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

	public Date getFrameDate() {
		return frameDate;
	}

	public void setFrameDate(Date frameDate) {
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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
	
}
