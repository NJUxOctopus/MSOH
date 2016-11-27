package DataHelper;

import po.CustomerPO;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public interface CustomerDataHelper {
    public void addCustomer(CustomerPO customerPO);

    public void deleteCustomer(CustomerPO customerPO);

    public void modifyCustomer(CustomerPO customerPO);

    public List<CustomerPO> getAllCustomers();
}
