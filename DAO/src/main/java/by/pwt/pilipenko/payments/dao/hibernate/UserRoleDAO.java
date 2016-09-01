package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.UserRole;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserRoleDAO extends AbstractEntityDAO<UserRole> {

    public UserRoleDAO(Session session) {
        super(session);
    }


    @Override
    public boolean delete(UserRole entity) throws SQLException, NamingException {
        Query query = getSession().createQuery("delete from UserRole where name = :name");
        query.setParameter("name", entity.getName());
        int result = query.executeUpdate();

        return true;
    }

    @Override
    public List<UserRole> findEntityByEntity(UserRole entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from UserRole where name=COALESCE(:name, name)");
        query.setParameter("name", entity.getName());
        return (List<UserRole>) query.list();
    }

    @Override
    public UserRole findEntityByPK(UserRole entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from UserRole where name=:name");
        query.setParameter("name", entity.getName());
        return (UserRole) query.uniqueResult();
    }

    @Override
    public List<UserRole> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<UserRole> list;

        list = (List<UserRole>) getSession().createQuery("from UserRole").list();
        return list;
    }

}
