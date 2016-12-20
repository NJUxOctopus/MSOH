package vo;

import util.OrderStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
/**
 * @author pxr
 */
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

	public OrderVO(String customerName, String phone, String customerID, String hotelID, String hotelName,
				   Timestamp estimatedCheckinTime, Timestamp actualCheckinTime, Timestamp estimatedCheckoutTime,
				   String[] rooms, int numOfCustomers, boolean haveChildren,
				   double initialPrice, double finalPrice, OrderStatus orderType) {
		this.customerName = customerName;
		this.phone = phone;
		this.customerID = customerID;
		this.hotelID=hotelID;
		this.hotelName = hotelName;
		this.estimatedCheckinTime = estimatedCheckinTime;
		this.actualCheckinTime = actualCheckinTime;
		this.estimatedCheckoutTime = estimatedCheckoutTime;
		this.rooms = rooms;
		this.numOfCustomers = numOfCustomers;
		this.haveChildren = haveChildren;
		this.initialPrice = initialPrice;
		this.finalPrice = finalPrice;
		this.orderType = orderType;
	}

	/**
	 * 生成订单的构造方法
	 * @param customerName
	 * @param phone
	 * @param customerID
	 * @param hotelID
	 * @param hotelName
	 * @param estimatedCheckinTime
	 * @param estimatedCheckoutTime
	 * @param rooms
	 * @param numOfCustomers
	 * @param haveChildren
	 * @param initialPrice
	 * @param finalPrice
	 * @param promotionName
	 */
	public OrderVO(String customerName, String phone, String customerID, String hotelID, String hotelName,
				   Timestamp estimatedCheckinTime, Timestamp estimatedCheckoutTime,
				   String[] rooms, int numOfCustomers, boolean haveChildren,
				   double initialPrice, double finalPrice, String promotionName) {
		this.customerName = customerName;
		this.phone = phone;
		this.customerID = customerID;
		this.hotelID=hotelID;
		this.hotelName = hotelName;
		this.estimatedCheckinTime = estimatedCheckinTime;
		this.estimatedCheckoutTime = estimatedCheckoutTime;
		this.rooms = rooms;
		this.numOfCustomers = numOfCustomers;
		this.haveChildren = haveChildren;
		this.initialPrice = initialPrice;
		this.finalPrice = finalPrice;
		this.promotionName = promotionName;
	}

	/**
	 * 获得订单价格的构造方法
	 * @param hotelID
	 * @param estimatedCheckinTime
	 * @param estimatedCheckoutTime
	 * @param rooms
	 */
	public OrderVO(String customerID, String hotelID, Timestamp estimatedCheckinTime, Timestamp estimatedCheckoutTime, String[] rooms) {
		this.customerID = customerID;
		this.hotelID=hotelID;
		this.estimatedCheckinTime = estimatedCheckinTime;
		this.estimatedCheckoutTime = estimatedCheckoutTime;
		this.rooms = rooms;

	}

}
