package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DAOFactory extends by.pwt.pilipenko.payments.dao.hibernate.AbstractDAOFactory {


    public DAOFactory() throws NamingException, ClassNotFoundException, SQLException {
        super();
    }


    @Override
    public BaseDAO createAddressTypeDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createPhoneTypeDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createDocumentTypeDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createUserDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createTypeDAO(String entityName, String tableName) throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createBankDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createCurrencyDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createExchangeRateDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createAgreementDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createAccountDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createCardDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createUserRoleDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createCommandDAO() throws SQLException {
        return null;
    }

    @Override
    public BaseDAO createUserRoleCommandDAO() throws SQLException {
        return null;
    }
}
