import businesslogic.member_bl.MemberLevel;
import junit.framework.TestCase;
import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by Pxr on 16/11/19.
 */
public class MemberLevelTest extends TestCase {
    MemberLevel memberLevel = new MemberLevel();
    public void addMemberLevelTest()throws RemoteException{
        int a[] = {10,20,30};
        ResultMessage resultMessage1 = memberLevel.addMemberLevel(new MemberLevelVO("pxr",new Date(2016,11,19),3,a));
        ResultMessage resultMessage2 = memberLevel.addMemberLevel(new MemberLevelVO("pxr",new Date(2016,11,19),3,null));
        ResultMessage resultMessage3 = memberLevel.addMemberLevel(new MemberLevelVO("pxr",new Date(2016,11,19),-1,a));
        assertEquals(resultMessage1,ResultMessage.MemberLevel_AddMemberLevelSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
        assertEquals(resultMessage3,null);
    }

    public void modifyMemberLevelTest()throws RemoteException{
        int a[] = {10,20,30};
        ResultMessage resultMessage1 = memberLevel.modifyMemberLevel(new MemberLevelVO("pxr",new Date(2016,11,19),3,a));
        ResultMessage resultMessage2 = memberLevel.modifyMemberLevel(new MemberLevelVO("pxr",new Date(2016,11,19),3,null));
        ResultMessage resultMessage3 = memberLevel.modifyMemberLevel(new MemberLevelVO("pxr",new Date(2016,11,19),-1,a));
        assertEquals(resultMessage1,ResultMessage.MemberLevel_ModifyMemberLevelSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
        assertEquals(resultMessage3,null);
    }
}
