package data;

import DataHelper.*;
import DataHelperImpl.DataFactoryImpl;
import dataservice.customer_dataservice.Customer_DataService;
import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;
import po.OrderPO;
import util.CopyUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/2.
 */
public class Customer_DataServiceImpl implements Customer_DataService {
    private CustomerDataHelper customerDataHelper;

    private CreditRecordDataHelper creditRecordDataHelper;

    private HotelDataHelper hotelDataHelper;

    private OrderDataHelper orderDataHelper;

    private DataFactory dataFactory;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return customerDataService的实例
     */
    private static Customer_DataServiceImpl customer_dataService;

    public static Customer_DataServiceImpl getInstance() {
        if (customer_dataService == null) {
            customer_dataService = new Customer_DataServiceImpl();
        }
        return customer_dataService;
    }

    private Customer_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        customerDataHelper = dataFactory.getCustomerDataHelper();
        creditRecordDataHelper = dataFactory.getCreditRecordDataHelper();
        hotelDataHelper = dataFactory.getHotelDataHelper();
        orderDataHelper = dataFactory.getOrderDataHelper();
    }

    /**
     * 新增客户
     *
     * @param customerPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean addCustomer(CustomerPO customerPO) throws RemoteException {
        return customerDataHelper.addCustomer(customerPO);
    }

    /**
     * 修改客户信息
     *
     * @param customerPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean modifyCustomer(CustomerPO customerPO) throws RemoteException {
        // 数据库中主键不能为空，否则更新不成功
        if (customerPO.getID() == null) {
            return false;
        }
        return customerDataHelper.modifyCustomer(customerPO);
    }

    /**
     * 删除客户
     *
     * @param customerPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean deleteCustomer(CustomerPO customerPO) throws RemoteException {
        // 数据库中主键不能为空，否则删除不成功
        if (customerPO.getID() == null) {
            return false;
        }
        return customerDataHelper.deleteCustomer(customerPO);
    }

    /**
     * 根据姓名查找客户
     *
     * @param customerName
     * @return 与客户姓名相匹配的客户列表
     * @throws RemoteException
     */
    public List<CustomerPO> findCustomerByName(String customerName) throws IOException, ClassNotFoundException {
        List<CustomerPO> customerPOList = customerDataHelper.findCustomerByName(customerName);

        if (customerPOList.isEmpty() || customerPOList == null) {
            return null;
        }

        List<CustomerPO> list = CopyUtil.deepCopy(customerPOList);

        return list;
    }

    /**
     * 根据ID查找客户
     *
     * @param customerID
     * @return 与ID相匹配的客户
     * @throws RemoteException
     */
    public CustomerPO findCustomerByID(String customerID) throws RemoteException {
        CustomerPO customerPO = customerDataHelper.findCustomerByID(customerID);

        return (CustomerPO) customerPO.clone();
    }

    /**
     * 获得所有客户
     *
     * @return 所有客户的列表
     * @throws RemoteException
     */
    public List<CustomerPO> findAllCustomers() throws IOException, ClassNotFoundException {
        List<CustomerPO> customerList = customerDataHelper.findAllCustomers();

        if (customerList == null || customerList.isEmpty()) {
            return null;
        }

        List<CustomerPO> list = CopyUtil.deepCopy(customerList);

        return list;
    }

    /**
     * 获得客户预定过的酒店列表
     *
     * @param ID
     * @return 客户预定过的酒店列表
     * @throws RemoteException
     */
    public List<HotelPO> getCustomerReservedHotel(String ID) throws IOException, ClassNotFoundException {
        List<OrderPO> customerOrderList = orderDataHelper.findOrderByCustomerID(ID);

        // 获得预定过的所有酒店ID（不重复的）
        List<String> reservedHotelIDList = new ArrayList<String>();
        String hotelIDinOrder = "";

        for (OrderPO orderPO : customerOrderList) {
            hotelIDinOrder = orderPO.getHotelID();
            if (!reservedHotelIDList.contains(hotelIDinOrder)) {
                reservedHotelIDList.add(hotelIDinOrder);
            }
        }

        if (reservedHotelIDList.isEmpty() || reservedHotelIDList == null) {
            return null;
        }

        // 根据酒店ID查找酒店
        List<HotelPO> reservedHotelList = new ArrayList<HotelPO>();
        for (String hotelID : reservedHotelIDList) {
            if (hotelDataHelper.getHotelByID(hotelID) == null) {
                continue;
            }
            reservedHotelList.add(hotelDataHelper.getHotelByID(hotelID));
        }

        return reservedHotelList;
    }

    /**
     * 新增信用记录
     *
     * @param creditRecordPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean addCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return creditRecordDataHelper.addCreditRecord(creditRecordPO);
    }

    /**
     * 删除信用记录
     *
     * @param creditRecordPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean deleteCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        return creditRecordDataHelper.deleteCreditRecord(creditRecordPO);
    }

    /**
     * 根据客户ID查找信用记录
     *
     * @param ID
     * @return 与ID相匹配的客户的信用记录
     * @throws RemoteException
     */
    public List<CreditRecordPO> findCreditRecordByID(String ID) throws IOException, ClassNotFoundException {
        List<CreditRecordPO> creditRecordList = creditRecordDataHelper.findCreditRecordByID(ID);

        if (creditRecordList == null || creditRecordList.isEmpty()) {
            return null;
        }

        List<CreditRecordPO> list = CopyUtil.deepCopy(creditRecordList);
        return list;
    }
}
