

import businesslogic.promotion_bl.Promotion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import util.MemberType;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionVO;

/**
 * Promotion Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class PromotionTest {
    private Promotion promotion;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        promotion = new Promotion();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addWebPromotion(PromotionVO promotionVO)
     */
    @Test
    public void testAddWebPromotion() throws Exception {
        ResultMessage resultMessage = promotion.addWebPromotion(new PromotionVO("", null, "", MemberType.ENTREPRISE,
                "", null, null, null, 8,
                0, "", PromotionType.WebPromotion_Holiday, ""));
        Assert.assertEquals(ResultMessage.Blank,resultMessage);
    }

    /**
     * Method: addHotelPromotion(PromotionVO promotionVO)
     */
    @Test
    public void testAddHotelPromotion() throws Exception {
        ResultMessage resultMessage = promotion.addHotelPromotion(new PromotionVO("", null, "", MemberType.ENTREPRISE,
                "", null, null, null, 8,
                0, "", PromotionType.WebPromotion_Holiday, ""));
        Assert.assertEquals(ResultMessage.Blank,resultMessage);
    }

    /**
     * Method: modifyWebPromotion(PromotionVO promotionVO)
     */
    @Test
    public void testModifyWebPromotion() throws Exception {
        ResultMessage resultMessage = promotion.modifyWebPromotion(new PromotionVO("", null, "", MemberType.ENTREPRISE,
                "", null, null, null, 8,
                0, "", PromotionType.WebPromotion_Holiday, ""));
        Assert.assertEquals(ResultMessage.Blank,resultMessage);
    }

    /**
     * Method: modifyHotelPromotion(PromotionVO promotionVO)
     */
    @Test
    public void testModifyHotelPromotion() throws Exception {
        ResultMessage resultMessage = promotion.modifyHotelPromotion(new PromotionVO("", null, "", MemberType.ENTREPRISE,
                "", null, null, null, 8,
                0, "", PromotionType.WebPromotion_Holiday, ""));
        Assert.assertEquals(ResultMessage.Blank,resultMessage);
    }

} 
