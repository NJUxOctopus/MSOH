package ui.controller;

import businesslogic.clerk_bl.Clerk;
import businesslogic.clerk_bl.ClerkUtil;
import businesslogic.customer_bl.CustomerUtil;
import businesslogic.manager_bl.ManagerUtil;
import businesslogic.marketer_bl.Marketer;
import businesslogic.marketer_bl.MarketerUtil;
import businesslogic.member_bl.MemberUtil;
import businesslogicservice.clerk_blservice.ClerkUtil_BLService;
import businesslogicservice.clerk_blservice.Clerk_BLService;
import businesslogicservice.customer_blservice.CustomerUtil_BLService;
import businesslogicservice.manager_blservice.ManagerUtil_BLService;
import businesslogicservice.marketer_blservice.MarketerUtil_BLService;
import businesslogicservice.marketer_blservice.Marketer_BLService;
import businesslogicservice.member_blservice.MemberUtil_BLService;
import ui.view.controllerservice.UserAdmin;
import util.ResultMessage;
import vo.*;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/12.
 */
public class UserAdminController implements UserAdmin {

    private ClerkUtil_BLService clerkUtil_blService;

    private CustomerUtil_BLService customerUtil_blService;

    private Clerk_BLService clerk_blService;

    private MemberUtil_BLService memberUtil_blService;

    private MarketerUtil_BLService marketerUtil_blService;

    private Marketer_BLService marketer_blService;

    private ManagerUtil_BLService managerUtil_blService;

    public UserAdminController(){
        customerUtil_blService = new CustomerUtil();
        memberUtil_blService = new MemberUtil();
        marketerUtil_blService = new MarketerUtil();
        marketer_blService = new Marketer();
        clerk_blService = new Clerk();
        managerUtil_blService = new ManagerUtil();
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

    public List<MarketerVO> findMarketerByName(String name)throws RemoteException  {
        return marketerUtil_blService.getByName(name);
    }

    public MarketerVO findMarketerByID(String ID) throws RemoteException {
        return marketerUtil_blService.getSingle(ID);
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

    public ManagerVO findManagerByID(String ID) throws RemoteException {
        return managerUtil_blService.getByID(ID);
    }

    public ResultMessage addClerk(ClerkVO vo)throws RemoteException {
        return clerk_blService.addClerk(vo);
    }

    public ResultMessage addMarketer(MarketerVO vo)throws RemoteException {
        return marketer_blService.addMarketer(vo);
    }

    public ResultMessage deleteClerk(ClerkVO vo) {
        return null;
    }

    public ResultMessage deleteMarketer(MarketerVO vo) {
        return null;
    }


    public MemberVO findMemberByID(String ID) throws RemoteException{
        return memberUtil_blService.getSingle(ID);
    }

}
