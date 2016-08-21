package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.plipenko.payments.model.entities.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDAO extends AbstractEntityDAO<Bank> {

    public BankDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected Bank getEntity(ResultSet resultSet) throws SQLException {

        Bank bank = new Bank();
        bank.setId(resultSet.getInt("id"));
        bank.setName(resultSet.getString("name"));
        bank.setUNN(resultSet.getString("unn"));

        return bank;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Bank entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Bank entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getUNN());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Bank entity, PreparedStatement statement) throws SQLException {

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getUNN());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Bank entity, PreparedStatement statement) throws SQLException {

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getUNN());
        statement.setInt(3, entity.getId());

        return statement;
    }

}
