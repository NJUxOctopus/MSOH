import businesslogic.order_bl.OrderUtil;
import junit.framework.TestCase;
import util.OrderStatus;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pxr on 16/11/20.
 */
public class OrderUtilTest extends TestCase {

    OrderUtil orderUtil = new OrderUtil();

    public void getSingleTest()throws RemoteException{
        OrderVO orderVO1= new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
                new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED);
        assertEquals(orderVO1,orderUtil.getSingle("12138"));
        assertEquals(null,orderUtil.getSingle("12139"));
    }
    public void getOrdersByCustomerIDTest()throws RemoteException{
        List<OrderVO> orderVOList = new ArrayList<OrderVO>();
        orderVOList.add(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
                new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(orderVOList,orderUtil.getOrdersByCustomerID("320200000000000000"));
        assertEquals(null,orderUtil.getOrdersByCustomerID("123456"));
    }

    public void getOrderByIDAndStatusTest()throws RemoteException{
        List<OrderVO> orderVOList = new ArrayList<OrderVO>();
        orderVOList.add(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
                new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(orderVOList,orderUtil.getOrderByIDAndStatus("320200000000000000",OrderStatus.UNEXECUTED));
        assertEquals(null,orderUtil.getOrderByIDAndStatus("320200000000000000",OrderStatus.ABNORMAL));
        assertEquals(null,orderUtil.getOrderByIDAndStatus("123456",OrderStatus.ABNORMAL));
    }

    public void getOrdersByHotelIDTest()throws RemoteException{
        List<OrderVO> orderVOList = new ArrayList<OrderVO>();
        orderVOList.add(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
                new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(orderVOList,orderUtil.getOrdersByHotelID("123"));
        assertEquals(null,orderUtil.getOrdersByHotelID("234"));
    }

    public void sortBytimeTest()throws RemoteException{
        //TODO
    }

    public void getOrderByStatus()throws RemoteException{
        List<OrderVO> orderVOList = new ArrayList<OrderVO>();
        orderVOList.add(new OrderVO("pxr", "12345678910", "320200000000000000", "123",
                "RUJIA", "12138", new Date(2016,11,20), null, new Date(2016,11,21), null,
                new Date(2016,11,20), null, 2, false, null, null,250, 200, OrderStatus.UNEXECUTED));
        assertEquals(orderVOList,orderUtil.getOrderByStatus(OrderStatus.UNEXECUTED));
        assertEquals(null,orderUtil.getOrderByStatus(OrderStatus.ABNORMAL));
    }
}
