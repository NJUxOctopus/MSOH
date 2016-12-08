package po;

import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;
import util.WorkerPosition;

/**
 * Created by zqh on 2016/12/7.
 */
public class ManagerPOTest {
    @Test
    public void testSaveManager(){
        ManagerPO managerPO=new ManagerPO("金三","320522201612072017","13012653214","123456","F:/jinsan.png", WorkerPosition.Manager);

        Session session= HibernateUtil.getSession();
        session.beginTransaction();

        session.save(managerPO);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }
}
