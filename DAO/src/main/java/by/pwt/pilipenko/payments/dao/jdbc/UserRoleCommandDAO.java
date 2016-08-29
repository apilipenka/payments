package by.pwt.pilipenko.payments.dao.jdbc;

/**
 * Created by apilipenka on 8/22/2016.
 */

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Command;
import by.pwt.pilipenko.payments.model.entities.UserRole;
import by.pwt.pilipenko.payments.model.entities.UserRoleCommand;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRoleCommandDAO extends AbstractEntityDAO<UserRoleCommand> {

    public UserRoleCommandDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected UserRoleCommand getEntity(ResultSet resultSet) throws SQLException, NamingException, ClassNotFoundException {
        UserRoleCommand userRoleCommand = new UserRoleCommand();
        userRoleCommand.setId(resultSet.getInt("id"));

        int roleId = resultSet.getInt("user_role_id");
        BaseDAO typeDao = DaoFactoryFactory.getInstance().createUserRoleDAO();
        UserRole userRole = (UserRole) typeDao.findEntityById(roleId);
        userRoleCommand.setUserRole(userRole);

        int commandId = resultSet.getInt("command_id");
        BaseDAO commandDAO = DaoFactoryFactory.getInstance().createCommandDAO();
        Command command = (Command) commandDAO.findEntityById(commandId);
        userRoleCommand.setCommand(command);


        return userRoleCommand;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(UserRoleCommand entity, PreparedStatement statement)
            throws SQLException {
        statement.setInt(1, entity.getUserRole().getId());
        statement.setInt(2, entity.getCommand().getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(UserRoleCommand entity, PreparedStatement statement)
            throws SQLException {
        statement.setInt(1, entity.getUserRole().getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(UserRoleCommand entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getUserRole().getId());
        statement.setInt(2, entity.getCommand().getId());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(UserRoleCommand entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getUserRole().getId());
        statement.setInt(2, entity.getCommand().getId());
        statement.setInt(3, entity.getId());
        return statement;
    }

}

