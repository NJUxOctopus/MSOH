import businesslogic.clerk_bl.Clerk;
import junit.framework.TestCase;
import util.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/19.
 */
public class ClerkTest extends TestCase {


    public static void testChangePassword() throws RemoteException {
        Clerk clerk = new Clerk();
        ResultMessage resultMessage1 = clerk.changePassword("320581199701010006", "ljr2016", "123456", "123456");
        assertEquals(resultMessage1, ResultMessage.ChangePasswordSuccess);
        clerk.changePassword("320581199701010006", "123456", "ljr2016", "ljr2016");
    }
}
