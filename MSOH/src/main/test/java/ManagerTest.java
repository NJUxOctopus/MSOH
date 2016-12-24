

import businesslogic.manager_bl.Manager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import util.ResultMessage;
import vo.ManagerVO;

import static junit.framework.TestCase.assertEquals;

/**
 * Manager Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class ManagerTest {
    private Manager manager;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        manager = new Manager();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: changeInfo(ManagerVO managerVO)
     */
    @Test
    public void testChangeInfo() throws Exception {
        ResultMessage resultMessage1 = manager.changeInfo(new ManagerVO("1", "1", "1", "20522201612072016", ""));//不存在该工作人员
        assertEquals(ResultMessage.Manager_ManagerNotExist, resultMessage1);
    }

    /**
     * Method: changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)
     */
    @Test
    public void testChangePassword() throws Exception {
        ResultMessage resultMessage1 = manager.changePassword("320522201612072017", "123123", "123456", "123456");
        assertEquals(resultMessage1, ResultMessage.ChangePasswordSuccess);
        manager.changePassword("320581199701010006", "123456", "123123", "123123");
    }


} 
