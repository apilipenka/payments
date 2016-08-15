package by.pwt.pilipenko.payments.dao;

import by.pwt.plipenko.payments.model.entities.Account;
import by.pwt.plipenko.payments.model.entities.Agreement;
import by.pwt.plipenko.payments.model.entities.Currency;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends AbstractEntityDAO<Account> {

    public AccountDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected Account getEntity(ResultSet resultSet) throws SQLException, NamingException {
        Account account = new Account();

        account.setId(resultSet.getInt("id"));
        account.setNumber(resultSet.getString("number"));
        account.setAmount(resultSet.getDouble("amount"));

        int agrremntId = resultSet.getInt("agreement_id");

        AgreementDAO agrremntDao = DAOFactory.getInstance().createAgreementDAO();
        Agreement bank = agrremntDao.findEntityById(agrremntId);
        account.setAgreement(bank);

        int currencyId = resultSet.getInt("currency_id");
        CurrencyDAO currencyDAO = DAOFactory.getInstance().createCurrencyDAO();
        Currency currency = currencyDAO.findEntityById(currencyId);
        account.setCurrency(currency);

        return account;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Account entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getNumber());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Account entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getNumber());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Account entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setDouble(2, entity.getAmount());
        statement.setInt(3, entity.getAgreement().getId());
        statement.setInt(4, entity.getCurrency().getId());

        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Account entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setDouble(2, entity.getAmount());
        statement.setInt(3, entity.getAgreement().getId());
        statement.setInt(4, entity.getCurrency().getId());
        statement.setInt(5, entity.getId());
        return statement;
    }

}
