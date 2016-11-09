package vo;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;
/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:10:51
 *
 */
public class ClerkVO extends WorkerVO implements Serializable{

	public String hotelName;

	public String hotelID;
		
	public ClerkVO(){}
	
	public ClerkVO(String name,String phone,String password,String ID,Image pic,String hotelName,String hotelID,WorkerPosition position){
		super(name,phone,password,ID,pic,position);
		this.hotelName=hotelName;
		this.hotelID=hotelID;
	}
}
