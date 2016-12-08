package po;

import DataHelper.customerDataHelper.CustomerDataHelper;
import DataHelperImpl.customerDataHelperImpl.CustomerDataHelperSQLImpl;
import org.junit.Test;
import util.MemberType;

import java.util.List;

/**
 * Created by zqh on 2016/12/2.
 */
public class CustomerTest {

    @Test
    public void testCustomer() {
        CustomerDataHelper customerDataHelper = new CustomerDataHelperSQLImpl();

        CustomerPO st = new CustomerPO("桑田", "123456", "13013822266", "151250121@nju.edu.cn", 7000, "c:/st.png", "320581199701011010", MemberType.NONMEMBER);
        CustomerPO zqh = new CustomerPO("周沁涵", "123456", "13013522336", "151250206@nju.edu.cn", 5000, "c:/zqh.png", "320581199702022020", MemberType.NONMEMBER);
        CustomerPO pxr = new CustomerPO("潘潇睿", "123456", "13025209632", "151250117@nju.edu.cn", 5500, "c:/pxr.png", "320581199703033030", MemberType.NONMEMBER);
        CustomerPO qky = new CustomerPO("钱柯宇", "123456", "13030201234", "151250118@nju.edu.cn", 6000, "c:/qky.png", "320581199704044040", MemberType.NONMEMBER);

        customerDataHelper.addCustomer(st);
//        customerDataHelper.addCustomer(zqh);
//        customerDataHelper.addCustomer(pxr);
//        customerDataHelper.addCustomer(qky);

        List<CustomerPO> list = customerDataHelper.findCustomerByName("钱志豪");
//        customerDataHelper.deleteCustomer(st);
        System.out.print(list.isEmpty());
    }
}
