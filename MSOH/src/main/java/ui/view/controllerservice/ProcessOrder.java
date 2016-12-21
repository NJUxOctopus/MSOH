package ui.view.controllerservice;

import util.OrderStatus;
import util.ResultMessage;
import vo.OrderPriceVO;
import vo.OrderVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public interface ProcessOrder {

    public ResultMessage createOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage createOrderOffline(OrderVO orderVO) throws RemoteException;

    public ResultMessage cancelOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage renewOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage executeOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage endOrder(OrderVO orderVO) throws RemoteException;

    public List<OrderVO> getOrderByStatus(OrderStatus status) throws RemoteException;

    public List<OrderVO> getOrderByCustomerID(String customerID) throws RemoteException;

    public List<OrderVO> getOrderByIDAndStatus(String customerID, OrderStatus orderStatus) throws RemoteException;

    public List<OrderVO> getOrderByHotelAndStatus(String hotelID, OrderStatus status) throws RemoteException;

    public List<OrderVO> sortByTime(List<OrderVO> list) throws RemoteException;

    public OrderVO getSingle(String ID) throws RemoteException;

    public List<OrderVO> getOrderByIDAndHotelIDAndStatus(String ID, String hotelID, OrderStatus orderStatus) throws RemoteException;

    public List<OrderVO> getOrderByIDAndHotelID(String ID, String hotelID) throws RemoteException;

    public List<OrderVO> getOrderByStatusAndDate(Timestamp timestamp, OrderStatus orderStatus) throws RemoteException;

    public List<OrderPriceVO> usePromotion(OrderVO orderVO) throws IOException, ClassNotFoundException;

    public OrderPriceVO getLowestPrice(List<OrderPriceVO> orderPriceVOs) throws RemoteException ;

}
