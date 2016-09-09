package by.pwt.pilipenko.payments.dao.hibernate;

/**
 * Created by apilipenka on 8/29/2016.
 */

import by.pwt.pilipenko.payments.dao.BaseDAOFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;


/**
 * Created by apilipenka on 8/19/2016.
 */
public abstract class AbstractDAOFactory implements BaseDAOFactory {
    private static Logger log = Logger.getLogger(AbstractDAOFactory.class);
    private final ThreadLocal<Session> sessions = new ThreadLocal<>();
    private static ThreadLocal<Boolean> flags = new ThreadLocal<>();
    //private Session session;
    private Transaction transaction;


    public Session getSession() throws SQLException {
        Session localInstance = sessions.get();

        if (localInstance == null) {

            synchronized (AbstractDAOFactory.class) {


                localInstance =  sessions.get();
                if (localInstance == null) {
                    Session session = null;
                    try {
                        Configuration configuration = new Configuration();
                        configuration.configure();

                        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                                configuration.getProperties()).build();
                        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                        session = sessionFactory.openSession();
                        sessions.set(session);
                        flags.set(Boolean.FALSE);
                    } catch (Throwable ex) {
                        log.error("Initial SessionFactory creation failed. " + ex);
                        //System.exit(0);
                        throw ex;
                    }


                    localInstance = session;

                }

            }

        }

        return localInstance;

    }

    public void commit() throws SQLException {
        transaction.commit();
        flags.set(Boolean.FALSE);
    }

    public void rollback() throws SQLException {
        transaction.rollback();
        flags.set(Boolean.FALSE);
    }

    public void beginTransaction() throws SQLException {
        transaction = getSession().beginTransaction();
        flags.set(Boolean.TRUE);
    }

    public void endTransaction() throws SQLException {
        //getSession().close();
        //sessions.set(null);
        flags.set(Boolean.FALSE);
    }

    public boolean isParentTransactionStarted () {
        Boolean flag = flags.get();
        if (flag!=null)
            return flags.get();
        flags.set(Boolean.FALSE);
        return false;
    }

    public void closeSession() throws SQLException {
        Session session = sessions.get();
        if (session!=null) {
            if (getSession().isOpen())
                getSession().close();
            sessions.set(null);
        }

        flags.set(Boolean.FALSE);
    }

}


