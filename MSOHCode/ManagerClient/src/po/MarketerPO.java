package po;

import java.awt.Image;
import java.io.Serializable;

public class MarketerPO implements Serializable {
	// 姓名
	private String name;
	// 身份证号
	private String ID;
	// 手机号
	private String phone;
	// 密码
	private String password;
	// 个人头像
	private Image pic;

	public MarketerPO() {
	}

	public MarketerPO(String name, String ID, String phone, String password, Image pic) {
		this.name = name;
		this.ID = ID;
		this.phone = phone;
		this.password = password;
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
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

	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

}
