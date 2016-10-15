package vo;

import java.awt.Image;
import java.io.Serializable;

import util.MemberType;

/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:09:44
 *
 */
public class CustomerVO implements Serializable{

	public String userName;
	
	public String password;

	public String phone;

	public String email;

	public int credit;

	public Image picture;

	public String ID;

	public MemberType memberType;

	public CustomerVO() {
	}

	public CustomerVO(String userName, String password, String phone, String email, int credit, Image picture,
			String ID,MemberType memberType) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.credit = credit;
		this.picture = picture;
		this.ID = ID;
		this.memberType=memberType;
	}

}
