package vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author �L�ߺ� 2016-10-15 15:10:19
 *
 */
public class CreditRecordVO implements Serializable{

	public int variation;

	public Timestamp changeTime;

	public String customerName;
	
	public String customerID;

	public int afterChangeCredit;

	public String orderID;

	public String marketerName;

	public CreditRecordVO() {
	}

	public CreditRecordVO(int variation, Timestamp changeTime, String customerName, String customerID, int afterChangeCredit, String orderID,
			String marketerName) {
		this.variation = variation;
		this.changeTime = changeTime;
		this.customerName = customerName;
		this.customerID=customerID;
		this.afterChangeCredit = afterChangeCredit;
		this.orderID = orderID;
		this.marketerName = marketerName;
	}
}
