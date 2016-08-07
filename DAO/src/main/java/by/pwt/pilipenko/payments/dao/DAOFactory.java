package by.pwt.pilipenko.payments.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DAOFactory {
    private static DAOFactory instance;
    private DataSource dataSource;

    private DAOFactory() throws NamingException {
        super();

        Context initContext;
        try {
            initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/mysqldb");

        } catch (NamingException e) {
            // e.printStackTrace();
            throw e;
        }

    }

    public static DAOFactory getInstance() throws NamingException {

        DAOFactory localInstance = instance;

        if (localInstance == null) {

            synchronized (DAOFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DAOFactory();
                }

            }

        }

        return localInstance;
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
            userDAO = new UserDAO(dataSource.getConnection(), "user", "users");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }

        return userDAO;

    }

    private TypeDAO createTypeDAO(String entityName, String tableName) throws SQLException {
        TypeDAO typeDAO;
        try {
            typeDAO = new TypeDAO(dataSource.getConnection(), entityName, tableName);
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
            bankDAO = new BankDAO(dataSource.getConnection(), "bank", "banks");
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
            currencyDAO = new CurrencyDAO(dataSource.getConnection(), "currency", "currencies");
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
            exchangeRateDAO = new ExchangeRateDAO(dataSource.getConnection(), "exchangerate", "exchange_rates");
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
            agreementDAO = new AgreementDAO(dataSource.getConnection(), "agreement", "agreements");
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
            accountDAO = new AccountDAO(dataSource.getConnection(), "account", "accounts");
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
            cardDAO = new CardDAO(dataSource.getConnection(), "card", "cards");
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
            userRoleDAO = new UserRoleDAO(dataSource.getConnection(), "userrole", "user_roles");
        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        }
        return userRoleDAO;
    }

}