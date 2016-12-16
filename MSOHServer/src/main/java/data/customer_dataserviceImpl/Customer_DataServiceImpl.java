package data.customer_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.customerDataHelper.CreditRecordDataHelper;
import DataHelper.customerDataHelper.CustomerDataHelper;
import DataHelper.hotelDataHelper.HotelDataHelper;
import DataHelper.orderDataHelper.OrderDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.customer_dataservice.Customer_DataService;
import po.CreditRecordPO;
import po.CustomerPO;
import po.HotelPO;
import po.OrderPO;
import util.DataUtil.CopyUtil;
import util.DataUtil.EncryptionUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/2.
 */
@SuppressWarnings(value = {"Duplicates"})
public class Customer_DataServiceImpl implements Customer_DataService {
    private CustomerDataHelper customerDataHelper;

    private CreditRecordDataHelper creditRecordDataHelper;

    private HotelDataHelper hotelDataHelper;

    private OrderDataHelper orderDataHelper;

    private DataFactory dataFactory;

    private static final String key = "20162017";

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
        // 姓名、密码、联系方式、ID、email加密
        String name = EncryptionUtil.encode(key, customerPO.getUserName());
        String pw = EncryptionUtil.encode(key, customerPO.getPassword());
        String phone = EncryptionUtil.encode(key, customerPO.getPhone());
        String customerID = EncryptionUtil.encode(key, customerPO.getID());
        String email = EncryptionUtil.encode(key, customerPO.getEmail());
        customerPO.setUserName(name);
        customerPO.setPassword(pw);
        customerPO.setPhone(phone);
        customerPO.setID(customerID);
        customerPO.setEmail(email);

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

        // 姓名、密码、联系方式、ID、email加密
        String name = EncryptionUtil.encode(key, customerPO.getUserName());
        String pw = EncryptionUtil.encode(key, customerPO.getPassword());
        String phone = EncryptionUtil.encode(key, customerPO.getPhone());
        String customerID = EncryptionUtil.encode(key, customerPO.getID());
        String email = EncryptionUtil.encode(key, customerPO.getEmail());
        customerPO.setUserName(name);
        customerPO.setPassword(pw);
        customerPO.setPhone(phone);
        customerPO.setID(customerID);
        customerPO.setEmail(email);

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

        // 姓名、密码、联系方式、ID、email加密
        String name = EncryptionUtil.encode(key, customerPO.getUserName());
        String pw = EncryptionUtil.encode(key, customerPO.getPassword());
        String phone = EncryptionUtil.encode(key, customerPO.getPhone());
        String customerID = EncryptionUtil.encode(key, customerPO.getID());
        String email = EncryptionUtil.encode(key, customerPO.getEmail());
        customerPO.setUserName(name);
        customerPO.setPassword(pw);
        customerPO.setPhone(phone);
        customerPO.setID(customerID);
        customerPO.setEmail(email);

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
        customerName = EncryptionUtil.encode(key, customerName);

        List<CustomerPO> customerPOList = customerDataHelper.findCustomerByName(customerName);

        if (customerPOList == null || customerPOList.isEmpty()) {
            return new ArrayList<CustomerPO>();
        }

        for (CustomerPO customer : customerPOList) {
            // 姓名、密码、联系方式、ID、email解密
            customer.setUserName(EncryptionUtil.decode(key, customer.getUserName()));
            customer.setPassword(EncryptionUtil.decode(key, customer.getPassword()));
            customer.setPhone(EncryptionUtil.decode(key, customer.getPhone()));
            customer.setID(EncryptionUtil.decode(key, customer.getID()));
            customer.setEmail(EncryptionUtil.decode(key, customer.getEmail()));
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
        customerID = EncryptionUtil.encode(key, customerID);

        CustomerPO customerPO = customerDataHelper.findCustomerByID(customerID);

        if (customerPO == null) {
            return customerPO;
        } else {
            // 姓名、密码、联系方式、ID、email解密
            customerPO.setUserName(EncryptionUtil.decode(key, customerPO.getUserName()));
            customerPO.setPassword(EncryptionUtil.decode(key, customerPO.getPassword()));
            customerPO.setPhone(EncryptionUtil.decode(key, customerPO.getPhone()));
            customerPO.setID(EncryptionUtil.decode(key, customerPO.getID()));
            customerPO.setEmail(EncryptionUtil.decode(key, customerPO.getEmail()));
        }

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
            return new ArrayList<CustomerPO>();
        }

        for (CustomerPO customer : customerList) {
            // 姓名、密码、联系方式、ID、email解密
            customer.setUserName(EncryptionUtil.decode(key, customer.getUserName()));
            customer.setPassword(EncryptionUtil.decode(key, customer.getPassword()));
            customer.setPhone(EncryptionUtil.decode(key, customer.getPhone()));
            customer.setID(EncryptionUtil.decode(key, customer.getID()));
            customer.setEmail(EncryptionUtil.decode(key, customer.getEmail()));
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
        ID = EncryptionUtil.encode(key, ID);

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

        if (reservedHotelIDList == null || reservedHotelIDList.isEmpty()) {
            return new ArrayList<HotelPO>();
        }

        // 根据酒店ID查找酒店
        List<HotelPO> reservedHotelList = new ArrayList<HotelPO>();
        for (String hotelID : reservedHotelIDList) {
            if (hotelDataHelper.getHotelByID(hotelID) == null) {
                continue;
            }
            reservedHotelList.add(hotelDataHelper.getHotelByID(hotelID));
        }

        for (HotelPO hotel : reservedHotelList) {
            // 酒店工作人员ID解密
            hotel.setClerkID(EncryptionUtil.decode(key, hotel.getClerkID()));
        }

        return CopyUtil.deepCopy(reservedHotelList);
    }

    /**
     * 新增信用记录
     *
     * @param creditRecordPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean addCreditRecord(CreditRecordPO creditRecordPO) throws RemoteException {
        // 客户ID、姓名、营销人员姓名加密
        String customerID = EncryptionUtil.encode(key, creditRecordPO.getCustomerID());
        String customerName = EncryptionUtil.encode(key, creditRecordPO.getCustomerName());
        creditRecordPO.setCustomerID(customerID);
        creditRecordPO.setCustomerName(customerName);
        if (creditRecordPO.getMarketerName() != null || creditRecordPO.getMarketerName() != "") {
            String marketerName = EncryptionUtil.encode(key, creditRecordPO.getMarketerName());
            creditRecordPO.setMarketerName(marketerName);
        }

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
        // 客户ID、姓名、营销人员姓名加密
        String customerID = EncryptionUtil.encode(key, creditRecordPO.getCustomerID());
        String customerName = EncryptionUtil.encode(key, creditRecordPO.getCustomerName());
        creditRecordPO.setCustomerID(customerID);
        creditRecordPO.setCustomerName(customerName);
        if (creditRecordPO.getMarketerName() != null || creditRecordPO.getMarketerName() != "") {
            String marketerName = EncryptionUtil.encode(key, creditRecordPO.getMarketerName());
            creditRecordPO.setMarketerName(marketerName);
        }

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
        ID = EncryptionUtil.encode(key, ID);

        List<CreditRecordPO> creditRecordList = creditRecordDataHelper.findCreditRecordByID(ID);

        if (creditRecordList == null || creditRecordList.isEmpty()) {
            return new ArrayList<CreditRecordPO>();
        } else {
            // 客户姓名、ID、营销人员姓名解密
            for (CreditRecordPO creditRecord : creditRecordList) {
                creditRecord.setCustomerName(EncryptionUtil.decode(key, creditRecord.getCustomerName()));
                creditRecord.setCustomerID(EncryptionUtil.decode(key, creditRecord.getCustomerID()));
                if (creditRecord.getMarketerName() != null || creditRecord.getMarketerName() != "") {
                    creditRecord.setMarketerName(EncryptionUtil.decode(key, creditRecord.getMarketerName()));
                }
            }
        }

        List<CreditRecordPO> list = CopyUtil.deepCopy(creditRecordList);

        return list;
    }
}
