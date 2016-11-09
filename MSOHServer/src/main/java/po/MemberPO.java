package po;

import java.io.Serializable;
import java.util.Date;

import util.MemberType;
/**
 * 
 * @author 週沁涵
 *
 */
public class MemberPO implements Serializable {
	// 用户ID
	private String ID;
	// 会员类型
	private MemberType memberType;
	// 会员等级
	private int level;
	// 普通会员生日
	private Date birthday;
	// 企业会员企业名称
	private String companyName;

	public MemberPO() {
	}

	public MemberPO(String ID, MemberType memberType, int level, Date birthday, String companyName) {
		this.ID = ID;
		this.memberType = memberType;
		this.level = level;
		this.birthday = birthday;
		this.companyName = companyName;
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String ID){
		this.ID=ID;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
