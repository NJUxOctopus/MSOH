package po;

import data.promotion_dataserviceImpl.Promotion_DataServiceImpl;
import dataservice.promotion_dataservice.Promotion_DataService;
import org.junit.Test;
import util.MemberType;
import util.PromotionType;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by zqh on 2016/12/1.
 */
public class PromotionPOTest {
    @Test
    public void testPromotionPO() throws IOException, ClassNotFoundException {
        // 网站营销人员：	陈营	123456	320522201612082017	12036978541	 F:/chenying.png	 Marketer
        //                  孙销	123456	320581201612102017	12032115975	 F:/sunxiao.png	     Marketer
        String st1 = "2016-12-10 00:00:00";
        Timestamp frameDate = Timestamp.valueOf(st1);
        String str2 = "2016-12-11 00:00:00";
        Timestamp startTime = Timestamp.valueOf(str2);
        String str3 = "2016-12-31 00:00:00";
        Timestamp endTime = Timestamp.valueOf(str3);

//        PromotionPO promotionPO=new PromotionPO("梁静茹", frameDate,"悠闲圣诞，优惠享不停", MemberType.NONMEMBER,"新街口地区","10000006",startTime,endTime,0.75,1,null, PromotionType.HotelPromotion);



        Promotion_DataService promotion_dataService=Promotion_DataServiceImpl.getInstance();
//        PromotionPO promotionPO=promotion_dataService.getPromotion(8);
//        promotionPO.setTargetUser(MemberType.NONMEMBER);
//        promotion_dataService.modifyPromotion(promotionPO);
//        promotion_dataService.addPromotion(promotionPO);

    }
}
