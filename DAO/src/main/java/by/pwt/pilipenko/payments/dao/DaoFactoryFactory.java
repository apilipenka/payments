package by.pwt.pilipenko.payments.dao;

import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;

import javax.naming.NamingException;

/**
 * Created by apilipenka on 8/19/2016.
 */
public class DaoFactoryFactory {
    private static AbstractDAOFactory instance;



    public static AbstractDAOFactory getInstance() throws NamingException, ClassNotFoundException {

        AbstractDAOFactory localInstance = instance;

        if (localInstance == null) {

            synchronized (DaoFactoryFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DAOFactory();
                }

            }

        }

        return localInstance;
    }

    public DaoFactoryFactory() {
    }

    public DaoFactoryFactory(AbstractDAOFactory instance) {
        this.instance = instance;
    }


}
