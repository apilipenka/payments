package by.pwt.pilipenko.payments.dao;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by apilipenka on 8/19/2016.
 */
public abstract class  AbstractDAOFactory {
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public abstract TypeDAO createAddressTypeDAO() throws SQLException;

    public  abstract TypeDAO createPhoneTypeDAO() throws SQLException;

    public abstract TypeDAO createDocumentTypeDAO() throws SQLException;

    public abstract UserDAO createUserDAO() throws SQLException;

    public abstract TypeDAO createTypeDAO(String entityName, String tableName) throws SQLException ;


    public abstract BankDAO createBankDAO() throws SQLException;


    public abstract CurrencyDAO createCurrencyDAO() throws SQLException;

    public abstract ExchangeRateDAO createExchangeRateDAO() throws SQLException;

    public abstract AgreementDAO createAgreementDAO() throws SQLException;

    public abstract AccountDAO createAccountDAO() throws SQLException;

    public abstract CardDAO createCardDAO() throws SQLException;

    public abstract UserRoleDAO createUserRoleDAO() throws SQLException;

    public abstract CommandDAO createCommandDAO() throws SQLException;


}


