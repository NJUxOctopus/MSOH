package DataHelperImpl;

import DataHelper.CommentDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.CommentPO;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
@SuppressWarnings(value = {"Duplicates"})
public class CommentDataHelperSQLImpl implements CommentDataHelper {
    /**
     * 增加评论
     *
     * @param commentPO
     * @return 是否成功
     */
    public boolean addComment(CommentPO commentPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.save(commentPO);

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
     * 获得酒店的所有评论
     *
     * @param hotelID
     * @return 该酒店的评论列表
     */
    public List<CommentPO> getCommentsByHotel(String hotelID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from CommentPO as comment where comment.hotelID=:n";
            Query query = session.createQuery(hql);

            query.setString("n", hotelID);
            List<CommentPO> list = query.list();
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
     * 获得订单对应的评论
     *
     * @param orderID
     * @return 订单的评论
     */
    public CommentPO getCommentByOrder(String orderID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from CommentPO as comment where comment.orderID=:n";
            Query query = session.createQuery(hql);

            query.setString("n", orderID);
            List<CommentPO> list = query.list();

            if (list.isEmpty()) {
                return null;
            }

            return list.get(0);
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
}
