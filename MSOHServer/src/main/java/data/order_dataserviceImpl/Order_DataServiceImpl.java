package data.order_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.orderDataHelper.OrderDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.order_dataservice.Order_DataService;
import po.OrderPO;
import util.DataUtil.CopyUtil;
import util.DataUtil.EncryptionUtil;
import util.OrderStatus;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
@SuppressWarnings(value = {"Duplicates"})
public class Order_DataServiceImpl implements Order_DataService {
    private OrderDataHelper orderDataHelper;

    private DataFactory dataFactory;

    private static Order_DataServiceImpl order_dataServiceImpl;

    private static final String key = "20162017";

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return order_dataservice的实例
     */
    public static Order_DataServiceImpl getInstance() {
        if (order_dataServiceImpl == null) {
            order_dataServiceImpl = new Order_DataServiceImpl();
        }
        return order_dataServiceImpl;
    }

    private Order_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        orderDataHelper = dataFactory.getOrderDataHelper();
    }

    /**
     * 增加订单
     *
     * @param po
     * @throws RemoteException
     */
    public boolean addOrder(OrderPO po) throws IOException, ClassNotFoundException {
        // Generate OrderID automatically
        // Format : 201611300001 12 bits
        String thisOrderID = generateOrderID();

        po.setOrderID(thisOrderID);

        po.setCustomerID(EncryptionUtil.encode(key, po.getCustomerID()));
        po.setCustomerName(EncryptionUtil.encode(key, po.getCustomerName()));
        po.setPhone(EncryptionUtil.encode(key, po.getPhone()));

        return orderDataHelper.addOrder(po);
    }

    /**
     * 获取所有订单
     *
     * @return 所有订单组成的列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<OrderPO> getAllOrders() throws IOException, ClassNotFoundException {
        List<OrderPO> orderPOList = orderDataHelper.getAllOrders();
        if (null == orderPOList || orderPOList.isEmpty()) {
            return new ArrayList<OrderPO>();
        } else {
            for (OrderPO order : orderPOList) {
                order.setCustomerName(EncryptionUtil.decode(key, order.getCustomerName()));
                order.setCustomerID(EncryptionUtil.decode(key, order.getCustomerID()));
                order.setPhone(EncryptionUtil.decode(key, order.getPhone()));
            }
        }

        List<OrderPO> copiedList = CopyUtil.deepCopy(orderPOList);

        return copiedList;
    }

    /**
     * 根据订单ID搜索订单
     *
     * @param orderID
     * @return 与订单ID匹配的订单
     * @throws RemoteException
     */
    public OrderPO getOrderByOrderID(String orderID) throws RemoteException {
        OrderPO orderPO = orderDataHelper.getOrderByOrderID(orderID);

        if (null == orderPO) {
            return null;
        }

        orderPO.setCustomerName(EncryptionUtil.decode(key, orderPO.getCustomerName()));
        orderPO.setCustomerID(EncryptionUtil.decode(key, orderPO.getCustomerID()));
        orderPO.setPhone(EncryptionUtil.decode(key, orderPO.getPhone()));

        return (OrderPO) orderPO.clone();
    }

    /**
     * 根据客户ID搜索订单
     *
     * @param customerID
     * @return 该ID的客户的所有订单列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<OrderPO> findOrderByCustomerID(String customerID) throws IOException, ClassNotFoundException {
        customerID = EncryptionUtil.encode(key, customerID);

        List<OrderPO> orderPOList = orderDataHelper.findOrderByCustomerID(customerID);

        if (null == orderPOList || orderPOList.isEmpty()) {
            return new ArrayList<OrderPO>();
        } else {
            for (OrderPO order : orderPOList) {
                order.setCustomerName(EncryptionUtil.decode(key, order.getCustomerName()));
                order.setCustomerID(EncryptionUtil.decode(key, order.getCustomerID()));
                order.setPhone(EncryptionUtil.decode(key, order.getPhone()));
            }
        }

        List<OrderPO> copiedList = CopyUtil.deepCopy(orderPOList);

        return copiedList;
    }

    /**
     * 根据酒店ID搜索订单
     *
     * @param hotelID
     * @return 该酒店的所有订单
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<OrderPO> findOrderByHotelID(String hotelID) throws IOException, ClassNotFoundException {
        List<OrderPO> orderPOList = orderDataHelper.findOrderByHotelID(hotelID);

        if (null == orderPOList || orderPOList.isEmpty()) {
            return new ArrayList<OrderPO>();
        } else {
            for (OrderPO order : orderPOList) {
                order.setCustomerName(EncryptionUtil.decode(key, order.getCustomerName()));
                order.setCustomerID(EncryptionUtil.decode(key, order.getCustomerID()));
                order.setPhone(EncryptionUtil.decode(key, order.getPhone()));
            }
        }
        List<OrderPO> copiedList = CopyUtil.deepCopy(orderPOList);

        return copiedList;
    }

    /**
     * 根据订单状态搜索订单
     *
     * @param orderStatus
     * @return 对应状态的所有订单的列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) throws IOException, ClassNotFoundException {
        List<OrderPO> orderPOList = orderDataHelper.findOrderByOrderStatus(orderStatus);

        if (null == orderPOList || orderPOList.isEmpty()) {
            return new ArrayList<OrderPO>();
        } else {
            for (OrderPO order : orderPOList) {
                order.setCustomerName(EncryptionUtil.decode(key, order.getCustomerName()));
                order.setCustomerID(EncryptionUtil.decode(key, order.getCustomerID()));
                order.setPhone(EncryptionUtil.decode(key, order.getPhone()));
            }
        }


        List<OrderPO> copiedList = CopyUtil.deepCopy(orderPOList);

        return copiedList;
    }

    /**
     * 更新订单
     *
     * @param orderPO
     * @throws RemoteException
     */
    public boolean updateOrder(OrderPO orderPO) throws RemoteException {
        orderPO.setCustomerID(EncryptionUtil.encode(key, orderPO.getCustomerID()));
        orderPO.setCustomerName(EncryptionUtil.encode(key, orderPO.getCustomerName()));
        orderPO.setPhone(EncryptionUtil.encode(key, orderPO.getPhone()));

        return orderDataHelper.updateOrder(orderPO);
    }

    /**
     * 删除订单
     *
     * @param orderPO
     * @throws RemoteException
     */
    public boolean deleteOrder(OrderPO orderPO) throws RemoteException {
        orderPO.setCustomerID(EncryptionUtil.encode(key, orderPO.getCustomerID()));
        orderPO.setCustomerName(EncryptionUtil.encode(key, orderPO.getCustomerName()));
        orderPO.setPhone(EncryptionUtil.encode(key, orderPO.getPhone()));

        return orderDataHelper.deleteOrder(orderPO);
    }

    /**
     * Generate OrderID automatically
     * Format : 201611300001 12 bits
     *
     * @return OrderID
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private String generateOrderID() throws IOException, ClassNotFoundException {
        Date today = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String thisOrderID = simpleDateFormat.format(today);

        List<OrderPO> list = this.getAllOrders();

        List<OrderPO> todayList = new ArrayList<OrderPO>();

        Iterator<OrderPO> it = list.iterator();
        while (it.hasNext()) {
            OrderPO orderPO = it.next();
            if (orderPO.getOrderID().contains(thisOrderID)) {
                todayList.add(orderPO);
            }
        }

        if (todayList.isEmpty()) {
            thisOrderID += "0001";
        } else {
            long todayNum = 0;
            for (OrderPO order : todayList) {
                long thisNum = Long.valueOf(order.getOrderID());
                if (thisNum > todayNum) {
                    todayNum = thisNum;
                }
            }
            thisOrderID = String.valueOf(todayNum + 1);
        }

        return thisOrderID;
    }
}
