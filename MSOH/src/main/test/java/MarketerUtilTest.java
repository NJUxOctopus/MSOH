

import businesslogic.marketer_bl.Marketer;
import businesslogic.marketer_bl.MarketerUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vo.MarketerVO;

import static junit.framework.TestCase.assertEquals;

/**
 * MarketerUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class MarketerUtilTest {
    private MarketerUtil marketerUtil;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        marketerUtil = new MarketerUtil();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getSingle(String marketerID)
     */
    @Test
    public void testGetSingle() throws Exception {
        MarketerVO marketerVO = marketerUtil.getSingle("320581201612102017");
        assertEquals("320581201612102017", marketerVO.ID);
    }

    @Test
    public void testGetByName()throws Exception{
        MarketerVO marketerVO = marketerUtil.getByName("孙销").get(0);
        assertEquals("320581201612102017",marketerVO.ID);
    }
} 
