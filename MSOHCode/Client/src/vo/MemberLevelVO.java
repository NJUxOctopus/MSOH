package vo;

import java.util.Date;

public class MemberLevelVO {

	public String framerName;

	public Date frameDate;

	public int num;

	public int[] creditBoundaries;
	
	public MemberLevelVO(){
	}
	
	public MemberLevelVO(String framerName, Date frameDate, int num, int[] creditBoundaries){
		this.framerName=framerName;
		this.frameDate=frameDate;
		this.num=num;
		this.creditBoundaries=creditBoundaries;
	}
}
