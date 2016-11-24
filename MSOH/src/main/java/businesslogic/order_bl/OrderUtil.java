package businesslogic.order_bl;

import businesslogicservice.orderUtil_blservice.OrderUtil_BLService;
import dataservice.order_dataservice.Order_DataService_Stub;
import rmi.RemoteHelper;
import util.OrderStatus;
import vo.OrderVO;
import po.OrderPO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class OrderUtil implements OrderUtil_BLService {
    Order_DataService_Stub order_dataService_stub = new Order_DataService_Stub();

    public OrderVO getSingle(String orderID) throws RemoteException {
        if(orderID.equals(""))
            return null;
        OrderPO orderPO =order_dataService_stub.getOrderByOrderID(orderID);
        if(orderPO==null)
            return null;
        return new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckinTime(),
                orderPO.getActualCheckinTime(), orderPO.getEstimatedCheckoutTime(), orderPO.getActualCheckoutTime(),
                orderPO.getLatestExecutedTime(), orderPO.getRooms(), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderType());
    }

    public List<OrderVO> getOrdersByCustomerID(String customerID) throws RemoteException {
        if(customerID.equals(""))
            return null;
        List<OrderPO> orderPOList = order_dataService_stub.findOrderByCustomerID(customerID);
        if (orderPOList == null)
            return null;
        else {
            List<OrderVO> orderVOList = new ArrayList<OrderVO>();
            Iterator iterator = orderPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                OrderPO orderPO = (OrderPO) object;
                orderVOList.add(new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                        orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckinTime(),
                        orderPO.getActualCheckinTime(), orderPO.getEstimatedCheckoutTime(), orderPO.getActualCheckoutTime(),
                        orderPO.getLatestExecutedTime(), orderPO.getRooms(), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                        orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderType()));
            }
            return orderVOList;
        }

    }

    public List<OrderVO> getOrderByIDAndStatus(String customerID, OrderStatus orderStatus) throws RemoteException {
        if(customerID.equals(""))
            return null;
        List<OrderPO> orderPOList = order_dataService_stub.findOrderByCustomerID(customerID);
        if (orderPOList == null)
            return null;
        else {
            List<OrderVO> orderVOList = new ArrayList<OrderVO>();
            Iterator iterator = orderPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                OrderPO orderPO = (OrderPO) object;
                if (orderPO.getOrderType().equals(orderStatus))
                    orderVOList.add(new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                            orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckinTime(),
                            orderPO.getActualCheckinTime(), orderPO.getEstimatedCheckoutTime(), orderPO.getActualCheckoutTime(),
                            orderPO.getLatestExecutedTime(), orderPO.getRooms(), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                            orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderType()));
            }
            if(orderVOList.isEmpty())
                return null;
            return orderVOList;
        }
    }

    public List<OrderVO> getOrdersByHotelID(String hotelID) throws RemoteException {
        if(hotelID.equals(""))
            return null;
        List<OrderPO> orderPOList = order_dataService_stub.findOrderByHotelID(hotelID);
        if (orderPOList == null)
            return null;
        else {
            List<OrderVO> orderVOList = new ArrayList<OrderVO>();
            Iterator iterator = orderPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                OrderPO orderPO = (OrderPO) object;
                orderVOList.add(new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                        orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckinTime(),
                        orderPO.getActualCheckinTime(), orderPO.getEstimatedCheckoutTime(), orderPO.getActualCheckoutTime(),
                        orderPO.getLatestExecutedTime(), orderPO.getRooms(), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                        orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderType()));
            }
            return orderVOList;
        }
    }

    public List<OrderVO> sortByTime(List<OrderVO> list) throws RemoteException {
        return null;
    }

    public List<OrderVO> getOrderByStatus(OrderStatus status) throws RemoteException {
        List<OrderPO> orderPOList = order_dataService_stub.findOrderByOrderStatus(status);
        if (orderPOList == null)
            return null;
        else {
            List<OrderVO> orderVOList = new ArrayList<OrderVO>();
            Iterator iterator = orderPOList.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                OrderPO orderPO = (OrderPO) object;
                orderVOList.add(new OrderVO(orderPO.getCustomerName(), orderPO.getPhone(), orderPO.getCustomerID(), orderPO.getHotelID(),
                        orderPO.getHotelName(), orderPO.getOrderID(), orderPO.getEstimatedCheckinTime(),
                        orderPO.getActualCheckinTime(), orderPO.getEstimatedCheckoutTime(), orderPO.getActualCheckoutTime(),
                        orderPO.getLatestExecutedTime(), orderPO.getRooms(), orderPO.getNumOfCustomers(), orderPO.isHaveChildren(),
                        orderPO.getRemarks(), orderPO.getPromotionName(), orderPO.getInitialPrice(), orderPO.getFinalPrice(), orderPO.getOrderType()));
            }
            return orderVOList;
        }
    }
}
