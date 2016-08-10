package by.pwt.pilipenko.payments.dao;

import by.pwt.plipenko.payments.model.entities.Command;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandDAO extends AbstractEntityDAO<Command> {

    public CommandDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected Command getEntity(ResultSet resultSet) throws SQLException, NamingException {
        Command command = new Command();

        command.setId(resultSet.getInt("id"));
        command.setCommand(resultSet.getString("command"));
        command.setUrl(resultSet.getString("url"));
        command.setLabel(resultSet.getString("label"));
        command.setComment(resultSet.getString("comment"));


        return command;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Command entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getCommand());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Command entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getCommand());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Command entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getCommand());
        statement.setString(2, entity.getUrl());
        statement.setString(3, entity.getLabel());
        statement.setString(4, entity.getComment());

        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Command entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getCommand());
        statement.setString(2, entity.getUrl());
        statement.setString(3, entity.getLabel());
        statement.setString(4, entity.getComment());
        statement.setInt(5, entity.getId());
        return statement;
    }

}
