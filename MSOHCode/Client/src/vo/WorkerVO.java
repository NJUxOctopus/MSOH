package vo;

import java.awt.Image;
import java.io.Serializable;

import util.WorkerPosition;

/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:12:31
 *
 */
public abstract class WorkerVO implements Serializable{

	public String name;

	public String phone;

	public String password;

	public String ID;

	public Image pic;
	
	public WorkerPosition position;
	
	public WorkerVO(){}
	
	public WorkerVO(String name,String phone,String password,String ID,Image pic, WorkerPosition position){
		this.name=name;
		this.phone=phone;
		this.password=password;
		this.ID=ID;
		this.pic=pic;
		this.position=position;
	}
}
