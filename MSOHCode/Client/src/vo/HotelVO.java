package vo;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import vo.CommentVO;
import vo.DailyRoomInfoVO;

public class HotelVO implements Serializable{

	public String hotelName;

	public String hotelAddress;

	public String area;

	public String intro;

	public List<String> infra;

	public int star;

	public double score;

	public String license;

	public List<Image> pics;

	public String clerkName;

	public String clerkPhone;

	public String hotelID;

	public List<DailyRoomInfoVO> dailyRoomInfo;

	public List<CommentVO> comment;

	public HotelVO() {
	}

	public HotelVO(String hotelName, String hotelAddress, String area, String intro, List<String> infra, int star,
			double score, String license, String clerkName, String clerkPhone, String hotelID, List<DailyRoomInfoVO> dailyRoomInfo,
			List<CommentVO> comment) {
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.area = area;
		this.intro = intro;
		this.infra = infra;
		this.star = star;
		this.score = score;
		this.license = license;
		this.clerkName = clerkName;
		this.clerkPhone = clerkPhone;
		this.hotelID = hotelID;
		this.dailyRoomInfo = dailyRoomInfo;
		this.comment = comment;
	}
	
}
