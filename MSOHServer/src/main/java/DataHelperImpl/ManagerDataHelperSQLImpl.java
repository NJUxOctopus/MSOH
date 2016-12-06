package DataHelperImpl;

import DataHelper.ManagerDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.ManagerPO;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/11/28.
 */
@SuppressWarnings(value = {"Duplicates", "deprecation"})
public class ManagerDataHelperSQLImpl implements ManagerDataHelper {
    /**
     * 更新网站管理人员信息
     *
     * @param managerPO
     */
    public boolean modifyManager(ManagerPO managerPO) {
        Session session = null;
        try {
            session.beginTransaction();

            session.update(managerPO);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != session) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);

            }
        }
    }

    /**
     * 根据ID查找网站管理人员
     *
     * @param ID
     * @return 网站管理人员信息
     */
    public ManagerPO findManagerByID(String ID) {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            ManagerPO managerPO = (ManagerPO) session.get(ManagerPO.class, ID);
            return managerPO;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != session) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据姓名查找网站管理人员
     *
     * @param name
     * @return 与姓名相匹配的网站管理人员列表
     */
    public List<ManagerPO> findManagerByName(String name) {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from ManagerPO as manager where manager.name=:n";
            Query query = session.createQuery(hql);
            query.setString("n", name);

            List<ManagerPO> list = query.list();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<ManagerPO>();
        } finally {
            if (null != session) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 获得所有网站管理人员信息
     *
     * @return 所有网站管理人员的列表
     */
    public List<ManagerPO> findAllManagers() {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<ManagerPO> list = session.createQuery("from ManagerPO ").list();

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<ManagerPO>();
        } finally {
            if (null != session) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

}
