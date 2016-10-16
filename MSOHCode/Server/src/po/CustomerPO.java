package po;

import java.awt.Image;
import java.io.Serializable;
import util.MemberType;
/**
 * 
 * @author 週沁涵
 *
 */
public class CustomerPO implements Serializable {
	// 用户名
	private String userName;
	// 用户密码
	private String password;
	// 用户手机号
	private String phone;
	// 用户邮箱账号
	private String email;
	// 用户信用值
	private int credit;
	// 用户头像
	private Image picture;
	// 用户身份证号
	private String ID;
	// 会员类型
	private MemberType memberType;

	public CustomerPO() {
	}

	public CustomerPO(String userName, String password, String phone, String email, int credit, Image picture,
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String ID){
		this.ID=ID;
	}
	
	public MemberType getMemberType(){
		return memberType;
	}
	
	public void setMemberType(MemberType memberType){
		this.memberType=memberType;
	}
}
