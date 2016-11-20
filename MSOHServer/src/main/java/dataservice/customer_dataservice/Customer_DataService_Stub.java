package dataservice.customer_dataservice;

import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;
import util.MemberType;
import util.ResultMessage;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ���ߺ�
 */
public class Customer_DataService_Stub implements Customer_DataService {

    @Override
    public boolean addCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    @Override
    public boolean modifyCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteCustomer(CustomerPO customerPO) throws RemoteException {
        return false;
    }

    @Override
    public List<CustomerPO> findCustomerByName(String customerName) throws RemoteException {
        return null;
    }

    @Override
    public boolean addCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return false;
    }

    @Override
    public List<CreditRecordPO> findCreditRecordByID(String ID) throws RemoteException {
        return null;
    }

    @Override
    public CustomerPO findCustomerByID(String customerID) throws RemoteException {
        if(customerID=="320200000000000000")
            return new CustomerPO("pxr", "123456", "12345678910", "123@qq.com", 10, new ImageIcon().getImage(),
                customerID, MemberType.ENTREPRISE);
        else
            return null;
    }

    @Override
    public List<CustomerPO> findAllCustomers() throws RemoteException {
        return null;
    }

    @Override
    public List<HotelPO> getCustomerReservedHotel(String ID) throws RemoteException {
        return null;
    }
}
