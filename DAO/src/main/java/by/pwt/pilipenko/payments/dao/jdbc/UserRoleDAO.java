package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.plipenko.payments.model.entities.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleDAO extends AbstractEntityDAO<UserRole> {

    public UserRoleDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected UserRole getEntity(ResultSet resultSet) throws SQLException {
        UserRole type = new UserRole();
        type.setId(resultSet.getInt("id"));
        type.setName(resultSet.getString("name"));
        type.setDescription(resultSet.getString("description"));
        return type;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(UserRole entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(UserRole entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getName());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(UserRole entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(UserRole entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setInt(3, entity.getId());
        return statement;
    }

}
