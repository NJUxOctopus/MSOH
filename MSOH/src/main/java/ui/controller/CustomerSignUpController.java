package ui.controller;

import businesslogic.customer_bl.Customer;
import businesslogicservice.customer_blservice.Customer_BLService;
import ui.view.controllerservice.CustomerSignUp;
import util.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/11/10.
 */
public class CustomerSignUpController implements CustomerSignUp{

    private Customer_BLService customer_blService;

    public CustomerSignUpController(){
        customer_blService =new Customer();
    }
    public ResultMessage signUp(CustomerVO customerVO) throws RemoteException{
        return customer_blService.signUp(customerVO);
    }

}
