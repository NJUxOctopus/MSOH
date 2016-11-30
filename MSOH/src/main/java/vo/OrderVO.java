package vo;

import util.OrderStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class OrderVO implements Serializable{

	public String customerName;

	public String phone;

	public String customerID;
	
	public String hotelID;

	public String hotelName;

	public String orderID;

	public Timestamp estimatedCheckinTime;

	public Timestamp actualCheckinTime;

	public Timestamp estimatedCheckoutTime;

	public Timestamp actualCheckoutTime;

	public Timestamp latestExecutedTime;

	public String[] rooms;

	public int numOfCustomers;

	public boolean haveChildren;

	public String remarks;

	public String promotionName;

	public double initialPrice;

	public double finalPrice;

	public OrderStatus orderType;

	public OrderVO() {

	}

	public OrderVO(String customerName, String phone, String customerID, String hotelID, String hotelName, String orderID,
			Timestamp estimatedCheckinTime, Timestamp actualCheckinTime, Timestamp estimatedCheckoutTime, Timestamp actualCheckoutTime,
			Timestamp latestExecutedTime, String[] rooms, int numOfCustomers, boolean haveChildren, String remarks,
			String promotionName, double initialPrice, double finalPrice, OrderStatus orderType) {
		this.customerName = customerName;
		this.phone = phone;
		this.customerID = customerID;
		this.hotelID=hotelID;
		this.hotelName = hotelName;
		this.orderID = orderID;
		this.estimatedCheckinTime = estimatedCheckinTime;
		this.actualCheckinTime = actualCheckinTime;
		this.estimatedCheckoutTime = estimatedCheckoutTime;
		this.actualCheckoutTime = actualCheckoutTime;
		this.latestExecutedTime = latestExecutedTime;
		this.rooms = rooms;
		this.numOfCustomers = numOfCustomers;
		this.haveChildren = haveChildren;
		this.remarks = remarks;
		this.promotionName = promotionName;
		this.initialPrice = initialPrice;
		this.finalPrice = finalPrice;
		this.orderType = orderType;
	}

}
