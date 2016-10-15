package vo;

import java.io.Serializable;
import java.util.Date;

public class CreditRecordVO implements Serializable{

	public int variation;

	public Date changeTime;

	public String customerName;

	public int afterChangeCredit;

	public String orderID;

	public String marketerName;

	public CreditRecordVO() {
	}

	public CreditRecordVO(int variation, Date changeTime, String customerName, int afterChangeCredit, String orderID,
			String marketerName) {
		this.variation = variation;
		this.changeTime = changeTime;
		this.customerName = customerName;
		this.afterChangeCredit = afterChangeCredit;
		this.orderID = orderID;
		this.marketerName = marketerName;
	}
}
