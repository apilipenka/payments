package by.pwt.pilipenko.payments.dao;

import by.pwt.plipenko.payments.model.entities.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CurrencyDAO extends AbstractEntityDAO<Currency> {

    public CurrencyDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);

    }

    @Override
    protected Currency getEntity(ResultSet resultSet) throws SQLException {
        Currency currency = new Currency();
        currency.setId(resultSet.getInt("id"));
        currency.setName(resultSet.getString("name"));
        return currency;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        statement.setInt(3, entity.getId());
        return statement;
    }

}
