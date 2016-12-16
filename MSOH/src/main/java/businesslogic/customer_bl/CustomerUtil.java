package businesslogic.customer_bl;

import businesslogicservice.customer_blservice.CustomerUtil_BLService;
import dataservice.customer_dataservice.Customer_DataService;
import po.CustomerPO;
import rmi.RemoteHelper;
import vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/11/17.
 */
public class CustomerUtil implements CustomerUtil_BLService {
    private Customer_DataService customer_dataService = RemoteHelper.getInstance().getCustomerDataService();

    /**
     * 得到所有的用户
     *
     * @return
     * @throws RemoteException
     */
    public List<CustomerVO> getAll() throws RemoteException {
        List<CustomerPO> customerPOList = customer_dataService.findAllCustomers();
        if (customerPOList == null || customerPOList.isEmpty())
            //若用户列表为空
            return new ArrayList<CustomerVO>();
        else {
            List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
            for (CustomerPO customerPO : customerPOList) {
                customerVOList.add(new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                        customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicUrl(),
                        customerPO.getID(), customerPO.getMemberType()));
            }
            return customerVOList;
        }
    }

    /**
     * 根据ID得到用户
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public CustomerVO getSingle(String ID) throws RemoteException {
        if (ID.equals(""))//若ID为空
            return null;
        if (customer_dataService.findCustomerByID(ID) == null)
            //若用户不存在
            return null;
        CustomerPO customerPO = customer_dataService.findCustomerByID(ID);
        return new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicUrl(),
                customerPO.getID(), customerPO.getMemberType());
    }

    /**
     * 根据名字得到用户列表
     *
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<CustomerVO> getByName(String name) throws RemoteException {
        if (name.equals(""))
            //若名字为空
            return null;
        List<CustomerPO> customerPOList = customer_dataService.findCustomerByName(name);
        if (customerPOList == null || customerPOList.isEmpty())
            //若列表为空
            return new ArrayList<CustomerVO>();
        else {
            List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
            for(CustomerPO customerPO:customerPOList){
                customerVOList.add(new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                        customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicUrl(),
                        customerPO.getID(), customerPO.getMemberType()));
            }
            return customerVOList;
        }
    }
}
