package vo;

import java.awt.Image;
import java.io.Serializable;

public class ClerkVO extends WorkerVO implements Serializable{

	public String hotelName;

	public String hotelID;

	public String position;
		
	public ClerkVO(){}
	
	public ClerkVO(String name,String phone,String password,String ID,Image pic,String hotelName,String hotelID,String position){
		super(name,phone,password,ID,pic);
		this.hotelName=hotelName;
		this.hotelID=hotelID;
		this.position=position;
	}
}
