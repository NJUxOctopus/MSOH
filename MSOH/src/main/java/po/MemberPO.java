package po;

import util.MemberType;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author zqh
 *
 */
public class MemberPO implements Serializable {
	// 会员ID
	private String ID;
	// 会员类型
	private MemberType memberType;
	// 会员等级
	private int level;
	// 会员生日
	private Timestamp birthday;
	// 会员所在企业名称
	private String companyName;

	public MemberPO() {
	}

	public MemberPO(String ID, MemberType memberType, int level, Timestamp birthday, String companyName) {
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

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
