package po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author zqh
 *
 */
public class MemberLevelPO implements Serializable{
	private static final long serialVersionUID=1L;
	// 数据库中会员等级制度的序号，自动生成
	private int memberlevelID;
	// 制定人员姓名
	private String framerName;
	// 制定日期
	private Timestamp frameDate;
	// 等级个数
	private int num;
	// 等级界限值，分号隔开
	private String creditBoundaries;
	// 折扣，分号隔开
	private String discountList;
	
	public MemberLevelPO(){
	}
	
	public MemberLevelPO(String framerName, Timestamp frameDate, int num, String creditBoundaries,String discountList){
		this.framerName=framerName;
		this.frameDate=frameDate;
		this.num=num;
		this.creditBoundaries=creditBoundaries;
		this.discountList=discountList;
	}

	public String getFramerName() {
		return framerName;
	}

	public void setFramerName(String framerName) {
		this.framerName = framerName;
	}

	public Timestamp getFrameDate() {
		return frameDate;
	}

	public void setFrameDate(Timestamp frameDate) {
		this.frameDate = frameDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCreditBoundaries() {
		return creditBoundaries;
	}

	public void setCreditBoundaries(String creditBoundaries) {
		this.creditBoundaries = creditBoundaries;
	}

	public String getDiscountList() {
		return discountList;
	}

	public int getMemberlevelID() {
		return memberlevelID;
	}

	public void setDiscountList(String discountList) {
		this.discountList = discountList;
	}
}
