package po;

import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;

import java.sql.Timestamp;

/**
 * Created by zqh on 2016/11/24.
 */
public class CreditRecordPOTest {
    @Test
    public void testSaveCreditRecord() {
        CreditRecordPO cr = new CreditRecordPO(200, new Timestamp(System.currentTimeMillis()), "zqh", "320581199707230236", 3200, "201611240001", "pxr");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(cr);
        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }
}
