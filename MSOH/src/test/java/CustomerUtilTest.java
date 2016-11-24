import businesslogic.customer_bl.CustomerUtil;
import junit.framework.TestCase;
import util.MemberType;
import vo.CustomerVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pxr on 16/11/19.
 */
public class CustomerUtilTest extends TestCase{
    CustomerUtil customerUtil = new CustomerUtil();

    public void testGetAll()throws RemoteException{
        List<CustomerVO> customerVOList = customerUtil.getAll();
        List<CustomerVO> customerVOList1 = new ArrayList<CustomerVO>();
        customerVOList1.add(new CustomerVO("pxr", "123456", "12345678910", "123@qq.com", 0, null,
                "320200000000000000", MemberType.NONMEMBER));
        assertEquals(customerVOList.get(0).ID,customerVOList1.get(0).ID);
    }

    public void testGetSingle()throws RemoteException{
        CustomerVO customerVO1 = customerUtil.getSingle("320200000000000000");
        CustomerVO customerVO2 = new CustomerVO("pxr", "123456", "12345678910", "123@qq.com", 0, null,
                "320200000000000000", MemberType.NONMEMBER);
        CustomerVO customerVO3 = customerUtil.getSingle("123");
        assertEquals(customerVO1.ID,customerVO2.ID);
        assertEquals(customerVO3,null);
    }

    public void testGetByName()throws RemoteException{
        List<CustomerVO> customerVOList = customerUtil.getByName("pxr");
        List<CustomerVO> customerVOList1 = new ArrayList<CustomerVO>();
        List<CustomerVO> customerVOList2 = customerUtil.getByName("zqh");
        customerVOList1.add(new CustomerVO("pxr", "123456", "12345678910", "123@qq.com", 0, null,
                "320200000000000000", MemberType.NONMEMBER));
        assertEquals(customerVOList.get(0).ID,customerVOList1.get(0).ID);
        assertEquals(customerVOList2,null);
    }
}
