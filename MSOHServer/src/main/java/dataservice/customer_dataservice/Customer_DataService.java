package dataservice.customer_dataservice;

import java.util.List;

import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;
import util.ResultMessage;

/**
 * @author ÂXø¬”Ó
 */
public interface Customer_DataService {

    public CustomerPO find(String customerID);

    public String getID(CustomerPO customerPO);

    public ResultMessage add(CustomerPO customerPO);

    public double getCurrentCredit(String customerID);

    public ResultMessage modify(CustomerPO customerPO);

    public List<CustomerPO> findByID(String customerID);

    public CreditRecordPO getCreditRecord(CustomerPO customerPO);

    public List<HotelPO> getReservedHotel(CustomerPO customerPO);

    public ResultMessage addCreditRecord(String customerID, CreditRecordPO creditRecordPO);
}
