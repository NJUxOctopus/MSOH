package businesslogicservice.order_blservice;

import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface OrderUtil_BLService {
    public OrderVO getSingle(String ID) throws RemoteException;

    public List<OrderVO> getOrdersByCustomerID(String customerID) throws RemoteException;

    public List<OrderVO> getOrdersByHotelID(String hotelID) throws RemoteException;

    public List<OrderVO> sortByTime(List<OrderVO> list) throws RemoteException;

    public List<OrderVO> getOrderByStatus(OrderStatus status) throws RemoteException;

    public List<OrderVO> getOrderByIDAndStatus(String customerID, OrderStatus orderStatus) throws RemoteException;

    public List<OrderVO> getOrderByHotelAndStatus(String hotelID, OrderStatus status) throws RemoteException;

    public List<OrderVO> getOrderByIDAndHotelID(String ID, String hotelID) throws RemoteException;

    public List<OrderVO> getOrderByIDAndHotelIDAndStatus(String ID, String hotelID, OrderStatus orderStatus) throws RemoteException;

    public List<OrderVO> getOrderByStatusAndDate(Timestamp timestamp, OrderStatus orderStatus) throws RemoteException;
}
