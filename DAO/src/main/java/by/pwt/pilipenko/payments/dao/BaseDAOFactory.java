package by.pwt.pilipenko.payments.dao;

import java.sql.SQLException;


public interface BaseDAOFactory {


    void beginTransaction() throws SQLException;

    void endTransaction() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    boolean isParentTransactionStarted();

    BaseDAO createAddressTypeDAO() throws SQLException;

    BaseDAO createPhoneTypeDAO() throws SQLException;

    BaseDAO createDocumentTypeDAO() throws SQLException;

    BaseDAO createUserDAO() throws SQLException;

    BaseDAO createTypeDAO(String entityName, String tableName) throws SQLException;


    BaseDAO createBankDAO() throws SQLException;


    BaseDAO createCurrencyDAO() throws SQLException;

    BaseDAO createExchangeRateDAO() throws SQLException;

    BaseDAO createAgreementDAO() throws SQLException;

    BaseDAO createAccountDAO() throws SQLException;

    BaseDAO createCardDAO() throws SQLException;

    BaseDAO createUserRoleDAO() throws SQLException;

    BaseDAO createCommandDAO() throws SQLException;

    BaseDAO createUserRoleCommandDAO() throws SQLException;


}
