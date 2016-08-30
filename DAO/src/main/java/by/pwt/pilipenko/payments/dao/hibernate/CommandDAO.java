package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.jdbc.AbstractEntityDAO;
import by.pwt.pilipenko.payments.model.entities.Bank;
import by.pwt.pilipenko.payments.model.entities.Command;
import org.hibernate.Query;
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
        session.saveOrUpdate(entity);
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException, NamingException, ClassNotFoundException {

        Command command = findEntityById(id);
        session.delete(command);

        return false;
    }

    @Override
    public boolean delete(Command entity) throws SQLException, NamingException {

        Query query = session.createQuery("delete from Command where command = :command");
        query.setParameter("command", entity.getCommand());
        int result = query.executeUpdate();

        return true;
    }

    @Override
    public Command findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException {

        Command command = (Command) getSession().get(Command.class, id);
        return command;

    }

    @Override
    public List<Command> findEntityByEntity(Command entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from Command where command=COALESCE(:command, command)");
        query.setParameter("command", entity.getCommand());
        List<Command> list = (List<Command>) query.list();
        return list;
    }

    @Override
    public Command findEntityByPK(Command entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from Command where command=:command");
        query.setParameter("command", entity.getCommand());
        Command command = (Command) query.uniqueResult();
        return command;
    }

    @Override
    public List<Command> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<Command> list;

        list = (List<Command>) getSession().createQuery("from Command").list();
        return list;


    }

    @Override
    public Command insert(Command entity) throws SQLException, NamingException {
        session.save(entity);
        return (Command) entity;
    }
}
