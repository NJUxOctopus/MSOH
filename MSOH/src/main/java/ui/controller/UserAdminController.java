package ui.controller;

import businesslogic.clerk_bl.ClerkUtil;
import businesslogic.customer_bl.CustomerUtil;
import businesslogicservice.clerk_blservice.ClerkUtil_BLService;
import businesslogicservice.clerk_blservice.Clerk_BLService;
import businesslogicservice.customer_blservice.CustomerUtil_BLService;
import ui.view.controllerservice.UserAdmin;
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
public class UserAdminController implements UserAdmin {

    private ClerkUtil_BLService clerkUtil_blService;

    private CustomerUtil_BLService customerUtil_blService;

    private Clerk_BLService clerk_blService;

    public UserAdminController(){
        customerUtil_blService = new CustomerUtil();
    }

    public UserVO findAllByID(String ID) {
        return null;
    }

    public UserVO findAllByName(String name) {
        return null;
    }

    public List<ClerkVO> findAllClerk() {
        return null;
    }

    public List<ClerkVO> findClerkByName(String name) {
        return null;
    }

    public ClerkVO findClerkByID(String ID) throws RemoteException {
        clerkUtil_blService = new ClerkUtil();
        return clerkUtil_blService.getSingle(ID);
    }

    public List<MarketerVO> findAllMarketer() {
        return null;
    }

    public List<MarketerVO> findMarketerByName(String name) {
        return null;
    }

    public MarketerVO findMarketerByID(String ID) {
        return null;
    }

    public List<CustomerVO> findAllCustomer() {
        return null;
    }

    public List<CustomerVO> findCustomerByName(String name) {
        return null;
    }

    public CustomerVO findCustomerByID(String ID) throws RemoteException {
        return customerUtil_blService.getSingle(ID);
    }

    public ResultMessage addClerk(ClerkVO vo) {
        return null;
    }

    public ResultMessage addMarketer(MarketerVO vo) {
        return null;
    }

    public ResultMessage deleteClerk(ClerkVO vo) {
        return null;
    }

    public ResultMessage deleteMarketer(MarketerVO vo) {
        return null;
    }

    public ResultMessage updateCustomerInfo(CustomerVO vo) {
        return null;
    }

    public ResultMessage updateMarketerInfo(MarketerVO vo) {
        return null;
    }
}
