

import businesslogic.order_bl.Order;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import util.OrderStatus;
import util.ResultMessage;

import static junit.framework.TestCase.assertEquals;

/**
 * Order Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class OrderTest {
    private Order order;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        order = new Order();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: changeOrderStatus(String orderID, OrderStatus orderStatus)
     */
    @Test
    public void testChangeOrderStatus() throws Exception {
        ResultMessage resultMessage = order.changeOrderStatus("123", OrderStatus.ABNORMAL);
        assertEquals(ResultMessage.Order_notExist, resultMessage);
    }


} 
