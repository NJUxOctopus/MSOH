package po;

import org.hibernate.Session;
import org.junit.Test;
import util.DataUtil.HibernateUtil;

/**
 * Created by zqh on 2016/12/11.
 */
public class CompanyTest {
    @Test
    public void testSaveCompany(){
        Company company=new Company("苏宁控股集团");

        Session session= HibernateUtil.getSession();
        session.beginTransaction();
        session.save(company);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }
}
