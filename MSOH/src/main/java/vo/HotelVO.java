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

	public int star;

	public double score;

	public String license;

	public String[] picUrls;

	public String clerkName;

	public String clerkPhone;

	public String hotelID;

	public List<DailyRoomInfoVO> dailyRoomInfo;

	public List<CommentVO> comment;

	public HotelVO() {
	}

	public HotelVO(String hotelName, String hotelAddress, String area, String intro, String[] infra, int star,
			double score, String license,String[] picUrls, String clerkName, String clerkPhone, String hotelID, List<DailyRoomInfoVO> dailyRoomInfo,
			List<CommentVO> comment) {
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.area = area;
		this.intro = intro;
		this.infra = infra;
		this.star = star;
		this.score = score;
		this.license = license;
		this.picUrls=picUrls;
		this.clerkName = clerkName;
		this.clerkPhone = clerkPhone;
		this.hotelID = hotelID;
		this.dailyRoomInfo = dailyRoomInfo;
		this.comment = comment;
	}
	
}
