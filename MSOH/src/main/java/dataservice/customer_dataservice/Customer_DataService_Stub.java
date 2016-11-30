package dataservice.customer_dataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import po.*;
import util.MemberType;
import util.ResultMessage;
import util.WorkerPosition;

import javax.swing.*;

/**
 * 
 * @author zqh
 *
 */
public class Customer_DataService_Stub implements Customer_DataService{

	public boolean addCustomer(CustomerPO customerPO) throws RemoteException {
		return false;
	}

	public boolean modifyCustomer(CustomerPO customerPO) throws RemoteException {
		return false;
	}

	public boolean deleteCustomer(CustomerPO customerPO) throws RemoteException {
		return false;
	}

	public List<CustomerPO> findCustomerByName(String customerName) throws RemoteException {
		if(customerName.equals("pxr")){
			List<CustomerPO> customerPOList = new ArrayList<CustomerPO>();
			customerPOList.add(new CustomerPO("pxr", "123456", "12345678910", "123@qq.com", 10, "",
					"320200000000000000", MemberType.ENTREPRISE));
			return customerPOList;
		}else
			return null;
	}

	public CustomerPO findCustomerByID(String customerID) throws RemoteException {
		if(customerID.equals("320200000000000000"))
			return new CustomerPO("pxr", "123456", "12345678910", "123@qq.com", 10, "",
					customerID, MemberType.NONMEMBER);
		else if(customerID.equals("12345678"))
			return new CustomerPO("pxr", "123456", "12345678910", "123@qq.com", 10, "",
					customerID, MemberType.ENTREPRISE);
		else
			return null;
	}

	public List<CustomerPO> findAllCustomers() throws RemoteException {
		List<CustomerPO> customerPOList = new ArrayList<CustomerPO>();
		customerPOList.add(new CustomerPO("pxr", "123456", "12345678910", "123@qq.com", 10, "",
				"320200000000000000", MemberType.ENTREPRISE));
		return customerPOList;
	}

	public boolean addCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
		return false;
	}

	public boolean deleteCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
		return false;
	}

	public List<CreditRecordPO> findCreditRecordByID(String ID) throws RemoteException {
		return null;
	}

	public List<HotelPO> getCustomerReservedHotel(String ID) throws RemoteException {
		if(ID.equals("320200000000000000")){
			List<HotelPO> hotelPOList = new ArrayList<HotelPO>();
			hotelPOList.add(new HotelPO("RUJIA", "NJU", "XIANLIN", "wu", null, 5,
			5, "has", null,new ClerkPO("pxr","123","123","123","RUJIA","123", WorkerPosition.Clerk,"123"), "123", null, null));
			return hotelPOList;
		}else
			return null;
	}
}
