package by.pwt.pilipenko.payments.dao;

import by.pwt.pilipenko.payments.dao.jdbc.*;

import java.sql.SQLException;


public interface BaseDAOFactory {


        public abstract void beginTransaction() throws SQLException;

        public abstract void commit() throws SQLException;

        public abstract void rollback() throws SQLException;

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
    }
