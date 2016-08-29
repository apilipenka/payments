package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Account;
import by.pwt.pilipenko.payments.model.entities.Agreement;
import by.pwt.pilipenko.payments.model.entities.Currency;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDAO extends AbstractEntityDAO<Account> {

    public AccountDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected Account getEntity(ResultSet resultSet) throws SQLException, NamingException, ClassNotFoundException {
        Account account = new Account();

        account.setId(resultSet.getInt("id"));
        account.setNumber(resultSet.getString("number"));
        account.setAmount(resultSet.getDouble("amount"));

        int agrremntId = resultSet.getInt("agreement_id");

        BaseDAO agreemntDao = DaoFactoryFactory.getInstance().createAgreementDAO();
        Agreement bank = (Agreement) agreemntDao.findEntityById(agrremntId);
        account.setAgreement(bank);

        int currencyId = resultSet.getInt("currency_id");
        BaseDAO currencyDAO = DaoFactoryFactory.getInstance().createCurrencyDAO();
        Currency currency = (Currency) currencyDAO.findEntityById(currencyId);
        account.setCurrency(currency);


        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(currency);
        BaseDAO exchangeRateDAO = DaoFactoryFactory.getInstance().createExchangeRateDAO();
        List<ExchangeRate> list = ((ExchangeRateDAO)exchangeRateDAO).findEntityByParent(exchangeRate);
        Set<ExchangeRate> set = new HashSet<>();
        set.addAll(list);
        currency.setRates(set);

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
