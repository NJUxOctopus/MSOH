package po;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author zqh
 *
 */
public class HotelPO implements Serializable {
	// 酒店名称
	private String hotelName;
	// 酒店ID
	private String hotelID;
	// 酒店地址
	private String hotelAddress;
	// 酒店所处商圈
	private String area;
	// 酒店简介
	private String intro;
	// 酒店设施
	private List<String> infra;
	// 酒店星级
	private int star;
	// 酒店评分
	private double score;
	// 酒店经营许可证号
	private String license;
	// 酒店照片
	private List<String> picUrls;
	// 系统中该酒店负责人姓名
	private String clerkName;
	// 系统中该酒店负责人联系方式
	private String clerkPhone;
	// 酒店每日客房信息<DailyRoomInfoPO>
	private List<DailyRoomInfoPO> dailyRoomInfo;
	// 酒店评价
	private List<CommentPO> comment;

	public HotelPO() {
	}

	public HotelPO(String hotelName, String hotelAddress, String area, String intro, List<String> infra, int star,
				   double score, String license, List<String> picUrls,String clerkName, String clerkPhone, String hotelID, List<DailyRoomInfoPO> dailyRoomInfo,
				   List<CommentPO> comment) {
		this.hotelName = hotelName;
		this.hotelID=hotelID;
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
		this.dailyRoomInfo = dailyRoomInfo;
		this.comment = comment;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<String> getInfra() {
		return infra;
	}

	public void setInfra(List<String> infra) {
		this.infra = infra;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public List<String> getPicUrls(){
		return picUrls;
	}

	public void setPicUrls(List<String> picUrls){
		this.picUrls=picUrls;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public String getClerkPhone() {
		return clerkPhone;
	}

	public void setClerkPhone(String clerkPhone) {
		this.clerkPhone = clerkPhone;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public List<DailyRoomInfoPO> getDailyRoomInfo() {
		return dailyRoomInfo;
	}

	public void setRoom(List<DailyRoomInfoPO> dailyRoomInfo) {
		this.dailyRoomInfo = dailyRoomInfo;
	}

	public List<CommentPO> getComment() {
		return comment;
	}

	public void setComment(List<CommentPO> comment) {
		this.comment = comment;
	}

}
