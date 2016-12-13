package DataHelperImpl.hotelDataHelperImpl;

import DataHelper.hotelDataHelper.HotelDataHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import po.HotelPO;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/2.
 */
@SuppressWarnings(value = "Duplicates")
public class HotelDataHelperSQLImpl implements HotelDataHelper {
    /**
     * 增加酒店
     *
     * @param hotelPO
     * @return 是否成功
     */
    public boolean addHotel(HotelPO hotelPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.save(hotelPO);
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
     * 更新酒店信息
     *
     * @param hotelPO
     * @return 是否成功
     */
    public boolean modifyHotel(HotelPO hotelPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.update(hotelPO);
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
     * 删除酒店
     *
     * @param hotelPO
     * @return 是否成功
     */
    public boolean deleteHotel(HotelPO hotelPO) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            session.delete(hotelPO);
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
     * 获取所有酒店
     *
     * @return 所有酒店的列表
     */
    public List<HotelPO> getHotels() {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<HotelPO> list = session.createQuery("from HotelPO").list();

            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<HotelPO>();
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据酒店ID查找酒店
     *
     * @param ID
     * @return 相应ID的酒店
     */
    public HotelPO getHotelByID(String ID) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            HotelPO hotelPO = (HotelPO) session.get(HotelPO.class, ID);

            return hotelPO;
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
     * 根据酒店名称查找酒店
     *
     * @param hotelName
     * @return 相应名称的酒店
     */
    public List<HotelPO> getHotelByName(String hotelName) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String hql = "from HotelPO as hotel where hotel.hotelName=:n";
            Query query = session.createQuery(hql);
            query.setString("n", hotelName);

            List<HotelPO> list = query.list();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<HotelPO>();
        } finally {
            if (session != null) {
                session.getTransaction().commit();
                HibernateUtil.closeSession(session);
            }
        }
    }

    /**
     * 根据商圈获得该商圈内的所有酒店
     * @param areaName
     * @return 该商圈内的所有酒店
     */
    public List<HotelPO> getHotelByArea(String areaName) {
        List<HotelPO> hotelList=getHotels();

        if(hotelList==null||hotelList.isEmpty()){
            return hotelList;
        }

        List<HotelPO> hotelInThisArea=new ArrayList<HotelPO>();
        for(HotelPO hotel:hotelList){
            if(hotel.getArea().equals(areaName)){
                hotelInThisArea.add(hotel);
            }
        }

        return hotelInThisArea;
    }
}
