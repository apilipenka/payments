package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.jdbc.AbstractEntityDAO;
import by.pwt.pilipenko.payments.model.entities.Bank;
import by.pwt.pilipenko.payments.model.entities.Command;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommandDAO implements BaseDAO<Command> {
    Session session;

    public CommandDAO() {
    }

    public CommandDAO(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    @Override
    public boolean update(Command entity) throws SQLException, NamingException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException, NamingException {
        return false;
    }

    @Override
    public boolean delete(Command entity) throws SQLException, NamingException {
        return false;
    }

    @Override
    public Command findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Command> findEntityByEntity(Command entity) throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public Command findEntityByPK(Command entity) throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Command> findAll() throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public Command insert(Command entity) throws SQLException, NamingException {
        return null;
    }
}
