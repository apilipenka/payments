package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.Account;
import by.pwt.plipenko.payments.model.entities.Card;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO extends AbstractEntityDAO<Card> {

    public CardDAO(Connection connection, String entityName, String tableName) {
        super(connection, entityName, tableName);
    }

    @Override
    protected Card getEntity(ResultSet resultSet) throws SQLException, NamingException {
        Card card = new Card();

        card.setId(resultSet.getInt("id"));
        card.setNumber(resultSet.getString("number"));
        card.setName(resultSet.getString("name"));
        card.setValidToDate(resultSet.getDate("valid_to_date"));

        int accountId = resultSet.getInt("accounts_id");
        AccountDAO accountDAO = DaoFactoryFactory.getInstance().createAccountDAO();
        Account account = accountDAO.findEntityById(accountId);
        card.setAccount(account);

        return card;
    }

    @Override
    protected PreparedStatement prepareSelectByPKStatement(Card entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getNumber());
        return statement;
    }

    @Override
    protected PreparedStatement prepareSelectByEntityStatement(Card entity, PreparedStatement statement)
            throws SQLException {

        statement.setString(1, entity.getNumber());
        return statement;
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Card entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setString(2, entity.getName());
        statement.setDate(3, new java.sql.Date(entity.getValidToDate().getTime()));
        statement.setInt(4, entity.getAccount().getId());

        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Card entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getNumber());
        statement.setString(2, entity.getName());
        statement.setDate(3, new java.sql.Date(entity.getValidToDate().getTime()));
        statement.setInt(4, entity.getAccount().getId());
        statement.setInt(5, entity.getId());
        return statement;
    }

}
