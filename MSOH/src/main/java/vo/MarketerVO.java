package vo;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;

/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:11:30
 *
 */
public class MarketerVO extends WorkerVO implements Serializable {

	public MarketerVO() {
	}

	public MarketerVO(String name, String ID, String phone, String password, Image pic, WorkerPosition position) {
		super(name, ID, phone, password, pic,position);
	}
}
