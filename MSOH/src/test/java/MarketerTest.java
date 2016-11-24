import businesslogic.marketer_bl.Marketer;
import junit.framework.TestCase;
import util.ResultMessage;
import vo.MarketerVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/11/22.
 */
public class MarketerTest extends TestCase {
    Marketer marketer = new Marketer();
    public void testDeleteMarketer()throws RemoteException{
        ResultMessage resultMessage1 = marketer.deleteMarketer("1234");
        ResultMessage resultMessage2 = marketer.deleteMarketer("123");
        assertEquals(resultMessage1,ResultMessage.Marketer_DeleteMarketerSuccess);
        assertEquals(resultMessage2,ResultMessage.Marketer_MarketerNotExist);
    }

    public void testAddMarketer()throws RemoteException{
        ResultMessage resultMessage1 = marketer.addMarketer(new MarketerVO("pxr","12345678910","123456","123",null));
        ResultMessage resultMessage2 = marketer.addMarketer(new MarketerVO("pxr","12345678910","123456","1234",null));
        ResultMessage resultMessage3 = marketer.addMarketer(new MarketerVO("","12345678910","123456","123",null));
        ResultMessage resultMessage4 = marketer.addMarketer(new MarketerVO("pxr","12345678910","1234","123",null));
        assertEquals(resultMessage1,ResultMessage.Marketer_AddMarketerSuccess);
        assertEquals(resultMessage2,ResultMessage.Marketer_MarketerAlreadyExist);
        assertEquals(resultMessage3,ResultMessage.Blank);
        assertEquals(resultMessage4,ResultMessage.DataFormatWrong);
    }

    public void testChangeInfo()throws RemoteException{
        ResultMessage resultMessage1 = marketer.changeInfo(new MarketerVO("zqh","12345678910","123456","1234",null));
        ResultMessage resultMessage2 = marketer.changeInfo(new MarketerVO("","12345678910","123456","1234",null));
        ResultMessage resultMessage3 = marketer.changeInfo(new MarketerVO("zqh","12345678910","123456","123",null));
        assertEquals(resultMessage1,ResultMessage.Marketer_ChangeInfoSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
        assertEquals(resultMessage3,ResultMessage.Marketer_MarketerNotExist);
    }

    public void testChangePassword()throws RemoteException{
        ResultMessage resultMessage1 = marketer.changePassword("1234","123456","1234567","1234567");
        ResultMessage resultMessage2 = marketer.changePassword("1234","12345","1234567","1234567");
        ResultMessage resultMessage3 = marketer.changePassword("1234","123456","1234567","123456");
        ResultMessage resultMessage4 = marketer.changePassword("","123456","1234567","1234567");
        ResultMessage resultMessage5 = marketer.changePassword("12345","123456","1234567","1234567");

        assertEquals(resultMessage1,ResultMessage.ChangePasswordSuccess);
        assertEquals(resultMessage2,ResultMessage.ChangePasswordWrongOldPw);
        assertEquals(resultMessage3,ResultMessage.ChangePassword2DifferentNew);
        assertEquals(resultMessage4,ResultMessage.Blank);
        assertEquals(resultMessage5,ResultMessage.Marketer_MarketerNotExist);
    }
}
