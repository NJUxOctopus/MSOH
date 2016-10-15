package dataservice.customer_dataservice;

import java.util.List;

import po.CustomerPO;
import util.ResultMessage;
public interface Customer_DataService {
	
	public ResultMessage add(CustomerPO po);
	
	public ResultMessage modify(CustomerPO po);
	
	public List<CustomerPO> find(String id);	
	
	public ResultMessage delete(CustomerPO po);
	
	public String getID (CustomerPO po);
}
