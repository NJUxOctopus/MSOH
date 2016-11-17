package businesslogic.customer_bl;

import businesslogicservice.customerUtil_blservice.CustomerUtil_BLService;
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
    public List<CustomerVO> getAll() throws RemoteException {
        //这个按照什么查找？
        return null;
    }

    public CustomerVO getSingle(String ID) throws RemoteException {
        //先按照ID查找到用户的列表，在转换成vo
        CustomerPO customerPO = RemoteHelper.getInstance().getCustomerDataService().find(ID);
        return new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicture(),
                customerPO.getID(), customerPO.getMemberType());
    }

    public List<CustomerVO> getByName(String name) throws RemoteException {
        //先按照名字查找到用户的列表，在转换成vo
        List<CustomerPO> customerPOList = RemoteHelper.getInstance().getCustomerDataService().findByName(name);
        if (customerPOList == null)
            return null;
        else {
            List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
            Iterator iterator = customerPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                CustomerPO customerPO = (CustomerPO) object;
                customerVOList.add(new CustomerVO(customerPO.getUserName(), customerPO.getPassword(), customerPO.getPhone(),
                        customerPO.getEmail(), customerPO.getCredit(), customerPO.getPicture(),
                        customerPO.getID(), customerPO.getMemberType()));
            }
            return customerVOList;
        }
    }
}
