package po;

import java.io.Serializable;
import java.util.Date;

public class MemberLevelPO implements Serializable{
	//制定者姓名
	private String framerName;
	//制定日期
	private Date frameDate;
	//会员个数
	private int num;
	//会员信用值界限
	private int[] creditBoundaries;
	
	public MemberLevelPO(){
	}
	
	public MemberLevelPO(String framerName, Date frameDate, int num, int[] creditBoundaries){
		this.framerName=framerName;
		this.frameDate=frameDate;
		this.num=num;
		this.creditBoundaries=creditBoundaries;
	}

	public String getFramerName() {
		return framerName;
	}

	public void setFramerName(String framerName) {
		this.framerName = framerName;
	}

	public Date getFrameDate() {
		return frameDate;
	}

	public void setFrameDate(Date frameDate) {
		this.frameDate = frameDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int[] getCreditBoundaries() {
		return creditBoundaries;
	}

	public void setCreditBoundaries(int[] creditBoundaries) {
		this.creditBoundaries = creditBoundaries;
	}
	
	
}
