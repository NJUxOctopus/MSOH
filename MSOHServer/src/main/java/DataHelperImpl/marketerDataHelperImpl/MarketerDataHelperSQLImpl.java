package DataHelperImpl.marketerDataHelperImpl;

import DataHelper.marketerDataHelper.MarketerDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.MarketerPO;
import util.EncryptionUtil;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/11/28.
 */
@SuppressWarnings(value = {"Duplicates", "deprecation"})
public class MarketerDataHelperSQLImpl implements MarketerDataHelper {
    /**
     * 新增网站营销人员
     *
     * @param marketerPO
     */
    public boolean addMarketer(MarketerPO marketerPO) {
        // 密码加密
        String pw = EncryptionUtil.encode(marketerPO.getPassword());
        marketerPO.setPassword(pw);

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.save(marketerPO);
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
     * 更新网站营销人员信息
     *
     * @param marketerPO
     */
    public boolean modifyMarketer(MarketerPO marketerPO) {
        // 密码加密
        String pw = EncryptionUtil.encode(marketerPO.getPassword());
        marketerPO.setPassword(pw);

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.update(marketerPO);
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
     * 删除网站营销人员
     *
     * @param marketerPO
     */
    public boolean deleteMarketer(MarketerPO marketerPO) {
        // 密码加密
        String pw = EncryptionUtil.encode(marketerPO.getPassword());
        marketerPO.setPassword(pw);

        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.delete(marketerPO);
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
     * 根据姓名获取网站营销人员列表
     *
     * @param name
     * @return 与姓名匹配的网站营销人员列表
     */
    public List<MarketerPO> getMarketerByName(String name) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from MarketerPO as marketer where marketer.name=:n";
            Query query = session.createQuery(hql);
            query.setString("n", name);

            List<MarketerPO> list = query.list();

            if(list==null||list.isEmpty()){
                return new ArrayList<MarketerPO>();
            }else{
                // 密码解密
                for(MarketerPO marketer:list){
                    marketer.setPassword(EncryptionUtil.decode(marketer.getPassword()));
                }
            }

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<MarketerPO>();
        } finally {
            if (null != session) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据ID获取网站营销人员
     *
     * @param ID
     * @return 与ID匹配的网站营销人员
     */
    public MarketerPO getMarketerByID(String ID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            MarketerPO marketerPO = (MarketerPO) session.get(MarketerPO.class, ID);

            if(marketerPO==null){
                return marketerPO;
            }else{
                // 密码解密
                marketerPO.setPassword(EncryptionUtil.decode(marketerPO.getPassword()));
            }

            return marketerPO;
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
     * 获取所有网站营销人员列表
     *
     * @return 所有网站营销人员列表
     */
    public List<MarketerPO> getAllMarketers() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<MarketerPO> list = session.createQuery("from MarketerPO ").list();

        if(list==null||list.isEmpty()){
            return new ArrayList<MarketerPO>();
        }else{
            // 密码解密
            for(MarketerPO marketer:list){
                marketer.setPassword(EncryptionUtil.decode(marketer.getPassword()));
            }
        }

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);
        return list;
    }
}
