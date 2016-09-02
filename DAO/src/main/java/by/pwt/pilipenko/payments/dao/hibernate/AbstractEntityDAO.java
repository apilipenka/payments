package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.model.entities.AbstractEntity;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;


/**
 * Created by Darrko on 30.08.2016.
 */
public abstract class AbstractEntityDAO<T extends AbstractEntity> implements BaseDAO<T> {
    private Session session;

    AbstractEntityDAO(Session session) {
        this.session = session;
    }

    Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException {

        return (T) getSession().get(getPersistentClass(), id);

    }

    public T loadEntity(int id) throws SQLException, NamingException, ClassNotFoundException {

        return (T) getSession().load(getPersistentClass(), id);

    }

    public boolean update(T entity) throws SQLException, NamingException {
        getSession().saveOrUpdate(entity);
        return true;
    }

    public boolean delete(int id) throws SQLException, NamingException, ClassNotFoundException {

        T entity = findEntityById(id);
        getSession().delete(entity);

        return true;
    }

    public T insert(T entity) throws SQLException, NamingException {
        getSession().persist(entity); //.save(entity);
        return entity;
    }


}
