package po;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 
 * @author zqh
 *
 */
public class MemberLevelPO implements Serializable{
	// 制定人员姓名
	private String framerName;
	// 制定日期
	private Timestamp frameDate;
	// 等级个数
	private int num;
	// 等级界限值
	private int[] creditBoundaries;
	
	public MemberLevelPO(){
	}
	
	public MemberLevelPO(String framerName, Timestamp frameDate, int num, int[] creditBoundaries){
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

	public int[] getCreditBoundaries() {
		return creditBoundaries;
	}

	public void setCreditBoundaries(int[] creditBoundaries) {
		this.creditBoundaries = creditBoundaries;
	}
	
	
}
