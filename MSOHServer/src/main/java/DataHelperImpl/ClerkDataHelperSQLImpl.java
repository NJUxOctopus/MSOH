package DataHelperImpl;

import DataHelper.ClerkDataHelper;
import org.hibernate.Session;
import po.ClerkPO;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zqh on 2016/11/24.
 */
public class ClerkDataHelperSQLImpl implements ClerkDataHelper{
    /**
     * 数据库中新增酒店营销人员
     * @param clerkPO
     */
    public void addClerk(ClerkPO clerkPO) {

    }

    public void modifyClerk(ClerkPO clerkPO) {

    }

    public void deleteClerk(ClerkPO clerkPO) {

    }

    public ClerkPO getClerkByID(String ID) {
        return null;
    }

    public ClerkPO getClerkByName(String name) {
        return null;
    }

    /**
     *
     * @return 所有酒店工作人员组成的列表
     */
    public List<ClerkPO> getAllClerks() {
        Session session=HibernateUtil.getSession();
        session.beginTransaction();

        List<ClerkPO> list=session.createQuery("from ClerkPO").list();

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return list;
    }
}
