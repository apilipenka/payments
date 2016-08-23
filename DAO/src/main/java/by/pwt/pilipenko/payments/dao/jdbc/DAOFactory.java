package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.AbstractDAOFactory;
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

public class DAOFactory extends AbstractDAOFactory {


    public DAOFactory() throws NamingException, ClassNotFoundException {
        super();

        Context initContext;
        try {
            initContext = new InitialContext();
            setDataSource((DataSource) initContext.lookup("java:comp/env/jdbc/mysqldb"));

        } catch (NamingException e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/payments?autoReconnect=true&useSSL=false&autocommit=1", "root", "awp1977");


                PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
                        null);

                ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
                ((GenericObjectPool) connectionPool).setMaxTotal(150);
                ((GenericObjectPool) connectionPool).setMinIdle(10);
                ((GenericObjectPool) connectionPool).setMaxIdle(50);
                ((GenericObjectPool) connectionPool).setMaxWaitMillis(10000);
                ((GenericObjectPool) connectionPool).setMinEvictableIdleTimeMillis(60000);
                AbandonedConfig abandonedConfig = new AbandonedConfig();
                abandonedConfig.setRemoveAbandonedTimeout(60);
                abandonedConfig.setRemoveAbandonedOnBorrow(true);
                abandonedConfig.setRemoveAbandonedOnMaintenance(true);

                ((GenericObjectPool) connectionPool).setAbandonedConfig(abandonedConfig);


                poolableConnectionFactory.setPool(connectionPool);
                setDataSource((DataSource) new PoolingDataSource<>(connectionPool));


            } catch (ClassNotFoundException e1) {
                throw e1;
            }

        }

    }

    public DAOFactory(DataSource dataSource) throws NamingException {
        super();

        this.setDataSource(dataSource);

    }


    public TypeDAO createAddressTypeDAO() throws SQLException {

        return createTypeDAO("type", "address_types");
    }

    public TypeDAO createPhoneTypeDAO() throws SQLException {

        return createTypeDAO("type", "phone_types");
    }

    public TypeDAO createDocumentTypeDAO() throws SQLException {

        return createTypeDAO("type", "document_types");
    }

    public UserDAO createUserDAO() throws SQLException {
        UserDAO userDAO;
        try {
            userDAO = new UserDAO(getDataSource().getConnection(), "user", "users");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }

        return userDAO;

    }

    public TypeDAO createTypeDAO(String entityName, String tableName) throws SQLException {
        TypeDAO typeDAO;
        try {
            typeDAO = new TypeDAO(getDataSource().getConnection(), entityName, tableName);
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }

        return typeDAO;
    }


    public BankDAO createBankDAO() throws SQLException {
        BankDAO bankDAO;
        try {
            bankDAO = new BankDAO(getDataSource().getConnection(), "bank", "banks");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }

        return bankDAO;
    }


    public CurrencyDAO createCurrencyDAO() throws SQLException {
        CurrencyDAO currencyDAO;
        try {
            currencyDAO = new CurrencyDAO(getDataSource().getConnection(), "currency", "currencies");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return currencyDAO;
    }

    public ExchangeRateDAO createExchangeRateDAO() throws SQLException {
        ExchangeRateDAO exchangeRateDAO;
        try {
            exchangeRateDAO = new ExchangeRateDAO(getDataSource().getConnection(), "exchangerate", "exchange_rates");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return exchangeRateDAO;
    }

    public AgreementDAO createAgreementDAO() throws SQLException {
        AgreementDAO agreementDAO;
        try {
            agreementDAO = new AgreementDAO(getDataSource().getConnection(), "agreement", "agreements");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return agreementDAO;
    }

    public AccountDAO createAccountDAO() throws SQLException {
        AccountDAO accountDAO;
        try {
            accountDAO = new AccountDAO(getDataSource().getConnection(), "account", "accounts");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return accountDAO;
    }

    public CardDAO createCardDAO() throws SQLException {
        CardDAO cardDAO;
        try {
            cardDAO = new CardDAO(getDataSource().getConnection(), "card", "cards");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return cardDAO;
    }


    public UserRoleDAO createUserRoleDAO() throws SQLException {
        UserRoleDAO userRoleDAO;
        try {
            userRoleDAO = new UserRoleDAO(getDataSource().getConnection(), "userrole", "user_roles");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return userRoleDAO;
    }

    public CommandDAO createCommandDAO() throws SQLException {
        CommandDAO commandDAO;
        try {
            commandDAO = new CommandDAO(getDataSource().getConnection(), "command", "commands");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return commandDAO;
    }

    @Override
    public UserRoleCommandDAO createUserRoleCommandDAO() throws SQLException {
        UserRoleCommandDAO userRoleCommandDAO;
        try {
            userRoleCommandDAO = new UserRoleCommandDAO(getDataSource().getConnection(), "userrolecommand", "user_role_commands");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return userRoleCommandDAO;
    }

}