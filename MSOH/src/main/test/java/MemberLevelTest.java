

import businesslogic.member_bl.MemberLevel;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vo.MemberLevelVO;

import static junit.framework.TestCase.assertEquals;

/**
 * MemberLevel Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class MemberLevelTest {
    private MemberLevel memberLevel;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        memberLevel = new MemberLevel();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getMemberLevel()
     */
    @Test
    public void testGetMemberLevel() throws Exception {
        MemberLevelVO memberLevelVO = memberLevel.getMemberLevel();
        assertEquals(11, memberLevelVO.num);
    }


} 
