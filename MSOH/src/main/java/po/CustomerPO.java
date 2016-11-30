package po;

import util.MemberType;

import java.io.Serializable;

/**
 * 
 * @author zqh
 *
 */
public class CustomerPO implements Serializable {
	private static final long serialVersionUID=1L;
	// 客户姓名
	private String userName;
	// 客户密码
	private String password;
	// 客户联系方式
	private String phone;
	// 客户邮箱
	private String email;
	// 客户信用值
	private int credit;
	// 客户头像
	private String picUrl;
	// 客户ID
	private String ID;
	// 客户会员类型
	private MemberType memberType;

	public CustomerPO() {
	}

	public CustomerPO(String userName, String password, String phone, String email, int credit, String picUrl,
                      String ID, MemberType memberType) {
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.credit = credit;
		this.picUrl = picUrl;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicture(String picUrl) {
		this.picUrl = picUrl;
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
