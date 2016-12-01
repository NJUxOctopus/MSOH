package DataHelperImpl;

import DataHelper.MemberDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import po.MemberPO;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
@SuppressWarnings(value = {"Duplicates"})
public class MemberDataHelperSQLImpl implements MemberDataHelper{
    /**
     * 增加会员
     * @param po
     * @return 是否成功
     */
    public boolean addMember(MemberPO po) {
        Session session=null;
        try{
            session= HibernateUtil.getSession();
            session.beginTransaction();
            session.save(po);
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }finally {
            if(session!=null){
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 删除会员
     * @param po
     * @return 是否成功
     */
    public boolean deleteMember(MemberPO po) {
        Session session=null;
        try{
            session= HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(po);
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }finally {
            if(session!=null){
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 更新会员信息
     * @param po
     * @return 是否成功
     */
    public boolean updateMember(MemberPO po) {
        Session session=null;
        try{
            session= HibernateUtil.getSession();
            session.beginTransaction();
            session.update(po);
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            return false;
        }finally {
            if(session!=null){
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据ID查找会员
     * @param ID
     * @return 对应ID的会员
     */
    public MemberPO findMemberByID(String ID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            MemberPO memberPO = (MemberPO) session.get(MemberPO.class, ID);

            return memberPO;
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
     * 获取所有会员信息
     * @return 所有会员的列表
     */
    public List<MemberPO> findAllMembers() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<MemberPO> list = session.createQuery("from MemberPO ").list();

        session.getTransaction().commit();
        HibernateUtil.closeSession(session);

        return list;
    }
}
