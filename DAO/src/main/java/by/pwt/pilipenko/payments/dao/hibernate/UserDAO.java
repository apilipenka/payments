package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractEntityDAO<User> {


    public UserDAO(Session session) {
        super(session);
    }

    @Override
    public boolean delete(User entity) throws SQLException, NamingException {

        Query query = getSession().createQuery("delete from User where personalNumber = :personalNumber");
        query.setParameter("personalNumber", entity.getPersonalNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<User> findEntityByEntity(User entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from User where personalNumber=COALESCE(:personalNumber, personalNumber)");
        query.setParameter("personalNumber", entity.getPersonalNumber());
        List<User> list = (List<User>) query.list();
        return list;
    }

    @Override
    public User findEntityByPK(User entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from User where personalNumber=:personalNumber");
        query.setParameter("personalNumber", entity.getPersonalNumber());
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<User> list;

        list = (List<User>) getSession().createQuery("from User").list();
        return list;


    }

}
