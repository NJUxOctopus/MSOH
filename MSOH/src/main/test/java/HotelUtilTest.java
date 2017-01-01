

import businesslogic.hotel_bl.HotelUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import vo.DailyRoomInfoVO;
import vo.HotelVO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

/**
 * HotelUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class HotelUtilTest {
    private HotelUtil hotelUtil;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        hotelUtil = new HotelUtil();
    }

    @After
    public void after() throws Exception {
    }



    /**
     * Method: getByID(String ID)
     */
    @Test
    public void testGetByID() throws Exception {
        HotelVO hotelVO = hotelUtil.getByID("10000002");
        assertEquals("10000002", hotelVO.hotelID);
    }

    /**
     * Method: getHotelByClerkID(String clerkID)
     */
    @Test
    public void testGetHotelByClerkID() throws Exception {
        HotelVO hotelVO = hotelUtil.getHotelByClerkID("320581199701010006");
        assertEquals(null, hotelVO);
    }

    /**
     * Method: hotelIsReserverd(String customerID, String hotelID)
     */
    @Test
    public void testHotelIsReserverd() throws Exception {
        boolean b = hotelUtil.hotelIsReserverd("320581199701010006", "10000006");
        assertEquals(false, b);
    }
    @Test
    public void testGetComment()throws Exception{
        assertEquals(0,hotelUtil.getComment("10000004").size());
    }

    @Test
    public void testGetDailyRoomInfo()throws Exception{
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00.0");
        Timestamp timestamp = Timestamp.valueOf(simpleDateFormat.format(date));
        DailyRoomInfoVO dailyRoomInfoVO = hotelUtil.getDailyRoomInfo("10000004",timestamp);
        assertEquals(dailyRoomInfoVO.room.size(),3);
    }
} 
