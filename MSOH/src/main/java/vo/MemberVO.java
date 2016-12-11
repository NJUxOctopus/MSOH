package vo;

import util.MemberType;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author pxr
 *
 */
public class MemberVO implements Serializable{

	public String ID;
	
	public MemberType memberType;
	
	public int level;

	public Timestamp birthday;

	public String companyName;
	
	public MemberVO() {
	}

	public MemberVO(String ID, MemberType memberType, int level, Timestamp birthday, String companyName) {
		this.ID = ID;
		this.memberType = memberType;
		this.level = level;
		this.birthday = birthday;
		this.companyName = companyName;
	}

	public MemberVO(String ID, MemberType memberType, int level, Timestamp birthday){
		this.ID = ID;
		this.memberType = memberType;
		this.level = level;
		this.birthday = birthday;
	}

	public MemberVO(String ID, MemberType memberType, int level, String companyName) {
		this.ID = ID;
		this.memberType = memberType;
		this.level = level;
		this.companyName = companyName;
	}
}
