package vo;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;

/**
 * 
 * @author zqh
 *
 */
public class ManagerVO extends UserVO implements Serializable {

	public ManagerVO() {
	}

	public ManagerVO(String name, String ID, String phone, String password, Image pic) {
		super(name, ID, phone, password, pic);
	}
}
