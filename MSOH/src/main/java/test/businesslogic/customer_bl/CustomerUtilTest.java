package test.businesslogic.customer_bl;

import businesslogic.customer_bl.CustomerUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.businesslogic.IP;
import test.businesslogic.TestDriver;
import vo.CustomerVO;

import static junit.framework.TestCase.assertEquals;

/**
 * CustomerUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class CustomerUtilTest {
    private CustomerUtil customerUtil;

    @Before
    public void before() throws Exception {
        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        customerUtil = new CustomerUtil();
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: getSingle(String ID)
     */
    @Test
    public void testGetSingle() throws Exception {
        CustomerVO customerVO = customerUtil.getSingle("320581199704044040");
        assertEquals("320581199704044040", customerVO.ID);
    }



} 
