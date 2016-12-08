package ui.controller;

import businesslogic.customer_bl.Customer;
import businesslogicservice.customer_blservice.Customer_BLService;
import ui.view.controllerservice.CustomerInfoChange;
import util.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/10.
 */
public class CustomerInfoChangeController implements CustomerInfoChange{
    private Customer_BLService customer_blService;

    public CustomerInfoChangeController(){
        customer_blService = new Customer();
    }
    @Override
    public ResultMessage changeInfo(CustomerVO customerVO) throws RemoteException{
        return customer_blService.changeInfo(customerVO);
    }

    @Override
    public ResultMessage changePassword(String ID, String oldPw, String newPw1, String newPw2)throws RemoteException {
        return customer_blService.changePassword(ID, oldPw, newPw1, newPw2);
    }
}
