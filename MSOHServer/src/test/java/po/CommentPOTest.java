package po;

import org.hibernate.Session;
import org.junit.Test;
import util.DataUtil.HibernateUtil;

import java.sql.Timestamp;

/**
 * Created by zqh on 2016/11/23.
 */
public class CommentPOTest {
    @Test
    public void testSaveCommentPO() {
        Session session = HibernateUtil.getSession();

        CommentPO c = new CommentPO(55, "bad", "st", "320581199703032586", "hgg", "52363523", "201611230002", new Timestamp(System.currentTimeMillis()));
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }
}
