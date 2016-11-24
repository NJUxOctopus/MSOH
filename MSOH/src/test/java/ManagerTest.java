import businesslogic.manager_bl.Manager;
import junit.framework.TestCase;
import util.ResultMessage;
import vo.ManagerVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/23.
 */
public class ManagerTest extends TestCase {
    Manager manager = new Manager();

    public void testChangeInfo()throws RemoteException{
        ResultMessage resultMessage1 = manager.changeInfo(new ManagerVO("pxr","12345678910","123456","320200000000000000",null));
        ResultMessage resultMessage2 = manager.changeInfo(new ManagerVO("pxr","12345678910","123456","320200000000000001",null));
        ResultMessage resultMessage3 = manager.changeInfo(new ManagerVO("","12345678910","123456","320200000000000000",null));
        assertEquals(ResultMessage.Manager_ChangeManagerInfoSuccess,resultMessage1);
        assertEquals(ResultMessage.Manager_ManagerNotExist,resultMessage2);
        assertEquals(ResultMessage.Blank,resultMessage3);
    }

    public void testChangePassword()throws RemoteException{
        ResultMessage resultMessage1 = manager.changePassword("320200000000000000","123456","1234567","1234567");
        ResultMessage resultMessage2 = manager.changePassword("320200000000000000","12345","1234567","1234567");
        ResultMessage resultMessage3 = manager.changePassword("320200000000000000","123456","1234567","123456");
        ResultMessage resultMessage4 = manager.changePassword("","123456","1234567","1234567");
        ResultMessage resultMessage5 = manager.changePassword("320200000000000001","123456","1234567","1234567");
        ResultMessage resultMessage6 = manager.changePassword("320200000000000000","123456","1234","1234");

        assertEquals(resultMessage1,ResultMessage.ChangePasswordSuccess);
        assertEquals(resultMessage2,ResultMessage.ChangePasswordWrongOldPw);
        assertEquals(resultMessage3,ResultMessage.ChangePassword2DifferentNew);
        assertEquals(resultMessage4,ResultMessage.Blank);
        assertEquals(resultMessage5,ResultMessage.Manager_ManagerNotExist);
        assertEquals(resultMessage6,ResultMessage.DataFormatWrong);
    }
}
