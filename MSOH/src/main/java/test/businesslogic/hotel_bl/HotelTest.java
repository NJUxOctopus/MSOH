package test.businesslogic.hotel_bl;

import businesslogic.hotel_bl.Hotel;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.businesslogic.IP;
import test.businesslogic.TestDriver;
import util.ResultMessage;
import vo.ClerkVO;
import vo.HotelVO;

import static junit.framework.TestCase.assertEquals;

/**
 * Hotel Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class HotelTest {
    private Hotel hotel;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        hotel = new Hotel();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: modifyHotel(HotelVO hotelVO)
     */
    @Test
    public void testModifyHotel() throws Exception {
        ResultMessage resultMessage = hotel.modifyHotel(new HotelVO("1000", "123", "123", "1", "1", "1", null, 1,
                "1", null));
        assertEquals(ResultMessage.Hotel_HotelNotExist, resultMessage);
    }

} 
