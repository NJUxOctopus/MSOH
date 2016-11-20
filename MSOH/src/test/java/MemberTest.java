import businesslogic.member_bl.Member;
import junit.framework.TestCase;
import util.MemberType;
import util.ResultMessage;
import vo.MemberVO;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by Pxr on 16/11/19.
 */
public class MemberTest extends TestCase {
    Member member = new Member();

    public void signUpTest() throws RemoteException {
        ResultMessage resultMessage1 = member.signUp(new MemberVO("320200000000000000", MemberType.ENTREPRISE, 1, null,
                "NJU"), "320200000000000000");
        ResultMessage resultMessage2 = member.signUp(new MemberVO("320200000000000000", MemberType.ENTREPRISE, 1, null,
                ""), "320200000000000000");
        ResultMessage resultMessage3 = member.signUp(new MemberVO("320200000000000000", MemberType.NORMAL, 1, new Date(1997,8,10),
                ""), "320200000000000000");
        ResultMessage resultMessage4 = member.signUp(new MemberVO("320200000000000000", MemberType.NORMAL, 1, null,
                ""), "320200000000000000");
        ResultMessage resultMessage5 = member.signUp(new MemberVO("320200000000000000", MemberType.ENTREPRISE, 1, null,
                "NJU"), "12345678");
        assertEquals(resultMessage1,ResultMessage.Member_EnterpriseSignupSuccess);
        assertEquals(resultMessage2,ResultMessage.Blank);
        assertEquals(resultMessage3,ResultMessage.Member_NormalSignupSuccess);
        assertEquals(resultMessage4,ResultMessage.Blank);
        assertEquals(resultMessage5,ResultMessage.Member_AddMemberAlreadyExist);
    }
}
