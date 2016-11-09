package vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import util.MemberType;
/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:12:07
 *
 */
public class PromotionVO implements Serializable{

	public String framerName;

	public Date frameDate;

	public String promotionName;

	public MemberType targetUser;

	public String targetArea;

	public List<String> targetHotel;

	public Date startTime;

	public Date endTime;

	public double discount;

	public int minRoom;
	
	public String promotionID;
	
	public PromotionVO(){}

	public PromotionVO(String framerName, Date frameDate, String promotionName, MemberType targetUser,
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
}
