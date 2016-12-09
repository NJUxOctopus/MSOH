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

	public String customerID;

	public String phone;


	public CustomerVO() {
	}

	public CustomerVO(String userName, String password, String phone, String email, int credit, String picUrl,
			String ID,MemberType memberType) {
		super(userName,phone,password,ID,picUrl);
		this.email = email;
		this.credit = credit;
		this.memberType=memberType;
	}

	public CustomerVO(String customerID, String name, String phone, String email){
		this.customerID = customerID;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

}
