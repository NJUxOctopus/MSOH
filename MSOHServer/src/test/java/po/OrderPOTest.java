package po;

import DataHelper.OrderDataHelper;
import DataHelperImpl.OrderDataHelperSQLImpl;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;
import util.OrderStatus;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zqh on 2016/11/30.
 */
public class OrderPOTest {
    @Test
    public void test() {

//        Timestamp ts1;
//        Timestamp ts2;
//        Timestamp ts3;
//        Timestamp ts4;
//        Timestamp ts5;
//
//        String str1="2016-11-30 22:33:33";
//        String str2="2016-12-01 12:34:33";
//        String str3="2016-12-03 12:34:33";
//        String str4="2016-12-05 12:34:33";
//        String str5="2016-12-06 12:34:33";
//
//        ts1=Timestamp.valueOf(str1);
//        ts2=Timestamp.valueOf(str2);
//        ts3=Timestamp.valueOf(str3);
//        ts4=Timestamp.valueOf(str4);
//        ts5=Timestamp.valueOf(str5);

        OrderDataHelper orderDataHelper=new OrderDataHelperSQLImpl();

        List<OrderPO> list=orderDataHelper.findOrderByOrderStatus(OrderStatus.ABNORMAL);

        System.out.print(list.size());


    }

}
