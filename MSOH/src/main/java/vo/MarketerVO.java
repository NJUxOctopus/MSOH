package vo;

import java.io.Serializable;

/**
 * 
 * @author zqh
 *
 */
public class MarketerVO extends UserVO implements Serializable {

	public MarketerVO() {
	}

	public MarketerVO(String name, String phone, String password, String ID, String picUrl) {
		super(name,  phone, password, ID,picUrl);
	}
}
