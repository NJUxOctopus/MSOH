package test.businesslogic.marketer_bl;

import businesslogic.marketer_bl.Marketer;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.businesslogic.IP;
import test.businesslogic.TestDriver;
import util.ResultMessage;
import vo.MarketerVO;

import static junit.framework.TestCase.assertEquals;

/**
 * Marketer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class MarketerTest {
    private Marketer marketer;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        marketer = new Marketer();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: deleteMarketer(String marketerID)
     */
    @Test
    public void testDeleteMarketer() throws Exception {
        ResultMessage resultMessage = marketer.deleteMarketer("123");
        assertEquals(ResultMessage.Marketer_MarketerNotExist, resultMessage);
    }

    /**
     * Method: addMarketer(MarketerVO marketerVO)
     */
    @Test
    public void testAddMarketer() throws Exception {
        ResultMessage resultMessage = marketer.addMarketer(new MarketerVO("1", "1", "123", "320581201612102017", ""));
        assertEquals(ResultMessage.Marketer_MarketerAlreadyExist, resultMessage);
    }

    /**
     * Method: changeInfo(MarketerVO marketerVO)
     */
    @Test
    public void testChangeInfo() throws Exception {
        ResultMessage resultMessage = marketer.changeInfo(new MarketerVO("1", "1", "123", "32058120161210", ""));
        assertEquals(ResultMessage.Marketer_MarketerNotExist, resultMessage);
    }

    /**
     * Method: changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)
     */
    @Test
    public void testChangePassword() throws Exception {
        ResultMessage resultMessage1 = marketer.changePassword("320581201612102017", "123456", "123123", "123123");
        assertEquals(resultMessage1, ResultMessage.ChangePasswordSuccess);
        marketer.changePassword("320581201612102017", "123123", "123456", "123456");
    }


} 
