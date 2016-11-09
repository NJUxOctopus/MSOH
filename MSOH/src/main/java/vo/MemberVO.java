package vo;

import java.io.Serializable;
import java.util.Date;

import util.MemberType;

/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:11:44
 *
 */
public class MemberVO implements Serializable{

	public String ID;
	
	public MemberType memberType;
	
	public int level;

	public Date birthday;

	public String companyName;
	
	public MemberVO() {
	}

	public MemberVO(String ID, MemberType memberType, int level, Date birthday, String companyName) {
		this.ID = ID;
		this.memberType = memberType;
		this.level = level;
		this.birthday = birthday;
		this.companyName = companyName;
	}
}
