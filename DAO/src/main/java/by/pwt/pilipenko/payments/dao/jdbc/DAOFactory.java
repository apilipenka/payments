package by.pwt.pilipenko.payments.dao.jdbc;

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

public class DAOFactory extends AbstractDAOFactory {


    public DAOFactory() throws NamingException, ClassNotFoundException, SQLException {
        super();

        Context initContext;
        try {
            initContext = new InitialContext();
            setDataSource((DataSource) initContext.lookup("java:comp/env/jdbc/mysqldb"));
            setConnection(getDataSource().getConnection());

        } catch (NamingException e) {
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
            setDataSource(new PoolingDataSource<>(connectionPool));
            setConnection(getDataSource().getConnection());

        }

    }

    public DAOFactory(DataSource dataSource) throws NamingException, SQLException {
        super();

        this.setDataSource(dataSource);
        setConnection(this.getDataSource().getConnection());


    }


    public BaseDAO createAddressTypeDAO() throws SQLException {

        return createTypeDAO("type", "address_types");
    }

    public BaseDAO createPhoneTypeDAO() throws SQLException {

        return createTypeDAO("type", "phone_types");
    }

    public BaseDAO createDocumentTypeDAO() throws SQLException {

        return createTypeDAO("type", "document_types");
    }

    public BaseDAO createUserDAO() throws SQLException {
        UserDAO userDAO;
        userDAO = new UserDAO(getConnection(), "user", "users");

        return userDAO;

    }

    public BaseDAO createTypeDAO(String entityName, String tableName) throws SQLException {
        TypeDAO typeDAO;
        typeDAO = new TypeDAO(getConnection(), entityName, tableName);

        return typeDAO;
    }


    public BaseDAO createBankDAO() throws SQLException {
        BankDAO bankDAO;
        bankDAO = new BankDAO(getConnection(), "bank", "banks");

        return bankDAO;
    }


    public BaseDAO createCurrencyDAO() throws SQLException {
        CurrencyDAO currencyDAO;
        currencyDAO = new CurrencyDAO(getConnection(), "currency", "currencies");
        return currencyDAO;
    }

    public BaseDAO createExchangeRateDAO() throws SQLException {
        ExchangeRateDAO exchangeRateDAO;
        exchangeRateDAO = new ExchangeRateDAO(getConnection(), "exchangerate", "exchange_rates");
        return exchangeRateDAO;
    }

    public BaseDAO createAgreementDAO() throws SQLException {
        AgreementDAO agreementDAO;
        agreementDAO = new AgreementDAO(getConnection(), "agreement", "agreements");
        return agreementDAO;
    }

    public AccountDAO createAccountDAO() throws SQLException {
        AccountDAO accountDAO;
        accountDAO = new AccountDAO(getConnection(), "account", "accounts");
        return accountDAO;
    }

    public BaseDAO createCardDAO() throws SQLException {
        CardDAO cardDAO;
        cardDAO = new CardDAO(getConnection(), "card", "cards");
        return cardDAO;
    }


    public BaseDAO createUserRoleDAO() throws SQLException {
        UserRoleDAO userRoleDAO;
        userRoleDAO = new UserRoleDAO(getConnection(), "userrole", "user_roles");
        return userRoleDAO;
    }

    public BaseDAO createCommandDAO() throws SQLException {
        CommandDAO commandDAO;
        commandDAO = new CommandDAO(getConnection(), "command", "commands");
        return commandDAO;
    }

    @Override
    public BaseDAO createUserRoleCommandDAO() throws SQLException {
        UserRoleCommandDAO userRoleCommandDAO;
        userRoleCommandDAO = new UserRoleCommandDAO(getConnection(), "userrolecommand", "user_role_commands");
        return userRoleCommandDAO;
    }

}