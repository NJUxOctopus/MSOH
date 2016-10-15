package vo;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;

/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:08:58
 *
 */
public class ManagerVO extends WorkerVO implements Serializable {

	public ManagerVO() {
	}

	public ManagerVO(String name, String ID, String phone, String password, Image pic,WorkerPosition position) {
		super(name, ID, phone, password, pic,position);
	}
}
