package vo;

import java.awt.Image;
import java.io.Serializable;

public class ManagerVO extends WorkerVO implements Serializable {

	public ManagerVO() {
	}

	public ManagerVO(String name, String ID, String phone, String password, Image pic) {
		super(name, ID, phone, password, pic);
	}
}
