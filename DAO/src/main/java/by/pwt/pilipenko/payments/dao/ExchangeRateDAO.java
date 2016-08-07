package by.pwt.pilipenko.payments.dao;

import by.pwt.plipenko.payments.model.entities.Currency;
import by.pwt.plipenko.payments.model.entities.ExchangeRate;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeRateDAO extends AbstractEntityDAO<ExchangeRate> {

    public ExchangeRateDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected ExchangeRate getEntity(ResultSet resultSet) throws SQLException, NamingException {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(resultSet.getInt("id"));
        exchangeRate.setRateDate(resultSet.getDate("rate_date"));
        exchangeRate.setRate(resultSet.getFloat("rate"));

        int currencyId = resultSet.getInt("currency_id");
        CurrencyDAO currencyDao = DAOFactory.getInstance().createCurrencyDAO();
        Currency currency = currencyDao.findEntityById(currencyId);
        exchangeRate.setCurrency(currency);

        return exchangeRate;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(ExchangeRate entity, PreparedStatement statement)
            throws SQLException {

        statement.setDate(1, new java.sql.Date(entity.getRateDate().getTime()));
        statement.setInt(2, entity.getCurrency().getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(ExchangeRate entity, PreparedStatement statement)
            throws SQLException {

        statement.setDate(1, new java.sql.Date(entity.getRateDate().getTime()));
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(ExchangeRate entity, PreparedStatement statement)
            throws SQLException {
        statement.setDate(1, new java.sql.Date(entity.getRateDate().getTime()));
        statement.setFloat(2, entity.getRate());
        statement.setInt(3, entity.getCurrency().getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(ExchangeRate entity, PreparedStatement statement)
            throws SQLException {
        statement.setDate(1, new java.sql.Date(entity.getRateDate().getTime()));
        statement.setFloat(2, entity.getRate());
        statement.setInt(3, entity.getCurrency().getId());
        statement.setInt(4, entity.getId());
        return statement;
    }

}
