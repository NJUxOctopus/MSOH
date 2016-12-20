package businesslogicservice.order_blservice;

import util.OrderStatus;
import util.ResultMessage;
import vo.OrderPriceVO;
import vo.OrderVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public interface Order_BLService {
    public ResultMessage createOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage createOrderOffline(OrderVO orderVO) throws RemoteException;

    public ResultMessage cancelOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage executeOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage endOrder(OrderVO orderVO) throws RemoteException;

//    public ResultMessage setAbnormal(String orderID) throws RemoteException;

    public ResultMessage renewOrder(OrderVO orderVO) throws RemoteException;

    public double getTotal(OrderVO orderVO) throws RemoteException;

    public List<OrderPriceVO> usePromotion(OrderVO orderVO) throws IOException, ClassNotFoundException;

    public OrderPriceVO getLowestPrice(List<OrderPriceVO> orderPriceVOs) throws RemoteException;

//    public void examineAbnormal(String orderID,Timestamp timestamp)throws RemoteException;

    public ResultMessage changeOrderStatus(String orderID, OrderStatus orderStatus) throws RemoteException;

}
