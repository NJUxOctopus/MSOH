package DataHelperImpl.customerDataHelperImpl;

import DataHelper.customerDataHelper.CustomerDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.CustomerPO;
import util.EncryptionUtil;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/2.
 */
@SuppressWarnings(value = {"Duplicates"})
public class CustomerDataHelperSQLImpl implements CustomerDataHelper {
    /**
     * 增加客户
     *
     * @param customerPO
     * @return 是否成功
     */
    public boolean addCustomer(CustomerPO customerPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.save(customerPO);

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
     * 删除客户
     *
     * @param customerPO
     * @return 是否成功
     */
    public boolean deleteCustomer(CustomerPO customerPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.delete(customerPO);

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
     * 修改客户信息
     *
     * @param customerPO
     * @return 是否成功
     */
    public boolean modifyCustomer(CustomerPO customerPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.update(customerPO);

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
     * 根据姓名查找客户
     *
     * @param customerName
     * @return 与客户姓名相匹配的客户列表
     */
    public List<CustomerPO> findCustomerByName(String customerName) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from CustomerPO as customer where customer.userName=:n";
            Query query = session.createQuery(hql);
            query.setString("n", customerName);

            List<CustomerPO> list = query.list();

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<CustomerPO>();
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据ID查找客户
     *
     * @param customerID
     * @return 与ID相对应的客户
     */
    public CustomerPO findCustomerByID(String customerID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            CustomerPO customerPO = session.get(CustomerPO.class, customerID);

            return customerPO;
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
     * 获得所有客户列表
     *
     * @return 所有客户列表
     */
    public List<CustomerPO> findAllCustomers() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<CustomerPO> list = session.createQuery("from CustomerPO ").list();

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return list;
    }
}
