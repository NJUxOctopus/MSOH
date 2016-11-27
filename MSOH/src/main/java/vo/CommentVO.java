package vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CommentVO implements Serializable {

	public double score;

	public String comment;

	public String customerName;
	
	public String customerID;

	public String hotelName;

	public String hotelID;

	public String orderID;

	public Timestamp commentTime;

	public CommentVO() {
	}

	public CommentVO(double score, String comment, String customerName, String customerID, String hotelName, String hotelID,
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
}
