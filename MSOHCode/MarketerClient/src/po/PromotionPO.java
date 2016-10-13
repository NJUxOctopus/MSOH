package po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import util.MemberType;

public class PromotionPO implements Serializable{

	private String framerName;
	
	private Date frameDate;
	
	private String promotionName;
	
	private MemberType targetUser;
	
	private String targetArea;
	
	private List<String> targetHotel;
	
	private Date startTime;
	
	private Date endTime;
	
	private double discount;
	
	private int minRoom;
	
	public PromotionPO(){}

	public PromotionPO(String framerName, Date frameDate, String promotionName, MemberType targetUser,
			String targetArea, List<String> targetHotel, Date startTime, Date endTime, double discount, int minRoom) {
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
	
	
}
