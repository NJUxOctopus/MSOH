package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by zqh on 2016/11/23.
 */
public class HibernateUtil {
    private static final SessionFactory sessionfactory;
    private static Session session;

    static{
        try{
//            Configuration cfg=new Configuration().configure();
//            StandardServiceRegistryBuilder ssrb=new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
//            StandardServiceRegistry ssr=ssrb.build();
//            sessionfactory=cfg.buildSessionFactory(ssr);
            sessionfactory=new Configuration().configure().buildSessionFactory();
        }catch(Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionfactory(){
        return sessionfactory;
    }
    public static Session getSession() throws HibernateException{
        session=sessionfactory.openSession();
        return session;
    }
    public static void closeSession(Session session){
        if(null!=session){
            session.close();
        }
    }
}
