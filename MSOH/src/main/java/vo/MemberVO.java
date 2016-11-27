package vo;

import util.MemberType;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author �L�ߺ� 2016-10-15 15:11:44
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
}
