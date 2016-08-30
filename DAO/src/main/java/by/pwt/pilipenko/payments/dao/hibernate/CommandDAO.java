package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.Command;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CommandDAO extends AbstractEntityDAO<Command> {


    public CommandDAO(Session session) {
        super(session);
    }

    @Override
    public boolean update(Command entity) throws SQLException, NamingException {
        getSession().saveOrUpdate(entity);
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException, NamingException, ClassNotFoundException {

        Command command = findEntityById(id);
        getSession().delete(command);

        return false;
    }

    @Override
    public boolean delete(Command entity) throws SQLException, NamingException {

        Query query = getSession().createQuery("delete from Command where command = :command");
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
        getSession().save(entity);
        return entity;
    }
}
