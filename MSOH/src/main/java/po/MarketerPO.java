package po;

import util.WorkerPosition;

import java.awt.*;
import java.io.Serializable;

/**
 * 
 * @author zqh
 *
 */
public class MarketerPO implements Serializable {
	// 网站营销人员姓名
	private String name;
	// 网站营销人员ID
	private String ID;
	// 网站营销人员联系方式
	private String phone;
	// 网站营销人员密码
	private String password;
	// 网站营销人员头像
	private Image pic;
	// ְ网站营销人员职位，默认为Marketer
	private WorkerPosition position;

	public MarketerPO() {
	}

	public MarketerPO(String name, String ID, String phone, String password, Image pic, WorkerPosition position) {
		this.name = name;
		this.ID = ID;
		this.phone = phone;
		this.password = password;
		this.pic = pic;
		this.position=position;
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

	public WorkerPosition getPosition(){
		return position;
	}
	
	public void setPosition(WorkerPosition position){
		this.position=position;
	}
}
