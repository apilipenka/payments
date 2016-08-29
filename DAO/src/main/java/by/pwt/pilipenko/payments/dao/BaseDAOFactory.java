package by.pwt.pilipenko.payments.dao;

import by.pwt.pilipenko.payments.dao.jdbc.*;

import java.sql.SQLException;


public interface BaseDAOFactory {


        public abstract void beginTransaction() throws SQLException;
        public abstract void endTransaction() throws SQLException;

        public abstract void commit() throws SQLException;

        public abstract void rollback() throws SQLException;

        public abstract BaseDAO createAddressTypeDAO() throws SQLException;

        public abstract BaseDAO createPhoneTypeDAO() throws SQLException;

        public abstract BaseDAO createDocumentTypeDAO() throws SQLException;

        public abstract BaseDAO createUserDAO() throws SQLException;

        public abstract BaseDAO createTypeDAO(String entityName, String tableName) throws SQLException;


        public abstract BaseDAO createBankDAO() throws SQLException;


        public abstract BaseDAO createCurrencyDAO() throws SQLException;

        public abstract BaseDAO createExchangeRateDAO() throws SQLException;

        public abstract BaseDAO createAgreementDAO() throws SQLException;

        public abstract BaseDAO createAccountDAO() throws SQLException;

        public abstract BaseDAO createCardDAO() throws SQLException;

        public abstract BaseDAO createUserRoleDAO() throws SQLException;

        public abstract BaseDAO createCommandDAO() throws SQLException;

        public abstract BaseDAO createUserRoleCommandDAO() throws SQLException;
    }
