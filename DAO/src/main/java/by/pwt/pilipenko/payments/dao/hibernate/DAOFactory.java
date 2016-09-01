package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;

import javax.naming.NamingException;
import java.sql.SQLException;

public class DAOFactory extends AbstractDAOFactory {


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
        return new UserDAO(getSession());
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
        return new CurrencyDAO(getSession());
    }

    @Override
    public BaseDAO createExchangeRateDAO() throws SQLException {
        return new ExchangeRateDAO(getSession());
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
        return new UserRoleDAO(getSession());
    }

    @Override
    public BaseDAO createCommandDAO() throws SQLException {
        return new CommandDAO(getSession());
    }

    @Override
    public BaseDAO createUserRoleCommandDAO() throws SQLException {
        return null;
    }
}
