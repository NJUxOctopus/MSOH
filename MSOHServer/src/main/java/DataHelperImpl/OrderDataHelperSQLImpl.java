package DataHelperImpl;

import DataHelper.OrderDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.OrderPO;
import util.HibernateUtil;
import util.OrderStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/11/30.
 */
@SuppressWarnings(value = {"Duplicates", "deprecation"})
public class OrderDataHelperSQLImpl implements OrderDataHelper {
    /**
     * 新建订单
     *
     * @param po
     */
    public boolean addOrder(OrderPO po) {
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
     * 更新订单信息
     *
     * @param orderPO
     */
    public boolean updateOrder(OrderPO orderPO) {
        Session session = null;
        try {
            session=HibernateUtil.getSession();
            session.beginTransaction();
            session.update(orderPO);
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
     * 删除订单
     *
     * @param orderPO
     */
    public boolean deleteOrder(OrderPO orderPO) {
        Session session = null;
        try {
            session=HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(orderPO);
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
     * 获得所有订单
     *
     * @return 所有订单列表
     */
    public List<OrderPO> getAllOrders() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<OrderPO> list = session.createQuery("from OrderPO ").list();

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return list;
    }

    /**
     * 根据ID获得订单
     *
     * @param orderID
     * @return 根据ID查询到的订单列表
     */
    public OrderPO getOrderByOrderID(String orderID) {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            OrderPO orderPO = (OrderPO) session.get(OrderPO.class, orderID);

            return orderPO;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 根据客户ID获得相应订单列表
     *
     * @param customerID
     * @return 该客户曾经的订单列表
     */
    public List<OrderPO> findOrderByCustomerID(String customerID) {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from OrderPO as order where order.customerID=:n";
            Query query = session.createQuery(hql);
            query.setString("n", customerID);

            List<OrderPO> list = query.list();

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<OrderPO>();
        } finally {
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 根据酒店ID获得响应订单列表
     *
     * @param hotelID
     * @return 该酒店的所有订单列表
     */
    public List<OrderPO> findOrderByHotelID(String hotelID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from OrderPO as order where order.hotelID=:n";
            Query query = session.createQuery(hql);
            query.setString("n", hotelID);

            List<OrderPO> list = query.list();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<OrderPO>();
        } finally {
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 根据订单状态获取订单
     *
     * @param orderStatus
     * @return 对应状态的订单列表
     */
    public List<OrderPO> findOrderByOrderStatus(OrderStatus orderStatus) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String orderStatusStr = String.valueOf(orderStatus);

            String hql = "from OrderPO as order where order.orderStatus=:n";
            Query query = session.createQuery(hql);

            query.setString("n", orderStatusStr);
            List<OrderPO> list = query.list();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<OrderPO>();
        } finally {
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }
    }
}
