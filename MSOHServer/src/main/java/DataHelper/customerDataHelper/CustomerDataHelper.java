package DataHelper.customerDataHelper;

import po.CustomerPO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface CustomerDataHelper {
    public boolean addCustomer(CustomerPO customerPO);

    public boolean deleteCustomer(CustomerPO customerPO);

    public boolean modifyCustomer(CustomerPO customerPO);

    public List<CustomerPO> findCustomerByName(String customerName);

    public CustomerPO findCustomerByID(String customerID);

    public List<CustomerPO> findAllCustomers();
}
