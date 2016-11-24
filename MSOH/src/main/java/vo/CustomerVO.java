package vo;

import util.MemberType;

import java.io.Serializable;

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

	public CustomerVO(String userName, String password, String phone, String email, int credit, String picUrl,
			String ID,MemberType memberType) {
		super(userName,phone,password,ID,picUrl);
		this.email = email;
		this.credit = credit;
		this.memberType=memberType;
	}

}
