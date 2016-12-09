package DataHelperImpl.promotionDataHelperImpl;

import DataHelper.promotionDataHelper.PromotionDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import po.PromotionPO;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
@SuppressWarnings(value = {"Duplicates", "deprecation"})
public class PromotionDataHelperSQLImpl implements PromotionDataHelper {
    /**
     * 新增促销策略
     *
     * @param po
     */
    public boolean addPromotion(PromotionPO po) {
        Session session = null;
        try {
            session=HibernateUtil.getSession();
            session.beginTransaction();

            session.save(po);
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
     * 获得促销策略
     *
     * @param promotionID
     * @return 根据ID查找得到的促销策略
     */
    public PromotionPO getPromotion(int promotionID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            PromotionPO promotionPO = (PromotionPO) session.get(PromotionPO.class, promotionID);
            return promotionPO;
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
     * 获得所有促销策略
     *
     * @return 所有促销策略构成的列表
     */
    public List<PromotionPO> getAllPromotions() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<PromotionPO> list = session.createQuery("from PromotionPO ").list();

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return list;
    }

    /**
     * 删除促销策略
     *
     * @param promotionPO
     */
    public boolean deletePromotion(PromotionPO promotionPO) {
        Session session = null;
        try {
            session=HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(promotionPO);
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
     * 修改促销策略
     *
     * @param promotionPO
     */
    public boolean modifyPromotion(PromotionPO promotionPO) {
        Session session = null;
        try {
            session=HibernateUtil.getSession();
            session.beginTransaction();
            session.update(promotionPO);
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
}
