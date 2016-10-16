package vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:08:10
 *
 */
public class MemberLevelVO implements Serializable{

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
