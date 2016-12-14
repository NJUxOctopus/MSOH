package po;

import org.hibernate.Session;
import org.junit.Test;
import util.DataUtil.HibernateUtil;

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


        memberLevel=session.get(MemberLevelPO.class,1);
        memberLevel.setDiscountList("0.9;0.85;0.8;0.75;0.7;0.6;0.55");
        session.update(memberLevel);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }
}
