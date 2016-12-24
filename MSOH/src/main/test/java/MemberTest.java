

import businesslogic.member_bl.Member;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import util.MemberType;
import util.ResultMessage;
import vo.MemberVO;

import static junit.framework.TestCase.assertEquals;

/**
 * Member Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class MemberTest {
    private Member member;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        member = new Member();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: signUp(MemberVO memberVO)
     */
    @Test
    public void testSignUp() throws Exception {
        ResultMessage resultMessage = member.signUp(new MemberVO("320581199704044040", MemberType.ENTREPRISE, 1, null, ""));
        assertEquals(ResultMessage.Member_AddMemberAlreadyExist, resultMessage);
    }


} 
