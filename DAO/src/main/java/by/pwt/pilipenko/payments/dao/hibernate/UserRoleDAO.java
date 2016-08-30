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
    public boolean update(UserRole entity) throws SQLException, NamingException {
        getSession().saveOrUpdate(entity);
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException, NamingException, ClassNotFoundException {
        UserRole userRole = findEntityById(id);
        getSession().delete(userRole);

        return true;
    }

    @Override
    public boolean delete(UserRole entity) throws SQLException, NamingException {
        Query query = getSession().createQuery("delete from UserRole where name = :name");
        query.setParameter("name", entity.getName());
        int result = query.executeUpdate();

        return true;
    }

    @Override
    public UserRole findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException {
        UserRole userRole = (UserRole) getSession().get(UserRole.class, id);
        return userRole;
    }

    @Override
    public List<UserRole> findEntityByEntity(UserRole entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from UserRole where name=COALESCE(:name, name)");
        query.setParameter("name", entity.getName());
        List<UserRole> list = (List<UserRole>) query.list();
        return list;
    }

    @Override
    public UserRole findEntityByPK(UserRole entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from UserRole where name=:name");
        query.setParameter("name", entity.getName());
        UserRole userRole = (UserRole) query.uniqueResult();
        return userRole;
    }

    @Override
    public List<UserRole> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<UserRole> list;

        list = (List<UserRole>) getSession().createQuery("from UserRole").list();
        return list;
    }

    @Override
    public UserRole insert(UserRole entity) throws SQLException, NamingException {
        getSession().save(entity);
        return entity;
    }
}
