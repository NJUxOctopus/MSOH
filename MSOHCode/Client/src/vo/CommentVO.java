package vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author ßLÇßº­ 2016-10-15 15:10:36
 *
 */
public class CommentVO implements Serializable {

	public double score;

	public String comment;

	public String customerName;
	
	public String customerID;

	public String hotelName;

	public String hotelID;

	public String orderID;

	public Date commentTime;

	public CommentVO() {
	}

	public CommentVO(double score, String comment, String customerName, String customerID, String hotelName, String hotelID,
			String orderID, Date commentTime) {
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
