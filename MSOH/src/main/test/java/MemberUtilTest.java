

import businesslogic.member_bl.MemberUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vo.MemberVO;

import static junit.framework.TestCase.assertEquals;

/**
 * MemberUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class MemberUtilTest {
    private MemberUtil memberUtil;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        memberUtil = new MemberUtil();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getSingle(String customerID)
     */
    @Test
    public void testGetSingle() throws Exception {
        MemberVO memberVO = memberUtil.getSingle("320581199704044040");
        assertEquals("320581199704044040", memberVO.ID);
    }


} 
