package DataHelperImpl.customerDataHelperImpl;

import DataHelper.customerDataHelper.CreditRecordDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.CreditRecordPO;
import util.DataUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
@SuppressWarnings(value = {"Duplicates"})
public class CreditRecordDataHelperSQLImpl implements CreditRecordDataHelper {
    /**
     * 新增信用记录
     *
     * @param creditRecordPO
     * @return 是否成功
     */
    public boolean addCreditRecord(CreditRecordPO creditRecordPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.save(creditRecordPO);

            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据客户ID查询信用记录
     *
     * @param ID
     * @return 与客户对应的信用记录
     */
    public List<CreditRecordPO> findCreditRecordByID(String ID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from CreditRecordPO as creditrecord where creditrecord.customerID=:n";
            Query query = session.createQuery(hql);
            query.setString("n", ID);

            List<CreditRecordPO> list = query.list();

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<CreditRecordPO>();
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }
}
