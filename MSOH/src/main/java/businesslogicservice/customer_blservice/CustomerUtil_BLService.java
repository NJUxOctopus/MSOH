package businesslogicservice.customer_blservice;

import vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Pxr on 16/11/17.
 */
public interface CustomerUtil_BLService {

    public List<CustomerVO> getAll() throws RemoteException;

    public CustomerVO getSingle(String ID) throws RemoteException;

    public List<CustomerVO> getByName(String name) throws RemoteException;

}
