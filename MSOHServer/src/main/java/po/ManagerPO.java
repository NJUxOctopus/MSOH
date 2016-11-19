package po;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;
/**
 * 
 * @author zqh
 *
 */
public class ManagerPO implements Serializable {
	// 网站管理人员姓名
	private String name;
	// 网站管理人员ID
	private String ID;
	// 网站管理人员联系方式
	private String phone;
	// 网站管理人员密码
	private String password;
	// 网站管理人员头像
	private Image pic;
	// ְ网站管理人员职位，默认为Manager
	private WorkerPosition position;

	public ManagerPO() {
	}

	public ManagerPO(String name, String ID, String phone, String password, Image pic,WorkerPosition position) {
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
