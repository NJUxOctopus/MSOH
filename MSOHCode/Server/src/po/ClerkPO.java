package po;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;
/**
 * 
 * @author 週沁涵
 *
 */
public class ClerkPO implements Serializable {
	// 姓名或名称
	private String name;
	// 手机号
	private String phone;
	// 密码
	private String password;
	// 身份证号
	private String ID;
	// 所在酒店名称
	private String hotelName;
	// 所在酒店ID
	private String hotelID;
	// 职位
	private WorkerPosition position;
	// 个人头像
	private Image pic;

	public ClerkPO() {
	}

	public ClerkPO(String name, String phone, String password, String ID, String hotelName, String hotelID,
			WorkerPosition position, Image pic) {
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.ID = ID;
		this.hotelName = hotelName;
		this.hotelID = hotelID;
		this.position = position;
		this.pic=pic;
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

	public Image getPic(){
		return pic;
	}
	
	public void setPic(Image pic){
		this.pic=pic;
	}
}
