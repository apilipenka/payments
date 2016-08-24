package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.Agreement;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgreementDAO extends AbstractEntityDAO<Agreement> {

    public AgreementDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected Agreement getEntity(ResultSet resultSet) throws SQLException, NamingException, ClassNotFoundException {
        Agreement agreement = new Agreement();

        agreement.setId(resultSet.getInt("id"));
        agreement.setNumber(resultSet.getString("number"));
        agreement.setValidFromDate(resultSet.getDate("valid_from_date"));
        agreement.setValidToDate(resultSet.getDate("valid_to_date"));

        int bankId = resultSet.getInt("bank_id");
        BankDAO bankDao = DaoFactoryFactory.getInstance().createBankDAO();
        Bank bank = bankDao.findEntityById(bankId);
        agreement.setBank(bank);

        int clientId = resultSet.getInt("user_id");
        UserDAO userDAO = DaoFactoryFactory.getInstance().createUserDAO();
        User user = userDAO.findEntityById(clientId);
        agreement.setClient(user);

        return agreement;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Agreement entity, PreparedStatement statement)
            throws SQLException {

        statement.setInt(1, entity.getBank().getId());
        statement.setString(2, entity.getNumber());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Agreement entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getNumber());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Agreement entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setDate(2, new java.sql.Date(entity.getValidFromDate().getTime()));
        statement.setDate(3, new java.sql.Date(entity.getValidToDate().getTime()));
        statement.setInt(4, entity.getBank().getId());
        statement.setInt(5, entity.getClient().getId());

        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Agreement entity, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setDate(2, new java.sql.Date(entity.getValidFromDate().getTime()));
        statement.setDate(3, new java.sql.Date(entity.getValidToDate().getTime()));
        statement.setInt(4, entity.getBank().getId());
        statement.setInt(5, entity.getClient().getId());
        statement.setInt(6, entity.getId());
        return statement;
    }

}
