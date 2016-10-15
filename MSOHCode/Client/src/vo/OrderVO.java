package vo;

import java.util.Date;
import java.util.List;

import util.OrderStatus;

public class OrderVO {

	public String customerName;

	public String phone;

	public String customerID;

	public String hotelName;

	public String orderID;

	public Date estimatedCheckinTime;

	public Date actualCheckinTime;

	public Date estimatedCheckoutTime;

	public Date actualCheckoutTime;

	public Date latestExecutedTime;

	public List<String> rooms;

	public int numOfCustomers;

	public boolean haveChildren;

	public String remarks;

	public String promotionName;

	public double initialPrice;

	public double finalPrice;

	public OrderStatus orderType;

	public OrderVO() {

	}

	// 将actualCheckoutTime和actualCheckinTime放在构造函数中，是因为会出现线下创建订单的情况
	public OrderVO(String customerName, String phone, String customerID, String hotelName, String orderID,
			Date estimatedCheckinTime, Date actualCheckinTime, Date estimatedCheckoutTime, Date actualCheckoutTime,
			Date latestExecutedTime, List<String> rooms, int numOfCustomers, boolean haveChildren, String remarks,
			String promotionName, double initialPrice, double finalPrice, OrderStatus orderType) {
		this.customerName = customerName;
		this.phone = phone;
		this.customerID = customerID;
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
