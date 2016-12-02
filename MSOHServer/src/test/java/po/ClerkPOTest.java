package po;

import DataHelper.ClerkDataHelper;
import DataHelperImpl.ClerkDataHelperSQLImpl;
import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;
import util.WorkerPosition;

import java.util.List;

/**
 * Created by zqh on 2016/11/23.
 */
public class ClerkPOTest {
    @Test
    public void testSaveClerkPO() {
        ClerkDataHelper clerkDataHelper = new ClerkDataHelperSQLImpl();

        List<ClerkPO> clerkPOList = clerkDataHelper.getClerkByName("桑田");

        System.out.print(clerkPOList.size());
    }
}
