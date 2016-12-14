package vo;

import java.io.Serializable;

/**
 * 
 * @author zqh
 *
 */
public class ManagerVO extends UserVO implements Serializable {
	public String name;

	public String phone;

	public String password;

	public String ID;

	public String picUrl;

	public ManagerVO() {
	}

	public ManagerVO(String name, String phone, String password, String ID, String picUrl) {
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.ID = ID;
		this.picUrl = picUrl;
	}
}
