package po;

import util.CreditChangeReason;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zqh
 */
public class CreditRecordPO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 信用记录在数据库中的索引，仅供数据库使用，无实际意义，不提供setter和getter方法
    private int _autoId;
    // 变化值
    private int variation;
    // 更改时间
    private Timestamp changeTime;
    // 客户姓名
    private String customerName;
    // 客户ID
    private String customerID;
    // 变更后的信用值
    private int afterChangeCredit;
    // 订单号
    private String orderID;
    // 营销人员姓名
    private String marketerName;
    // 变化原因
    private CreditChangeReason reason;

    public CreditRecordPO() {
    }

    public CreditRecordPO(int variation, Timestamp changeTime, String customerName, String customerID, int afterChangeCredit, String orderID,
                          String marketerName, CreditChangeReason reason) {
        this.variation = variation;
        this.changeTime = changeTime;
        this.customerName = customerName;
        this.customerID = customerID;
        this.afterChangeCredit = afterChangeCredit;
        this.orderID = orderID;
        this.marketerName = marketerName;
        this.reason = reason;
    }

    public int getVariation() {
        return variation;
    }

    public void setVariation(int variation) {
        this.variation = variation;
    }

    public Timestamp getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Timestamp changeTime) {
        this.changeTime = changeTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getAfterChangeCredit() {
        return afterChangeCredit;
    }

    public void setAfterChangeCredit(int afterChangeCredit) {
        this.afterChangeCredit = afterChangeCredit;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMarketerName() {
        return marketerName;
    }

    public void setMarketerName(String marketerName) {
        this.marketerName = marketerName;
    }

    public CreditChangeReason getReason() {
        return reason;
    }

    public void setReason(CreditChangeReason reason) {
        this.reason = reason;
    }
}
