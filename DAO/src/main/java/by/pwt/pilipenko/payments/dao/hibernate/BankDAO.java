package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.jdbc.AbstractEntityDAO;
import by.pwt.pilipenko.payments.model.entities.Bank;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankDAO implements BaseDAO<Bank> {


    Session session;

    public BankDAO() {
    }

    public BankDAO(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean update(Bank entity) throws SQLException, NamingException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException, NamingException {
        return false;
    }

    @Override
    public boolean delete(Bank entity) throws SQLException, NamingException {
        return false;
    }

    @Override
    public Bank findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Bank> findEntityByEntity(Bank entity) throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public Bank findEntityByPK(Bank entity) throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Bank> findAll() throws SQLException, NamingException, ClassNotFoundException {
        return null;
    }

    @Override
    public Bank insert(Bank entity) throws SQLException, NamingException {
        session.save(entity);
        return entity;
    }
}
