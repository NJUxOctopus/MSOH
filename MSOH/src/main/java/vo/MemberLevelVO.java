package vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author �L�ߺ� 2016-10-15 15:08:10
 *
 */
public class MemberLevelVO implements Serializable{

	public String framerName;

	public Timestamp frameDate;

	public int num;

	public int[] creditBoundaries;
	
	public MemberLevelVO(){
	}
	
	public MemberLevelVO(String framerName, Timestamp frameDate, int num, int[] creditBoundaries){
		this.framerName=framerName;
		this.frameDate=frameDate;
		this.num=num;
		this.creditBoundaries=creditBoundaries;
	}
}
