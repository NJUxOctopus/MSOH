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

	public String name;

	public String ID;

	public String phone;


	public CustomerVO() {
	}

	public CustomerVO(String userName, String password, String phone, String email, int credit, String picUrl,
			String ID,MemberType memberType) {
		this.name = userName;
		this.phone = phone;
		this.ID = ID;
		this.password = password;
		this.email = email;
		this.credit = credit;
		this.memberType=memberType;
	}

	public CustomerVO(String ID, String name, String phone, String email){
		this.ID = ID;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

}
