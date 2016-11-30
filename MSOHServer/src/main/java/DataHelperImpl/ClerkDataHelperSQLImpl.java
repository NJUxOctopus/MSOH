package DataHelperImpl;

import DataHelper.ClerkDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.ClerkPO;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
@SuppressWarnings(value = {"Duplicates", "deprecation"})
public class ClerkDataHelperSQLImpl implements ClerkDataHelper {
    /**
     * 数据库中新增酒店工作人员
     *
     * @param clerkPO
     */
    public void addClerk(ClerkPO clerkPO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.save(clerkPO);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }

    /**
     * 数据库中更新酒店工作人员
     *
     * @param clerkPO
     */
    public void modifyClerk(ClerkPO clerkPO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.update(clerkPO);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

    }

    /**
     * 数据库中删除酒店工作人员
     *
     * @param clerkPO
     */
    public void deleteClerk(ClerkPO clerkPO) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.delete(clerkPO);

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
    }

    /**
     * 根据ID查找酒店工作人员
     *
     * @param ID
     * @return 查询到的酒店工作人员PO
     */
    public ClerkPO getClerkByID(String ID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            ClerkPO clerkPO = (ClerkPO) session.get(ClerkPO.class, ID);

            return clerkPO;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据姓名查找酒店工作人员
     *
     * @param name
     * @return 查询到的酒店工作人员PO
     */
    public List<ClerkPO> getClerkByName(String name) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from ClerkPO as clerk where clerk.name=:n";
            Query query = session.createQuery(hql);
            query.setString("n", name);

            List<ClerkPO> list = query.list();
            return list;

        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 获得所有酒店工作人员信息
     *
     * @return 所有酒店工作人员组成的列表
     */
    public List<ClerkPO> getAllClerks() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<ClerkPO> list = session.createQuery("from ClerkPO").list();

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return list;
    }
}
