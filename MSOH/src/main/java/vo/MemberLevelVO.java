package vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author pxr
 */
public class MemberLevelVO implements Serializable{

	public String framerName;

	public Timestamp frameDate;

	public int num;

	public int[] creditBoundaries;

	public List<String> discountList;
	
	public MemberLevelVO(){
	}
	
	public MemberLevelVO(String framerName, Timestamp frameDate, int num, int[] creditBoundaries,List<String> discountList){
		this.framerName=framerName;
		this.frameDate=frameDate;
		this.num=num;
		this.creditBoundaries=creditBoundaries;
		this.discountList = discountList;
	}
}
