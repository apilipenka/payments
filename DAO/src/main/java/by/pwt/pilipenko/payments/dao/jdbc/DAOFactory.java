package by.pwt.pilipenko.payments.dao.jdbc;

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
            setDataSource((DataSource) new PoolingDataSource<>(connectionPool));
            setConnection(getDataSource().getConnection());

        }

    }

    public DAOFactory(DataSource dataSource) throws NamingException, SQLException {
        super();

        this.setDataSource(dataSource);
        setConnection(this.getDataSource().getConnection());


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
        userDAO = new UserDAO(getConnection(), "user", "users");

        return userDAO;

    }

    public TypeDAO createTypeDAO(String entityName, String tableName) throws SQLException {
        TypeDAO typeDAO;
        typeDAO = new TypeDAO(getConnection(), entityName, tableName);

        return typeDAO;
    }


    public BankDAO createBankDAO() throws SQLException {
        BankDAO bankDAO;
        bankDAO = new BankDAO(getConnection(), "bank", "banks");

        return bankDAO;
    }


    public CurrencyDAO createCurrencyDAO() throws SQLException {
        CurrencyDAO currencyDAO;
        currencyDAO = new CurrencyDAO(getConnection(), "currency", "currencies");
        return currencyDAO;
    }

    public ExchangeRateDAO createExchangeRateDAO() throws SQLException {
        ExchangeRateDAO exchangeRateDAO;
        exchangeRateDAO = new ExchangeRateDAO(getConnection(), "exchangerate", "exchange_rates");
        return exchangeRateDAO;
    }

    public AgreementDAO createAgreementDAO() throws SQLException {
        AgreementDAO agreementDAO;
        agreementDAO = new AgreementDAO(getConnection(), "agreement", "agreements");
        return agreementDAO;
    }

    public AccountDAO createAccountDAO() throws SQLException {
        AccountDAO accountDAO;
        accountDAO = new AccountDAO(getConnection(), "account", "accounts");
        return accountDAO;
    }

    public CardDAO createCardDAO() throws SQLException {
        CardDAO cardDAO;
        cardDAO = new CardDAO(getConnection(), "card", "cards");
        return cardDAO;
    }


    public UserRoleDAO createUserRoleDAO() throws SQLException {
        UserRoleDAO userRoleDAO;
        userRoleDAO = new UserRoleDAO(getConnection(), "userrole", "user_roles");
        return userRoleDAO;
    }

    public CommandDAO createCommandDAO() throws SQLException {
        CommandDAO commandDAO;
        commandDAO = new CommandDAO(getConnection(), "command", "commands");
        return commandDAO;
    }

    @Override
    public UserRoleCommandDAO createUserRoleCommandDAO() throws SQLException {
        UserRoleCommandDAO userRoleCommandDAO;
        userRoleCommandDAO = new UserRoleCommandDAO(getConnection(), "userrolecommand", "user_role_commands");
        return userRoleCommandDAO;
    }

}