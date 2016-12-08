package po;

import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;

import java.sql.Timestamp;

/**
 * Created by zqh on 2016/12/7.
 */
public class MemberLevelTest {
    @Test
    public void testSaveMemberLevel(){
        MemberLevelPO memberLevel=new MemberLevelPO();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        memberLevel.setFramerName("李沧海");
        memberLevel.setFrameDate(timestamp);
        memberLevel.setNum(7);
        memberLevel.setCreditBoundaries("2000;3000;5000;7000;10000;13000;20000");

        Session session= HibernateUtil.getSession();
        session.beginTransaction();

        session.save(memberLevel);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }
}
