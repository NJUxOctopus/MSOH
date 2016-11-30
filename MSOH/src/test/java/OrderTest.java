import businesslogic.order_bl.Order;
import junit.framework.TestCase;
import util.OrderStatus;
import util.ResultMessage;
import vo.OrderVO;
import vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pxr on 16/11/20.
 */
public class OrderTest extends TestCase {
    Order order = new Order();

    public void testCreateOrder()throws RemoteException{
        List<String> roomList = new ArrayList<String>();
        roomList.add("dachuang");
        ResultMessage resultMessage1 = order.createOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Timestamp(System.currentTimeMillis()), null, new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), (String[]) roomList.toArray(), 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        ResultMessage resultMessage2 = order.createOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", null, null, new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(resultMessage1,ResultMessage.Order_CreateOrderSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
    }

    public void testCancelOrder()throws RemoteException{
        ResultMessage resultMessage1 = order.cancelOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Timestamp(System.currentTimeMillis()), null, new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(resultMessage1,ResultMessage.Order_CancelOrderSuccess);
    }

    public void testExecuteOrder()throws RemoteException{
        ResultMessage resultMessage1 = order.executeOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        ResultMessage resultMessage2 = order.executeOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Timestamp(System.currentTimeMillis()), null, new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(resultMessage1,ResultMessage.Order_ExecuteOrderSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
    }

    public void testEndOrder()throws RemoteException{
        ResultMessage resultMessage1 = order.endOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.EXECUTED));
        ResultMessage resultMessage2 = order.endOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138",new Timestamp(System.currentTimeMillis()), null, new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.EXECUTED));
        assertEquals(resultMessage1,ResultMessage.Order_EndOrderSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
    }

    public void testSetAbnormal()throws RemoteException{
        ResultMessage resultMessage1 = order.setAbnormal(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Timestamp(System.currentTimeMillis()), null,new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(resultMessage1,ResultMessage.Order_SetAbnormalSuccess);
    }

    public void testRenewOrder()throws RemoteException{
        ResultMessage resultMessage1 = order.renewOrder(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Timestamp(System.currentTimeMillis()), null, new Timestamp(System.currentTimeMillis()), null,
                new Timestamp(System.currentTimeMillis()), null, 2, false, null, null,250, 200, OrderStatus.ABNORMAL));
        assertEquals(resultMessage1,ResultMessage.Order_RenewOrderSuccess);
    }

}
