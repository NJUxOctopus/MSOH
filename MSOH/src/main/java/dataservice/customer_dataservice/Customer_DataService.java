package dataservice.customer_dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;
import ui.view.controllerservice.CreditRecord;
import util.ResultMessage;

/**
 * @author zqh
 */
public interface Customer_DataService extends Remote {

    public CustomerPO find(String customerID) throws RemoteException;

    public String getID(CustomerPO customerPO) throws RemoteException;

    public ResultMessage add(CustomerPO customerPO) throws RemoteException;

    public double getCurrentCredit(String customerID) throws RemoteException;

    public ResultMessage modify(CustomerPO customerPO) throws RemoteException;

    public List<CustomerPO> findByID(String customerID) throws RemoteException;

    public CreditRecordPO getCreditRecord(CustomerPO customerPO) throws RemoteException;

    public List<HotelPO> getReservedHotel(CustomerPO customerPO) throws RemoteException;

    public ResultMessage addCreditRecord(String customerID, CreditRecordPO creditRecordPO) throws RemoteException;

}
