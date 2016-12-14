package DataHelperImpl.memberLevelDataHelperImpl;

import DataHelper.memberLevelDataHelper.MemberLevelDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import po.MemberLevelPO;
import util.EncryptionUtil;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
@SuppressWarnings(value = {"Duplicates"})
public class MemberLevelDataHelperSQLImpl implements MemberLevelDataHelper {
    /**
     * 更新会员等级制度
     *
     * @param memberLevelPO
     * @return 是否成功
     */
    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) {
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.update(memberLevelPO);
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
     * 获取会员等级制度
     *
     * @return 会员等级制度
     */
    public MemberLevelPO getMemberLevel() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<MemberLevelPO> list = session.createQuery("from MemberLevelPO ").list();

        if (list.isEmpty()) {
            return null;
        }

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return list.get(0);

    }
}
