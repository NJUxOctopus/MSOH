

import businesslogic.promotion_bl.PromotionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import vo.PromotionVO;

/**
 * PromotionUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class PromotionUtilTest {
    private PromotionUtil promotionUtil;

    @Before
    public void before() throws Exception {
        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        promotionUtil = new PromotionUtil();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getSingle(String promotionID)
     */
    @Test
    public void testGetSingle() throws Exception {
        PromotionVO promotionVO = promotionUtil.getSingle("28");
        Assert.assertEquals("VIPPPP！",promotionVO.promotionName);
    }
} 
