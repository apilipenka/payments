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
abstract class AbstractDAOFactory implements BaseDAOFactory {
    private static Logger log = Logger.getLogger(AbstractDAOFactory.class);
    private final ThreadLocal<Session> sessions = new ThreadLocal<>();
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory = null;


    public Session getSession() throws SQLException {
        Session localInstance = session;

        if (localInstance == null) {

            synchronized (AbstractDAOFactory.class) {


                localInstance = session;
                if (localInstance == null) {

                    try {
                        Configuration configuration = new Configuration();
                        configuration.configure();

                        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                                configuration.getProperties()).build();
                        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                    } catch (Throwable ex) {
                        log.error("Initial SessionFactory creation failed. " + ex);
                        System.exit(0);
                    }

                    session = sessionFactory.openSession();
                    localInstance = session;

                }

            }

        }

        return localInstance;

    }

    public void commit() throws SQLException {
        transaction.commit();
    }

    public void rollback() throws SQLException {
        transaction.rollback();
    }

    public void beginTransaction() throws SQLException {
        transaction = getSession().beginTransaction();
    }

    public void endTransaction() throws SQLException {
    }


}


