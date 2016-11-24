package businesslogic.customer_bl;

import businesslogicservice.customerUtil_blservice.CustomerUtil_BLService;
import dataservice.customer_dataservice.Customer_DataService_Stub;
import po.CustomerPO;
import rmi.RemoteHelper;
import vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pxr on 16/11/17.
 */
public class CustomerUtil implements CustomerUtil_BLService {
    Customer_DataService_Stub customer_dataService_stub = new Customer_DataService_Stub();
    public List<CustomerVO> getAll() throws RemoteException {
        List<CustomerPO> customerPOList = customer_dataService_stub.findAllCustomers();
        if (customerPOList == null)
            return null;
        else {
            List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
            Iterator iterator = customerPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                CustomerPO customerPO = (CustomerPO) object;
                customerVOList.add(new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                        customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicUrl(),
                        customerPO.getID(), customerPO.getMemberType()));
            }
            return customerVOList;
        }
    }

    public CustomerVO getSingle(String ID) throws RemoteException {
        if(ID.equals(""))
            return null;
        //先按照ID查找到用户的列表，在转换成vo
        if(customer_dataService_stub.findCustomerByID(ID)==null)
            return null;
        CustomerPO customerPO = customer_dataService_stub.findCustomerByID(ID);
        return new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicUrl(),
                customerPO.getID(), customerPO.getMemberType());
    }

    public List<CustomerVO> getByName(String name) throws RemoteException {
        if(name.equals(""))
            return null;
        //先按照名字查找到用户的列表，在转换成vo
        List<CustomerPO> customerPOList = customer_dataService_stub.findCustomerByName(name);
        if (customerPOList == null)
            return null;
        else {
            List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
            Iterator iterator = customerPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                CustomerPO customerPO = (CustomerPO) object;
                customerVOList.add(new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                        customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicUrl(),
                        customerPO.getID(), customerPO.getMemberType()));
            }
            return customerVOList;
        }
    }
}
