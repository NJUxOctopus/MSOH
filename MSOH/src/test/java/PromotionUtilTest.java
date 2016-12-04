import businesslogic.promotion_bl.PromotionUtil;
import junit.framework.TestCase;
import util.MemberType;
import util.PromotionType;
import vo.PromotionVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/11/27.
 */
public class PromotionUtilTest extends TestCase {
    PromotionUtil promotionUtil = new PromotionUtil();

    public void testGetAll() throws RemoteException {
        List<PromotionVO> promotionVOList = new ArrayList<PromotionVO>();
        promotionVOList.add(new PromotionVO("pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                null, new Timestamp(2016, 11, 27, 9, 0, 0, 0), new Timestamp(2016, 11, 28, 9, 0, 0, 0), 9, 3, "123456", PromotionType.HotelPromotion));
        assertEquals(promotionVOList.get(0).promotionID, promotionUtil.getAll(new Timestamp(2016, 11, 28, 0, 0, 0, 0)).get(0).promotionID);
    }

    public void testGetSingle() throws RemoteException {
        PromotionVO promotionVO1 = new PromotionVO("pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                null, new Timestamp(2016, 11, 27, 9, 0, 0, 0), new Timestamp(2016, 11, 28, 9, 0, 0, 0), 9, 3, "123456", PromotionType.HotelPromotion);

        assertEquals(promotionUtil.getSingle("123456").discount, promotionVO1.discount);
        assertEquals(promotionUtil.getSingle("123"), null);
    }
}
