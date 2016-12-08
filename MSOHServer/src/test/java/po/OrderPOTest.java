package po;

import data.order_dataserviceImpl.Order_DataServiceImpl;
import dataservice.order_dataservice.Order_DataService;
import org.junit.Test;
import util.OrderStatus;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by zqh on 2016/11/30.
 */
public class OrderPOTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {

        Timestamp esChIn;
        Timestamp acChIn;
        Timestamp esChOut;
        Timestamp acChOut;
        Timestamp laEx;

        String estimatedCheckInTime = "2016-11-30 22:33:33";
        String actualCheckInTime = "2016-12-01 12:34:33";
        String estimatedCheckOutTime = "2016-12-03 12:34:33";
        String actualCheckoutTime = "2016-12-05 12:34:33";
        String latestExecutedTime = "2016-12-06 12:34:33";

        esChIn = Timestamp.valueOf(estimatedCheckInTime);
        acChIn = Timestamp.valueOf(actualCheckInTime);
        esChOut = Timestamp.valueOf(estimatedCheckOutTime);
        acChOut = Timestamp.valueOf(actualCheckoutTime);
        laEx = Timestamp.valueOf(latestExecutedTime);

        OrderPO orderPO = new OrderPO("桑田", "13013822266", "320581199707210555", "12345678", "如家", esChIn, acChIn, esChOut, acChOut, laEx, "双人房", 2, false, "无", 1, "双十一", 520, 480, OrderStatus.ABNORMAL);
        Order_DataService order_dataService = Order_DataServiceImpl.getInstance();

        order_dataService.addOrder(orderPO);
    }

}
