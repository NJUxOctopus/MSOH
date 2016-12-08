package po;

import data.promotion_dataserviceImpl.Promotion_DataServiceImpl;
import dataservice.promotion_dataservice.Promotion_DataService;
import org.junit.Test;
import util.MemberType;
import util.PromotionType;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by 名 on 2016/12/1.
 */
public class PromotionPOTest {
    @Test
    public void testPromotionPO() throws IOException, ClassNotFoundException {
        // 网站营销人员：	陈营	123456	320522201612082017	12036978541	 F:/chenying.png	 Marketer
        //                  孙销	123456	320581201612102017	12032115975	 F:/sunxiao.png	     Marketer
        String st1 = "2016-12-07 00:00:00";
        Timestamp frameDate = Timestamp.valueOf(st1);
        String str2 = "2016-12-09 00:00:00";
        Timestamp startTime = Timestamp.valueOf(str2);
        String str3 = "2016-12-31 00:00:00";
        Timestamp endTime = Timestamp.valueOf(str3);

        PromotionPO promotionPO=new PromotionPO("孙销", frameDate,"夫子庙地区·", MemberType.ENTREPRISE,"夫子庙地区","10000007",startTime,endTime,0.95,2,null, PromotionType.HotelPromotion);

        Promotion_DataService promotion_dataService=Promotion_DataServiceImpl.getInstance();

        promotion_dataService.addPromotion(promotionPO);

    }
}
