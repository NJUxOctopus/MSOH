package dataservice.customer_dataservice;

import java.util.ArrayList;
import java.util.List;

import po.CustomerPO;
import po.HotelPO;
import util.ResultMessage;
/**
 * 
 * @author ÷‹«ﬂ∫≠
 *
 */
public class Customer_DataService_Stub implements Customer_DataService{
	

	@Override
	public ResultMessage add(CustomerPO customerPO) {
		// TODO Auto-generated method stub
		if(customerPO.getID().equals("320581190001012016")){
			return ResultMessage.Manager_AddCustomerAlreadyExist;
		}else{
			return ResultMessage.Manager_AddCustomerSuccess;
		}
	}

	@Override
	public ResultMessage modify(CustomerPO customerPO) {
		// TODO Auto-generated method stub
		if(customerPO.getID().equals("320581190001012016")){
			return ResultMessage.Manager_ChangeCustomerInfoSuccess;
		}else{
			return ResultMessage.Customer_CustomerNotExist;
		}
	}

	@Override
	public List<CustomerPO> find(String id) {
		// TODO Auto-generated method stub
		ArrayList<CustomerPO> customer=new ArrayList<CustomerPO>();
		customer.add(new CustomerPO());
		return customer;
	}

	@Override
	public String getID(CustomerPO customerPO) {
		// TODO Auto-generated method stub
		return "320581190001012016";
	}

	@Override
	public int getCurrentCredit(String customer_id) {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public List<HotelPO> getReservedHotel(CustomerPO customerPO) {
		// TODO Auto-generated method stub
		ArrayList<HotelPO> hotel=new ArrayList<HotelPO>();
		hotel.add(new HotelPO());
		return hotel;
	}

}
