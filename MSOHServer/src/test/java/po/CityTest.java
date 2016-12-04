package po;

import org.hibernate.Session;
import org.junit.Test;
import util.HibernateUtil;

/**
 * Created by zqh on 2016/12/3.
 */
public class CityTest {
    @Test
    public void testCity(){
        CityArea cityArea=new CityArea("南京","汤山温泉度假区");

        Session session= HibernateUtil.getSession();
        session.beginTransaction();

        session.delete(cityArea);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

    }
}
