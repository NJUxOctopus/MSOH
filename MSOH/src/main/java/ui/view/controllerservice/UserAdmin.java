package ui.view.controllerservice;

import util.ResultMessage;
import vo.ClerkVO;
import vo.CustomerVO;
import vo.MarketerVO;
import vo.UserVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/12.
 */
public interface UserAdmin {
    public UserVO findAllByID(String ID);

    public UserVO findAllByName(String name);

    public List<ClerkVO> findAllClerk();

    public List<ClerkVO> findClerkByName(String name);

    public ClerkVO findClerkByID(String ID) throws RemoteException;

    public List<MarketerVO> findAllMarketer();

    public List<MarketerVO> findMarketerByName(String name);

    public MarketerVO findMarketerByID(String ID);

    public List<CustomerVO> findAllCustomer();

    public List<CustomerVO> findCustomerByName(String name);

    public CustomerVO findCustomerByID(String ID);

    public ResultMessage addClerk(ClerkVO vo);

    public ResultMessage addMarketer(MarketerVO vo);

    public ResultMessage deleteClerk(ClerkVO vo);

    public ResultMessage deleteMarketer(MarketerVO vo);

    public ResultMessage updateCustomerInfo(CustomerVO vo);

    public ResultMessage updateMarketerInfo(MarketerVO vo);
}
