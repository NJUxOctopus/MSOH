

import businesslogic.customer_bl.Customer;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import util.MemberType;
import util.ResultMessage;
import vo.CreditRecordVO;
import vo.CustomerVO;
import vo.HotelVO;

import java.util.List;

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

    @Test
    public void testGetCredit() throws Exception {
        assertEquals(-1, customer.getCredit("123"));
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
        List<HotelVO> hotelVO = customer.getHistoryHotel("320581199704044040");
        assertEquals(0, hotelVO.size());
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

    /**
     * Method: changePassword(String ID, String oldPassword, String newPassword1, String newPassword2)
     */
    @Test
    public void testChangeInfo() throws Exception {
        ResultMessage resultMessage1 = customer.changeInfo(new CustomerVO("", "123456", "12345678910", "123@qq.com", 0, "",
                "320200000000000000", MemberType.NONMEMBER));
        assertEquals(ResultMessage.Blank, resultMessage1);
    }
} 
