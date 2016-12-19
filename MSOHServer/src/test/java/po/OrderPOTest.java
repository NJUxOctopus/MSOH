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

        String estimatedCheckInTime = "2016-12-11 00:00:00";
        String actualCheckInTime = null;
        String estimatedCheckOutTime = "2016-12-13 00:00:00";
        String actualCheckoutTime = null;
        String latestExecutedTime = "2016-12-11 06:00:00";

        esChIn = Timestamp.valueOf(estimatedCheckInTime);
        acChIn = null;
        esChOut = Timestamp.valueOf(estimatedCheckOutTime);
        acChOut = null;
        laEx = Timestamp.valueOf(latestExecutedTime);
//
        OrderPO orderPO = new OrderPO("吴青峰", "13001234567", "320581199701011234", "10000006", "南京古南都饭店", esChIn, acChIn, esChOut, acChOut, laEx, "单人房", 1, false, "无",  "", 699, 699, OrderStatus.ABNORMAL);
        Order_DataService order_dataService = Order_DataServiceImpl.getInstance();

        order_dataService.addOrder(orderPO);
    }

}
