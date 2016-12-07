package util.strategy;

import vo.OrderPriceVO;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/12/7.
 */
public interface Strategy {
    public Boolean usePromotion(OrderVO orderVO)throws RemoteException;
}
