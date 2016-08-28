package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAOFactory;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by apilipenka on 8/19/2016.
 */
abstract class AbstractDAOFactory implements BaseDAOFactory {
    private DataSource dataSource;
    private Connection connection;

    DataSource getDataSource() { return dataSource; }

    void setDataSource(DataSource dataSource) { this.dataSource = dataSource; }

    public Connection getConnection() throws SQLException {
        Connection localInstance = connection;

        if (localInstance == null) {

            synchronized (AbstractDAOFactory.class) {
                localInstance = connection;
                if (localInstance == null) {
                    connection=dataSource.getConnection();
                    localInstance = connection;

                }

            }

        }

        return localInstance;

    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void endTransaction() throws SQLException {
        connection.setAutoCommit(true);
    }

    public abstract TypeDAO createAddressTypeDAO() throws SQLException;

    public abstract TypeDAO createPhoneTypeDAO() throws SQLException;

    public abstract TypeDAO createDocumentTypeDAO() throws SQLException;

    public abstract UserDAO createUserDAO() throws SQLException;

    public abstract TypeDAO createTypeDAO(String entityName, String tableName) throws SQLException;


    public abstract BankDAO createBankDAO() throws SQLException;


    public abstract CurrencyDAO createCurrencyDAO() throws SQLException;

    public abstract ExchangeRateDAO createExchangeRateDAO() throws SQLException;

    public abstract AgreementDAO createAgreementDAO() throws SQLException;

    public abstract AccountDAO createAccountDAO() throws SQLException;

    public abstract CardDAO createCardDAO() throws SQLException;

    public abstract UserRoleDAO createUserRoleDAO() throws SQLException;

    public abstract CommandDAO createCommandDAO() throws SQLException;

    public abstract UserRoleCommandDAO createUserRoleCommandDAO() throws SQLException;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}


