package po;

import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;
import util.WorkerPosition;

/**
 * Created by zqh on 2016/11/23.
 */
public class ClerkPOTest {
    @Test
    public void testSaveClerkPO(){
        Session session= HibernateUtil.getSession();

        ClerkPO clerkPO=new ClerkPO("钱柯宇","13913025325","123321","320581190011223111","如家","56300074", WorkerPosition.Clerk,"c:/pxr.jpg");

        session.beginTransaction();
        session.save(clerkPO);
        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }
}
