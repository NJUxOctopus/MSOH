package po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author zqh
 *
 */
public class CommentPO implements Serializable {
	private static final long serialVersionUID=1L;
	// 评价编号,供数据库存储使用，无实际意义，不提供setter和getter方法
	private int commentId;
	// 评分
	private double score;
	// 评价具体内容
	private String comment;
	// 评价客户姓名
	private String customerName;
	// 评价客户ID
	private String customerID;
	// 评价酒店的名字
	private String hotelName;
	// 评价酒店ID
	private String hotelID;
	// 评价订单ID
	private String orderID;
	// 评价时间
	private Timestamp commentTime;

	public CommentPO() {
	}

	public CommentPO(double score, String comment, String customerName, String customerID, String hotelName, String hotelID,
			String orderID, Timestamp commentTime) {
		this.score = score;
		this.comment = comment;
		this.customerName = customerName;
		this.customerID=customerID;
		this.hotelName = hotelName;
		this.hotelID = hotelID;
		this.orderID = orderID;
		this.commentTime = commentTime;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerID(){
		return customerID;
	}

	public void setCustomerID(String customerID){
		this.customerID=customerID;
	}
	
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

}
