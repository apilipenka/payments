package by.pwt.pilipenko.payments.dao;

import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;

import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Created by apilipenka on 8/19/2016.
 */
public class DaoFactoryFactory {
    private static ThreadLocal<BaseDAOFactory> instance = new ThreadLocal<>();

    public static BaseDAOFactory getInstance() throws NamingException, ClassNotFoundException, SQLException {

        BaseDAOFactory localInstance = instance.get();

        if (localInstance == null) {

            synchronized (DaoFactoryFactory.class) {
                localInstance = instance.get();
                if (localInstance == null) {
                    instance.set(new DAOFactory());
                    localInstance = instance.get();

                }

            }

        }

        return localInstance;
    }


}
