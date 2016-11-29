package po;

import util.WorkerPosition;

import java.io.Serializable;

/**
 * 
 * @author zqh
 *
 */
public class ClerkPO implements Serializable {
	private static final long serialVersionUID = 1L;

	// 酒店工作人员姓名
	private String name;
	// 酒店工作人员联系方式
	private String phone;
	// 酒店工作人员密码
	private String password;
	// 酒店工作人员ID
	private String ID;
	// 酒店工作人员所在酒店名称
	private String hotelName;
	// 酒店工作人员所在酒店ID
	private String hotelID;
	// 工作人员类型：酒店工作人员默认为Clerk
	private WorkerPosition position;
	// 酒店工作人员头像地址
	private String picUrl;

	public ClerkPO() {
	}

	public ClerkPO(String name, String phone, String password, String ID, String hotelName, String hotelID,
                   WorkerPosition position, String picUrl) {
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.ID = ID;
		this.hotelName = hotelName;
		this.hotelID = hotelID;
		this.position = position;
		this.picUrl=picUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID){
		this.ID=ID;
	}
	
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public WorkerPosition getPosition() {
		return position;
	}

	public void setPosition(WorkerPosition position) {
		this.position = position;
	}

	public String getPicUrl(){
		return picUrl;
	}
	
	public void setPicUrl(String picUrl){
		this.picUrl=picUrl;
	}
}
