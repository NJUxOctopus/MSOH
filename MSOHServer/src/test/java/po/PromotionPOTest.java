package po;

import DataHelper.DataFactory;
import data.Promotion_DataServiceImpl;
import dataservice.promotion_dataservice.Promotion_DataService;
import org.junit.Test;
import util.MemberType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 名 on 2016/12/1.
 */
public class PromotionPOTest {
    @Test
    public void testPromotionPO() throws IOException,ClassNotFoundException{
        String st1="2016-11-30 20:55:30";
        Timestamp ts1=Timestamp.valueOf(st1);
        String str2="2016-12-01 20:55:13";
        Timestamp ts2=Timestamp.valueOf(str2);
        String str3="2016-12-03 20:36:36";
        Timestamp ts3=Timestamp.valueOf(str3);

//        PromotionPO promotionPO=new PromotionPO("周沁涵", ts1,"情人节", MemberType.ENTREPRISE,"南大","如家",ts2,ts3,0.85,0);

        Promotion_DataService promotion_dataService=Promotion_DataServiceImpl.getInstance();

//        promotion_dataService.addPromotion(promotionPO);
        List<PromotionPO> promotionPO=promotion_dataService.getAllPromotions();

        for(PromotionPO p:promotionPO){
            System.out.println(p.getPromotionName());
        }
    }
}
