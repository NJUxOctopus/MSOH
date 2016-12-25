package dataservice.customer_dataservice;

import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author zqh
 */
public interface Customer_DataService extends Remote {

    // 在数据库中新增客户信息
    public boolean addCustomer(CustomerPO customerPO) throws RemoteException;

    // 更新客户在数据库中的信息
    public boolean modifyCustomer(CustomerPO customerPO) throws RemoteException;

    // 删除客户在数据库中的信息
    public boolean deleteCustomer(CustomerPO customerPO) throws RemoteException;

    // 根据姓名查找客户（返回List是考虑了同名的情形）
    public List<CustomerPO> findCustomerByName(String customerName) throws RemoteException;

     // 根据客户ID查找客户信息
    public CustomerPO findCustomerByID(String customerID) throws RemoteException;

    // 获得所有客户信息
    public List<CustomerPO> findAllCustomers() throws RemoteException;

    // 获得客户预定过的酒店列表
    public List<HotelPO> getCustomerReservedHotel(String ID) throws RemoteException;

    // 增加信用记录
    public boolean addCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException;

    // 根据ID获取客户信用记录
    public List<CreditRecordPO> findCreditRecordByID(String ID) throws RemoteException;

}
