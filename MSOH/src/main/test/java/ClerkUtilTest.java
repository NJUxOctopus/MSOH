

import businesslogic.clerk_bl.ClerkUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import vo.ClerkVO;

import static junit.framework.TestCase.assertEquals;

/** 
* ClerkUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>十二月 24, 2016</pre> 
* @version 1.0 
*/ 
public class ClerkUtilTest {
    private ClerkUtil clerkUtil;

@Before
public void before() throws Exception {
    TestDriver testDriver = new TestDriver(IP.ip);
    testDriver.linkServer();
    clerkUtil = new ClerkUtil();

} 

@After
public void after() throws Exception { 
} 


/** 
* 
* Method: getSingle(String ID) 
* 
*/ 
@Test
public void testGetSingle() throws Exception {
    ClerkVO clerkVO = clerkUtil.getSingle("320581199701010006");
    assertEquals("ljr2016", clerkVO.password);
} 




} 
