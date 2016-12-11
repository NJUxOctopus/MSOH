package businesslogic.order_bl;

import dataservice.order_dataservice.Order_DataService;
import rmi.RemoteHelper;
import util.sort.sortOrderByDate;
import businesslogicservice.order_blservice.OrderUtil_BLService;
import util.OrderStatus;
import vo.OrderVO;
import po.OrderPO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by apple on 16/11/10.
 */
public class OrderUtil implements OrderUtil_BLService {
    Order_DataService order_dataService_stub = RemoteHelper.getInstance().getOrderDataService();

    /**
     * 根据订单号得到一个订单
     *
     * @param orderID
     * @return
     * @throws RemoteException
     */
    public OrderVO getSingle(String orderID) throws RemoteException {
        if (orderID.equals(""))
            //若ID为空
            return null;
        OrderPO orderPO = order_dataService_stub.getOrderByOrderID(orderID);
        if (orderPO == null)
            //若不存在该订单
            return null;
        return new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckInTime(),
                orderPO.getActualCheckInTime(), orderPO.getEstimatedCheckOutTime(), orderPO.getActualCheckOutTime(),
                orderPO.getLatestExecutedTime(), orderPO.getRooms().split(";"), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderStatus());
    }

    /**
     * 根据用户ID得到订单列表
     *
     * @param customerID
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrdersByCustomerID(String customerID) throws RemoteException {
        if (customerID.equals(""))
            //若用户ID为空
            return null;
        List<OrderPO> orderPOList = order_dataService_stub.findOrderByCustomerID(customerID);
        if (orderPOList == null || orderPOList.isEmpty())
            //若订单列表为空
            return new ArrayList<OrderVO>();
        else {
            List<OrderVO> orderVOList = new ArrayList<OrderVO>();
            Iterator iterator = orderPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                OrderPO orderPO = (OrderPO) object;
                orderVOList.add(new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                        orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckInTime(),
                        orderPO.getActualCheckInTime(), orderPO.getEstimatedCheckOutTime(), orderPO.getActualCheckOutTime(),
                        orderPO.getLatestExecutedTime(), orderPO.getRooms().split(";"), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                        orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderStatus()));
            }
            return orderVOList;
        }

    }

    /**
     * 根据ID和订单状态得到订单
     *
     * @param customerID
     * @param orderStatus
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByIDAndStatus(String customerID, OrderStatus orderStatus) throws RemoteException {
        List<OrderVO> orderVOList = getOrdersByCustomerID(customerID);
        orderVOList.retainAll(getOrderByStatus(orderStatus));
        return orderVOList;
    }

    /**
     * 根据酒店ID得到该酒店的订单列表
     *
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrdersByHotelID(String hotelID) throws RemoteException {
        if (hotelID.equals(""))
            //若酒店ID为空
            return null;
        List<OrderPO> orderPOList = order_dataService_stub.findOrderByHotelID(hotelID);
        if (orderPOList == null || orderPOList.isEmpty())
            //若酒店无订单
            return new ArrayList<OrderVO>();
        else {
            List<OrderVO> orderVOList = new ArrayList<OrderVO>();
            Iterator iterator = orderPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                OrderPO orderPO = (OrderPO) object;
                orderVOList.add(new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                        orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckInTime(),
                        orderPO.getActualCheckInTime(), orderPO.getEstimatedCheckOutTime(), orderPO.getActualCheckOutTime(),
                        orderPO.getLatestExecutedTime(), orderPO.getRooms().split(";"), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                        orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderStatus()));
            }
            return orderVOList;
        }
    }

    /**
     * 将订单按照时间排序
     *
     * @param list
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> sortByTime(List<OrderVO> list) throws RemoteException {
        Comparator<OrderVO> comparator = new sortOrderByDate();
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * 根据订单状态得到订单
     *
     * @param status
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByStatus(OrderStatus status) throws RemoteException {
        List<OrderPO> orderPOList = order_dataService_stub.findOrderByOrderStatus(status);
        if (orderPOList == null || orderPOList.isEmpty())
            //若订单列表为空
            return new ArrayList<OrderVO>();
        else {
            List<OrderVO> orderVOList = new ArrayList<OrderVO>();
            for(OrderPO orderPO:orderPOList){
                orderVOList.add(new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                        orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckInTime(),
                        orderPO.getActualCheckInTime(), orderPO.getEstimatedCheckOutTime(), orderPO.getActualCheckOutTime(),
                        orderPO.getLatestExecutedTime(), orderPO.getRooms().split(";"), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                        orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderStatus()));
            }
            return orderVOList;
        }
    }


    /**
     * 根据酒店ID得到该酒店不同状态的订单
     *
     * @param hotelID
     * @param status
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByHotelAndStatus(String hotelID, OrderStatus status) throws RemoteException {
        List<OrderVO> orderVOList = getOrdersByHotelID(hotelID);
        if(orderVOList==null||orderVOList.isEmpty())
            return new ArrayList<OrderVO>();
        List<OrderVO> orderVOList2 = new ArrayList<OrderVO>();
        for(OrderVO orderVO:orderVOList){
            if(orderVO.orderType.equals(status))
                orderVOList2.add(orderVO);
        }
        return orderVOList2;
    }

    /**
     * 获得某个人住过的某个酒店的订单的列表
     *
     * @param ID
     * @param hotelID
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByIDAndHotelID(String ID, String hotelID) throws RemoteException {
        List<OrderVO> orderVOList = getOrdersByCustomerID(ID);
        if(orderVOList==null||orderVOList.isEmpty())
            return new ArrayList<OrderVO>();
        List<OrderVO> orderVOList2 = new ArrayList<OrderVO>();
        for(OrderVO orderVO:orderVOList){
            if(orderVO.hotelID.equals(hotelID))
                orderVOList2.add(orderVO);
        }
        return orderVOList2;
    }

    /**
     * 获得某个人住过的某个酒店的某种状态的订单的列表
     *
     * @param ID
     * @param hotelID
     * @param orderStatus
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByIDAndHotelIDAndStatus(String ID, String hotelID, OrderStatus orderStatus) throws RemoteException {
        List<OrderVO> orderVOList = getOrderByHotelAndStatus(hotelID, orderStatus);
        if(orderVOList==null&&orderVOList.isEmpty())
            return new ArrayList<OrderVO>();
        List<OrderVO> orderVOList2 = new ArrayList<OrderVO>();
        for(OrderVO orderVO:orderVOList){
            if(orderVO.orderType.equals(orderStatus))
                orderVOList2.add(orderVO);
        }
        return orderVOList2;
    }

    /**
     * 获得某一天某一种状态的订单
     * @param timestamp
     * @param orderStatus
     * @return
     * @throws RemoteException
     */
    public List<OrderVO> getOrderByStatusAndDate(Timestamp timestamp, OrderStatus orderStatus) throws RemoteException {
        List<OrderVO> list = new ArrayList<OrderVO>();
        for(OrderVO orderVO:getOrderByStatus(orderStatus)){
            if(orderVO.estimatedCheckinTime.getDay()==timestamp.getDay())
                list.add(orderVO);
        }
        return list;
    }
}
