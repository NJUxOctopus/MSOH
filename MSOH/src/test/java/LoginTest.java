import businesslogic.login_bl.Login;
import junit.framework.TestCase;
import util.ResultMessage;

import java.rmi.RemoteException;


/**
 * Created by pxr on 2016/11/13.
 */
public class LoginTest extends TestCase{

    public void testLogin()throws RemoteException{
        Login login = new Login();
        ResultMessage resultMessage1 = login.login("320200000000000000","123456");
        ResultMessage resultMessage2 = login.login("","123456");
        ResultMessage resultMessage3 = login.login("320200000000000000","");
        ResultMessage resultMessage4 = login.login("320200000000000000","12345");
        ResultMessage resultMessage5 = login.login("320200000000000001","123456");
        ResultMessage resultMessage6 = login.login("1234","123456");
        assertEquals(resultMessage1,ResultMessage.Login_ClerkSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
        assertEquals(resultMessage3,ResultMessage.Blank);
        assertEquals(resultMessage4,ResultMessage.Login_WrongPassword);
        assertEquals(resultMessage5,ResultMessage.Login_NoUser);
        assertEquals(resultMessage6,ResultMessage.Login_MarketerSuccess);
    }

}
