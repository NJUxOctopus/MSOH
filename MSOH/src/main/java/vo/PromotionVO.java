package vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import util.MemberType;

public class PromotionVO implements Serializable{

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
	
	public PromotionVO(){}

	public PromotionVO(String framerName, Timestamp frameDate, String promotionName, MemberType targetUser,
			String targetArea, String[] targetHotel, Timestamp startTime, Timestamp endTime, double discount, int minRoom, String promotionID) {
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
}
