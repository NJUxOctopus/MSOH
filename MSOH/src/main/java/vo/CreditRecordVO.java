package vo;

import util.CreditChangeReason;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author pxr
 */
public class CreditRecordVO implements Serializable {

    public int variation;

    public Timestamp changeTime;

    public String customerName;

    public String customerID;

    public int afterChangeCredit;

    public String orderID;

    public String marketerName;

    // 变化原因
    public CreditChangeReason reason;

    public CreditRecordVO() {
    }

    public CreditRecordVO(int variation, Timestamp changeTime, String customerName, String customerID, int afterChangeCredit, String orderID,
                          String marketerName, CreditChangeReason creditChangeReason) {
        this.variation = variation;
        this.changeTime = changeTime;
        this.customerName = customerName;
        this.customerID = customerID;
        this.afterChangeCredit = afterChangeCredit;
        this.orderID = orderID;
        this.marketerName = marketerName;
        this.reason = creditChangeReason;
    }
}
