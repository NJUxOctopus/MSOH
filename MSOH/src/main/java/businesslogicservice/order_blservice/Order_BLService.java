package businesslogicservice.order_blservice;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

import util.OrderStatus;
import util.ResultMessage;
import vo.*;

public interface Order_BLService {
    public ResultMessage createOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage cancelOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage executeOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage endOrder(OrderVO orderVO) throws RemoteException;

    public ResultMessage setAbnormal(String orderID) throws RemoteException;

    public ResultMessage renewOrder(OrderVO orderVO) throws RemoteException;

    public double getTotal(OrderVO orderVO) throws RemoteException;

    public List<OrderPriceVO> usePromotion(OrderVO orderVO)throws IOException,ClassNotFoundException;

    public OrderPriceVO getLowestPrice(List<OrderPriceVO> orderPriceVOs)throws RemoteException;

    public void examineAbnormal(String orderID,Timestamp timestamp)throws RemoteException;

}
