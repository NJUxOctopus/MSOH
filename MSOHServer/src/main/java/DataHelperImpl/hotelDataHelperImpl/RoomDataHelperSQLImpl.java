package DataHelperImpl.hotelDataHelperImpl;

import DataHelper.hotelDataHelper.RoomDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.RoomPO;
import util.DataUtil.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/5.
 */
@SuppressWarnings(value = {"Duplicates"})
public class RoomDataHelperSQLImpl implements RoomDataHelper {
    /**
     * 新增房间类型
     *
     * @param roomPO
     * @return 是否成功
     */
    public boolean addRoom(RoomPO roomPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.save(roomPO);

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
     * 更新房间信息
     *
     * @param roomPO
     * @return 是否成功
     */
    public boolean modifyRoom(RoomPO roomPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.update(roomPO);

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
     * 删除房间
     *
     * @param roomPO
     * @return 是否成功
     */
    public boolean deleteRoom(RoomPO roomPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.delete(roomPO);

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
     * 根据酒店获得房间
     *
     * @param hotelID
     * @return 对应酒店的所有房间
     */
    public List<RoomPO> getRoomsByHotel(String hotelID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from RoomPO as room where room.hotelID=:n";
            Query query = session.createQuery(hql);
            query.setString("n", hotelID);

            List<RoomPO> roomList = query.list();
            return roomList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<RoomPO>();
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 获得所有房间，供自动按日期删除房间使用
     *
     * @return 所有房间的列表
     */
    public List<RoomPO> getAllRooms() {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<RoomPO> roomList = session.createQuery("from RoomPO ").list();

            return roomList;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<RoomPO>();
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }
}
