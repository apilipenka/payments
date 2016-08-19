package by.pwt.pilipenko.payments.dao;

import by.pwt.pilipenko.payments.dao.resources.QueriesManager;
import by.pwt.plipenko.payments.model.entities.Currency;
import by.pwt.plipenko.payments.model.entities.ExchangeRate;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExchangeRateDAO extends AbstractEntityDAO<ExchangeRate> {

    private String selectByParentStatement = null;

    public ExchangeRateDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);


        this.selectByParentStatement = QueriesManager.getProperty("exchangerate.selectByParentStatement")
                .replace("#TABLE_NAME#", tableName);

    }

    @Override
    protected ExchangeRate getEntity(ResultSet resultSet) throws SQLException, NamingException {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(resultSet.getInt("id"));
        exchangeRate.setRateDate(resultSet.getDate("rate_date"));
        exchangeRate.setRate(resultSet.getFloat("rate"));
        int currencyId = resultSet.getInt("currency_id");
        int targetCurrencyId = resultSet.getInt("target_currency_id");
        CurrencyDAO currencyDao = DaoFactoryFactory.getInstance().createCurrencyDAO();
        Currency currency = currencyDao.findEntityById(currencyId);
        exchangeRate.setCurrency(currency);
        currency = currencyDao.findEntityById(targetCurrencyId);
        exchangeRate.setTargetCurrency(currency);

        return exchangeRate;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(ExchangeRate entity, PreparedStatement statement)
            throws SQLException {

        statement.setDate(1, new java.sql.Date(entity.getRateDate().getTime()));
        statement.setInt(2, entity.getCurrency().getId());
        statement.setInt(3, entity.getTargetCurrency().getId());
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
        statement.setInt(4, entity.getTargetCurrency().getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(ExchangeRate entity, PreparedStatement statement)
            throws SQLException {
        statement.setDate(1, new java.sql.Date(entity.getRateDate().getTime()));
        statement.setFloat(2, entity.getRate());
        statement.setInt(3, entity.getCurrency().getId());
        statement.setInt(4, entity.getTargetCurrency().getId());
        statement.setInt(5, entity.getId());
        return statement;
    }

    protected PreparedStatement prepareSelectByParentStatement(ExchangeRate entity, PreparedStatement statement)
            throws SQLException {

        statement.setInt(1, entity.getCurrency().getId());
        if (entity.getTargetCurrency() != null) {
            statement.setInt(2, entity.getTargetCurrency().getId());
        } else {
            statement.setNull(2, java.sql.Types.INTEGER);
        }

        return statement;
    }

    public List<ExchangeRate> findEntityByParent(ExchangeRate entity) throws SQLException, NamingException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ExchangeRate> entities = null;
        try {
            statement = getConnection().prepareStatement(selectByParentStatement);
            statement = prepareSelectByParentStatement(entity, statement);
            resultSet = statement.executeQuery();
            entities = getEntities(resultSet);

        } catch (SQLException e) {
            // e.printStackTrace();
            // return null;
            throw e;
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return entities;
    }


}
