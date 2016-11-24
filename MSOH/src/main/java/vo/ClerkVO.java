package vo;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;
/**
 * 
 * @author zqh
 *
 */
public class ClerkVO extends UserVO implements Serializable{

	public String hotelName;

	public String hotelID;

	public String picUrl;
		
	public ClerkVO(){}
	
	public ClerkVO(String name,String phone,String password,String ID,Image pic,String picUrl,String hotelName,String hotelID){
		super(name,phone,password,ID,pic);
		this.hotelName=hotelName;
		this.hotelID=hotelID;
		this.picUrl = picUrl;
	}
}
