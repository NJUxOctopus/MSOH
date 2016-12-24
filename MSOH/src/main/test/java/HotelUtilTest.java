

import businesslogic.hotel_bl.HotelUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import vo.HotelVO;

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
        assertEquals("320581199701010006", hotelVO.clerkID);
    }

    /**
     * Method: hotelIsReserverd(String customerID, String hotelID)
     */
    @Test
    public void testHotelIsReserverd() throws Exception {
        boolean b = hotelUtil.hotelIsReserverd("320581199701010006", "10000006");
        assertEquals(false, b);
    }


} 
