package vo;

import java.awt.Image;
import java.io.Serializable;

import util.MemberType;

/**
 * 
 * @author zqh
 *
 */
public class CustomerVO extends UserVO implements Serializable  {

	public String email;

	public int credit;

	public MemberType memberType;

	public CustomerVO() {
	}

	public CustomerVO(String userName, String password, String phone, String email, int credit, Image picture,
			String ID,MemberType memberType) {
		super(userName,phone,password,ID,picture);
		this.email = email;
		this.credit = credit;
		this.memberType=memberType;
	}

}
