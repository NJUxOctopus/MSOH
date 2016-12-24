package test.businesslogic.clerk_bl;

import businesslogic.clerk_bl.Clerk;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import rmi.RemoteHelper;
import test.businesslogic.IP;
import test.businesslogic.TestDriver;
import util.ResultMessage;
import vo.ClerkVO;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static junit.framework.TestCase.assertEquals;

/**
 * Clerk Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class ClerkTest {
    private Clerk clerk;

    @Before
    public void before() throws Exception {
        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        clerk = new Clerk();

    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addClerk(ClerkVO clerkVO)
     */
    @Test
    public void testAddClerk() throws Exception {
        ResultMessage resultMessage1 = clerk.addClerk(new ClerkVO("pxr", "12345678910", "123456", "320581199701010006",
                "Rujia", "10000004", ""));//已存在的ID

        assertEquals(ResultMessage.Blank, resultMessage1);
    }

    /**
     * Method: changeInfo(ClerkVO clerkVO)
     */
    @Test
    public void testChangeInfo() throws Exception {
        ResultMessage resultMessage1 = clerk.changeInfo(new ClerkVO("pxr", "12345678910", "123456", "320581199701010008",
                "Rujia", "10000004", ""));//不存在该工作人员
        assertEquals(ResultMessage.Clerk_ClerkNotExist, resultMessage1);
    }


    /**
     * Method: changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)
     */
    @Test
    public void testChangePassword() throws Exception {
        ResultMessage resultMessage1 = clerk.changePassword("320581199701010006", "ljr2016", "123456", "123456");
        assertEquals(resultMessage1, ResultMessage.ChangePasswordSuccess);
        clerk.changePassword("320581199701010006", "123456", "ljr2016", "ljr2016");
    }


} 
