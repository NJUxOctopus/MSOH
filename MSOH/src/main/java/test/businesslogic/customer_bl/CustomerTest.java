package test.businesslogic.customer_bl;

import businesslogic.customer_bl.Customer;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.businesslogic.IP;
import test.businesslogic.TestDriver;
import util.MemberType;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;

import static junit.framework.TestCase.assertEquals;

/**
 * Customer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class CustomerTest {
    private Customer customer;

    @Before
    public void before() throws Exception {
        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        customer = new Customer();
    }

    @After
    public void after() throws Exception {

    }


    /**
     * Method: signUp(CustomerVO customerVO)
     */
    @Test
    public void testSignUp() throws Exception {
        ResultMessage resultMessage1 = customer.signUp(new CustomerVO("pxr", "123456", "12345678910", "123@qq.com", 0, "",
                "320200000000000000", MemberType.NONMEMBER));
        assertEquals(ResultMessage.Customer_SignupSuccess, resultMessage1);
        customer.deleteCustomer("320200000000000000");
    }

    /**
     * Method: getHistoryHotel(String customerID)
     */
    @Test
    public void testGetHistoryHotel() throws Exception {
        HotelVO hotelVO = customer.getHistoryHotel("320581199704044040").get(0);
        assertEquals("10000006", hotelVO.hotelID);
    }

    /**
     * Method: getCreditRecord(String customerID)
     */
    @Test
    public void testGetCreditRecord() throws Exception {
        CreditRecordVO creditRecordVO = customer.getCreditRecord("320581199704044040").get(0);
        assertEquals("320581199704044040", creditRecordVO.customerID);
    }

    /**
     * Method: changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)
     */
    @Test
    public void testChangePassword() throws Exception {
        ResultMessage resultMessage1 = customer.changePassword("320581199704044040", "123123", "123456", "123456");
        assertEquals(resultMessage1, ResultMessage.ChangePasswordSuccess);
        customer.changePassword("320581199704044040", "123456", "123123", "123123");
    }
} 
