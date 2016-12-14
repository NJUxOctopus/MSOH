package DataHelperImpl.memberDataHelperImpl;

import DataHelper.memberDataHelper.CompanyDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import po.Company;
import util.DataUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/8.
 */
public class CompanyDataHelperSQLImpl implements CompanyDataHelper {
    /**
     * 获得所有企业
     *
     * @return 所有企业的列表
     */
    public List<Company> getAllCompanies() {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<Company> list = session.createQuery("from Company ").list();

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<Company>();
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }

    }
}
