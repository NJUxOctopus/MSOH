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

	public MarketerVO(String name, String phone, String password, String ID, Image pic) {
		super(name,  phone, password, ID,pic);
	}
}
