package vo;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;

/**
 * 
 * @author zqh
 *
 */
public class MarketerVO extends UserVO implements Serializable {

	public MarketerVO() {
	}

	public MarketerVO(String name, String ID, String phone, String password, Image pic) {
		super(name, ID, phone, password, pic);
	}
}
