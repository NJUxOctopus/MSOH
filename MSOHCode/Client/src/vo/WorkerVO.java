package vo;

import java.awt.Image;
import java.io.Serializable;

public abstract class WorkerVO implements Serializable{

	public String name;

	public String phone;

	public String password;

	public String ID;

	public Image pic;
	
	public WorkerVO(){}
	
	public WorkerVO(String name,String phone,String password,String ID,Image pic){
		this.name=name;
		this.phone=phone;
		this.password=password;
		this.ID=ID;
		this.pic=pic;
	}
}
