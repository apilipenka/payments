package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.plipenko.payments.model.entities.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDAO extends AbstractEntityDAO<Type> {

    public TypeDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected Type getEntity(ResultSet resultSet) throws SQLException {
        Type type = new Type();
        type.setId(resultSet.getInt("id"));
        type.setName(resultSet.getString("name"));
        type.setDescription(resultSet.getString("description"));
        return type;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Type entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Type entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Type entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Type entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setInt(3, entity.getId());
        return statement;
    }

}
