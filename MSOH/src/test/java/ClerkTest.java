import businesslogic.clerk_bl.Clerk;
import junit.framework.TestCase;
import util.ResultMessage;
import vo.ClerkVO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/19.
 */
public class ClerkTest extends TestCase {

    public void testAddClerk() throws RemoteException {
        Clerk clerk = new Clerk();
        Image image = new ImageIcon().getImage();
        ResultMessage resultMessage1 = clerk.addClerk(new ClerkVO("pxr", "13333333333", "151250117", "320200000000000000", image,
                "RUJIA", "123"));
        ResultMessage resultMessage2 = clerk.addClerk(new ClerkVO("", "13333333333", "151250117", "320200000000000000", image,
                "RUJIA", "123"));
        ResultMessage resultMessage3 = clerk.addClerk(new ClerkVO("pxr", "13333333333", "151250117", "320200000000000001", image,
                "RUJIA", "123"));
        assertEquals(resultMessage1,ResultMessage.Clerk_AddClerkExist);
        assertEquals(resultMessage2,ResultMessage.Blank);
        assertEquals(resultMessage3,ResultMessage.Clerk_AddClerkSuccess);
    }

    public void testChangeInfo()throws RemoteException{
        Clerk clerk = new Clerk();
        Image image = new ImageIcon().getImage();
        ResultMessage resultMessage1 = clerk.changeInfo(new ClerkVO("pxr2", "13333333333", "151250117", "320200000000000000", image,
                "RUJIA", "123"));
        ResultMessage resultMessage2 = clerk.changeInfo(new ClerkVO("", "13333333333", "151250117", "320200000000000000", image,
                "RUJIA", "123"));
        assertEquals(resultMessage1,ResultMessage.ChangeInfoSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
    }

    public void testDeleteClerk()throws RemoteException{
        Clerk clerk = new Clerk();
        Image image = new ImageIcon().getImage();
        ResultMessage resultMessage1 = clerk.deleteClerk(new ClerkVO("pxr2", "13333333333", "151250117", "320200000000000000", image,
                "RUJIA", "123"));
        ResultMessage resultMessage2 = clerk.changeInfo(new ClerkVO("pxr2", "13333333333", "151250117", "320200000000000001", image,
                "RUJIA", "123"));
        assertEquals(resultMessage1,ResultMessage.Clerk_DeleteClerkSuccess);
        assertEquals(resultMessage2,ResultMessage.Clerk_ClerkNotExist);
    }

    public void testChangePassword()throws RemoteException{
        Clerk clerk = new Clerk();
        Image image = new ImageIcon().getImage();
        ResultMessage resultMessage1 = clerk.changePassword("320200000000000000","123456","1234567","1234567");
        ResultMessage resultMessage2 = clerk.changePassword("320200000000000000","12345","1234567","1234567");
        ResultMessage resultMessage3 = clerk.changePassword("320200000000000000","123456","1234567","123456");
        ResultMessage resultMessage4 = clerk.changePassword("","123456","1234567","1234567");

        assertEquals(resultMessage1,ResultMessage.ChangePasswordSuccess);
        assertEquals(resultMessage2,ResultMessage.ChangePasswordWrongOldPw);
        assertEquals(resultMessage3,ResultMessage.ChangePassword2DifferentNew);
        assertEquals(resultMessage4,ResultMessage.Blank);
    }
}
