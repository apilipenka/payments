package by.pwt.pilipenko.payments.dao;

//import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
//import by.pwt.pilipenko.payments.dao.hibernate.DAOFactory;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by apilipenka on 8/19/2016.
 */
public class DaoFactoryFactory {
    private static ThreadLocal<BaseDAOFactory> instance = new ThreadLocal<>();
    private static DAOType daoType = DAOType.valueOf(ConfigurationManager.getProperty("dao.type").toUpperCase());

    public static DAOType getDaoType() {
        return daoType;
    }

    public static void setDaoType(String pDaoType) throws SQLException, NamingException, ClassNotFoundException {
        synchronized (DaoFactoryFactory.class) {
            DaoFactoryFactory.daoType = DAOType.valueOf(pDaoType.toUpperCase());;
            if (daoType==DAOType.HIBERNATE)
                instance.set(new by.pwt.pilipenko.payments.dao.hibernate.DAOFactory());
            else
                instance.set(new by.pwt.pilipenko.payments.dao.jdbc.DAOFactory());
        }
    }

    public static BaseDAOFactory getInstance() throws NamingException, ClassNotFoundException, SQLException {

        BaseDAOFactory localInstance = instance.get();

        if (localInstance == null) {

            synchronized (DaoFactoryFactory.class) {
                localInstance = instance.get();
                if (localInstance == null) {
                    if (daoType==DAOType.HIBERNATE)
                        instance.set(new by.pwt.pilipenko.payments.dao.hibernate.DAOFactory());
                    else
                        instance.set(new by.pwt.pilipenko.payments.dao.jdbc.DAOFactory());
                    localInstance = instance.get();

                }

            }

        }

        return localInstance;
    }


}
