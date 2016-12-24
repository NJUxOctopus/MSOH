package test.businesslogic.manager_bl;

import businesslogic.manager_bl.Manager;
import businesslogic.manager_bl.ManagerUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.businesslogic.IP;
import test.businesslogic.TestDriver;
import vo.ManagerVO;

import static junit.framework.TestCase.assertEquals;

/**
 * ManagerUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class ManagerUtilTest {
    private ManagerUtil managerUtil;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        managerUtil = new ManagerUtil();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getByID(String managerID)
     */
    @Test
    public void testGetByID() throws Exception {
        ManagerVO managerVO = managerUtil.getByID("320522201612072017");
        assertEquals("320522201612072017", managerVO.ID);
    }


} 
