

import businesslogic.order_bl.OrderUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import util.OrderStatus;

import static junit.framework.TestCase.assertEquals;

/**
 * OrderUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class OrderUtilTest {
    private OrderUtil orderUtil;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        orderUtil = new OrderUtil();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getSingle(String orderID)
     */
    @Test
    public void testGetSingle() throws Exception {
        assertEquals("南京古南都饭店", orderUtil.getSingle("201701010001").hotelName);
    }

    /**
     * Method: getOrdersByCustomerID(String customerID)
     */
    @Test
    public void testGetOrdersByCustomerID() throws Exception {
        assertEquals(null, orderUtil.getOrdersByCustomerID("3202811997"));
    }

    /**
     * Method: getOrderByIDAndStatus(String customerID, OrderStatus orderStatus)
     */
    @Test
    public void testGetOrderByIDAndStatus() throws Exception {
        assertEquals(0, orderUtil.getOrderByIDAndStatus("", OrderStatus.ABNORMAL).size());
    }

    /**
     * Method: getOrdersByHotelID(String hotelID)
     */
    @Test
    public void testGetOrdersByHotelID() throws Exception {
        assertEquals(0, orderUtil.getOrdersByHotelID("10000004").size());
    }

    /**
     * Method: getOrderByHotelAndStatus(String hotelID, OrderStatus status)
     */
    @Test
    public void testGetOrderByHotelAndStatus() throws Exception {
        assertEquals(0, orderUtil.getOrderByHotelAndStatus("10000004", OrderStatus.ABNORMAL).size());
    }

} 
