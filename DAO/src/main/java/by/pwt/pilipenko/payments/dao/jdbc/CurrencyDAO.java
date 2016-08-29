package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.model.entities.Currency;

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
        currency.setCode(resultSet.getString("code"));
        currency.setMnemoCode(resultSet.getString("mnemo_code"));
        currency.setName(resultSet.getString("name"));
        return currency;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getMnemoCode());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getMnemoCode());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getCode());
        statement.setString(2, entity.getMnemoCode());
        statement.setString(3, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Currency entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getCode());
        statement.setString(2, entity.getMnemoCode());
        statement.setString(3, entity.getName());
        statement.setInt(4, entity.getId());
        return statement;
    }

}
