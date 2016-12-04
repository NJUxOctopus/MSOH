import businesslogic.promotion_bl.Promotion;
import junit.framework.TestCase;
import util.MemberType;
import util.PromotionType;
import util.ResultMessage;
import vo.PromotionVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/11/27.
 */
public class PromotionTest extends TestCase {
    Promotion promotion = new Promotion();

    public void testAddPromotion()throws RemoteException{
        String[] targetHotel = "RUJIA;HANTING".split(";");
        ResultMessage resultMessage1 = promotion.addPromotion(new PromotionVO(
                "pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),9,3,"123456", PromotionType.HotelPromotion));
        ResultMessage resultMessage2 = promotion.addPromotion(new PromotionVO(
                "pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),9,3,"1234567",PromotionType.HotelPromotion));
        ResultMessage resultMessage3 = promotion.addPromotion(new PromotionVO(
                "pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),-1,3,"123456",PromotionType.HotelPromotion));
        ResultMessage resultMessage4 = promotion.addPromotion(new PromotionVO(
                "pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),9,3,"",PromotionType.HotelPromotion));
        assertEquals(ResultMessage.PromotionExist,resultMessage1);
        assertEquals(ResultMessage.Promotion_AddPromotionSuccess,resultMessage2);
        assertEquals(ResultMessage.DataFormatWrong,resultMessage3);
        assertEquals(ResultMessage.Blank,resultMessage4);
    }

    public void testModifyPromotion()throws RemoteException{
        String[] targetHotel = "RUJIA;HANTING".split(";");
        ResultMessage resultMessage1 = promotion.modifyPromotion(new PromotionVO(
                "pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),9,3,"123456",PromotionType.HotelPromotion));
        ResultMessage resultMessage2 = promotion.modifyPromotion(new PromotionVO(
                "pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),9,3,"1234567",PromotionType.HotelPromotion));
        ResultMessage resultMessage3 = promotion.modifyPromotion(new PromotionVO(
                "pxr", new Timestamp(2016, 11, 27, 9, 0, 0, 0), "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),-1,3,"123456",PromotionType.HotelPromotion));
        ResultMessage resultMessage4 = promotion.modifyPromotion(new PromotionVO(
                "pxr", null, "1111", MemberType.NORMAL, "XIANLIN",
                targetHotel,new Timestamp(2016, 11, 27, 9, 0, 0, 0),new Timestamp(2016, 11, 28, 9, 0, 0, 0),9,3,"123456",PromotionType.HotelPromotion));
        assertEquals(ResultMessage.Promotion_ModifyPromotionSuccess,resultMessage1);
        assertEquals(ResultMessage.PromotionNotExist,resultMessage2);
        assertEquals(ResultMessage.DataFormatWrong,resultMessage3);
        assertEquals(ResultMessage.Blank,resultMessage4);
    }

    public void testDeletePromotion()throws RemoteException{
        ResultMessage resultMessage1 = promotion.deletePromotion("123456");
        ResultMessage resultMessage2 = promotion.deletePromotion("1234567");
        assertEquals(ResultMessage.Promotion_DeletePromotionSuccess,resultMessage1);
        assertEquals(ResultMessage.PromotionNotExist,resultMessage2);
    }
}
