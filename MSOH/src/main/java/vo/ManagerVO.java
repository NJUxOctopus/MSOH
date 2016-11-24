package vo;

import java.io.Serializable;

/**
 * 
 * @author zqh
 *
 */
public class ManagerVO extends UserVO implements Serializable {

	public ManagerVO() {
	}

	public ManagerVO(String name, String phone, String password, String ID, String picUrl) {
		super(name,  phone, password,ID, picUrl);
	}
}
