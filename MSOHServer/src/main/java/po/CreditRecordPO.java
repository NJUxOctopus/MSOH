package po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
/**
 *
 * @author zqh
 *
 */
@Entity
@Table(name = "creditrecord",schema = "msoh_database")
public class CreditRecordPO implements Serializable {
	private static final long serialVersionUID=1L;

	// 信用记录ID，为数据库中自动生成，仅供数据库使用，无实际意义
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "creditrecord_id_seq")
	@SequenceGenerator(name = "creditrecord_id_seq",sequenceName = "creditrecord_id_seq",allocationSize = 1)
	@Column(name = "creditRecordID")
	private int _autoId;
	// 变化值
	@Column(name = "variation")
	private int variation;
	// 更改时间
	@Column(name = "changeTime")
	private Timestamp changeTime;
	// 客户姓名
	@Column(name = "customerName")
	private String customerName;
	// 客户ID
	@Column(name = "customerID")
	private String customerID;
	// 变更后的信用值
	@Column(name = "afterChangeCredit")
	private int afterChangeCredit;
	// 订单号
	@Column(name = "orderID")
	private String orderID;
	// 营销人员姓名
	@Column(name = "marketerName")
	private String marketerName;

	public CreditRecordPO() {
	}

	public CreditRecordPO(int variation, Timestamp changeTime, String customerName, String customerID, int afterChangeCredit, String orderID,
						  String marketerName) {
		this.variation = variation;
		this.changeTime = changeTime;
		this.customerName = customerName;
		this.customerID=customerID;
		this.afterChangeCredit = afterChangeCredit;
		this.orderID = orderID;
		this.marketerName = marketerName;
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

	public String getCustomerID(){
		return customerID;
	}

	public void setCustomerID(String customerID){
		this.customerID=customerID;
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

}
