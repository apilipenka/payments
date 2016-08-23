package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDAO extends AbstractEntityDAO<User> {

    public UserDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected User getEntity(ResultSet resultSet) throws SQLException, NamingException, ClassNotFoundException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setPassword(resultSet.getString("password"));
        user.setPersonalNumber(resultSet.getString("personal_number"));

        Date birthDate = resultSet.getDate("birth_date");
        user.setBirthDate(birthDate);

        int roleId = resultSet.getInt("user_role_id");
        UserRoleDAO typeDao = DaoFactoryFactory.getInstance().createUserRoleDAO();
        UserRole userRole = (UserRole) typeDao.findEntityById(roleId);
        user.setUserRole(userRole);

        return user;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(User entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getLogin());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(User entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getPersonalNumber());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getPassword());
        statement.setString(5, entity.getPersonalNumber());
        statement.setInt(6, entity.getUserRole().getId());
        statement.setDate(7, new java.sql.Date(entity.getBirthDate().getTime()));


        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getPassword());
        statement.setString(5, entity.getPersonalNumber());
        statement.setInt(6, entity.getUserRole().getId());
        statement.setDate(7, new java.sql.Date(entity.getBirthDate().getTime()));
        statement.setInt(8, entity.getId());
        return statement;
    }

}
