package vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author �L�ߺ� 2016-10-15 15:09:12
 *
 */
public class HotelVO implements Serializable{

	public String hotelName;

	public String hotelAddress;

	public String area;

	public String intro;

	public String[] infra;

	public String[] roomType;

	public int star;

	public double score;

	public String license;

	public String[] picUrls;

	public String clerkID;

	public String hotelID;

	public DailyRoomInfoVO dailyRoomInfo;

	public List<CommentVO> comment;

	public HotelVO() {
	}

	public HotelVO(String hotelName, String hotelAddress, String area, String intro, String[] infra, String[] roomType,int star,
			double score, String license,String[] picUrls, String clerkID, String hotelID, DailyRoomInfoVO dailyRoomInfo,
			List<CommentVO> comment) {
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.area = area;
		this.intro = intro;
		this.infra = infra;
		this.roomType = roomType;
		this.star = star;
		this.score = score;
		this.license = license;
		this.picUrls=picUrls;
		this.clerkID = clerkID;
		this.hotelID = hotelID;
		this.dailyRoomInfo = dailyRoomInfo;
		this.comment = comment;
	}
	
}
