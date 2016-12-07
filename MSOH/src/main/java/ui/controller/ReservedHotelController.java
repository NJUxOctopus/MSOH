package ui.controller;

import businesslogic.customer_bl.Customer;
import businesslogicservice.customer_blservice.Customer_BLService;
import ui.view.controllerservice.ReservedHotel;
import vo.CustomerVO;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/10.
 */
public class ReservedHotelController implements ReservedHotel {
    private Customer_BLService customer_blService;

    public ReservedHotelController(){
        customer_blService = new Customer();
    }
    @Override
    public List<HotelVO> getHistoryHotel(String customerID)  throws RemoteException {
        return customer_blService.getHistoryHotel(customerID);
    }
}
