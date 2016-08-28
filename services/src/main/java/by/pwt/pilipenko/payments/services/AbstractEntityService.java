package by.pwt.pilipenko.payments.services;


import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.plipenko.payments.model.entities.Entity;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractEntityService<T extends Entity> {

    public AbstractEntityService() {
        super();
    }

    public List<T> getAllEntities() throws SQLException, NamingException, ClassNotFoundException {
        BaseDAO<T> entityDAO = getEntityDAO();
        List<T> list = entityDAO.findAll();
        return list;
    }

    public abstract List<T> searchEntityByName(String name) throws Exception;

    public T getEntity(int id) throws Exception {
        BaseDAO<T> entityDAO = getEntityDAO();
        T entity = entityDAO.findEntityById(id);
        return entity;

    }

    public T getEntityByPK(T entity) throws Exception {
        BaseDAO<T> entityDAO = getEntityDAO();
        entity = entityDAO.findEntityByPK(entity);
        return entity;
    }

    public boolean updateEntity(T entity) throws SQLException, NamingException, ClassNotFoundException {
        BaseDAO<T> entityDAO = getEntityDAO();
        boolean result = entityDAO.update(entity);
        return result;

    }

    public T insertEntity(T entity) throws SQLException, NamingException, ClassNotFoundException {
        BaseDAO<T> entityDAO = getEntityDAO();
        entity = entityDAO.insert(entity);
        return entity;

    }

    public boolean deleteEntity(int id) throws SQLException, NamingException, ClassNotFoundException {
        BaseDAO<T> entityDAO = getEntityDAO();
        boolean result = entityDAO.delete(id);
        return result;

    }

    public abstract BaseDAO<T> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException;


}
