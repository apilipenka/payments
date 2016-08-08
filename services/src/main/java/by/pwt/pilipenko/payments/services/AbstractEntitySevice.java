package by.pwt.pilipenko.payments.services;


import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.plipenko.payments.model.entities.Entity;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractEntitySevice<T extends Entity> {

    public AbstractEntitySevice() {
        super();
    }

    public List<T> getAllEntities() throws SQLException, NamingException {
        AbstractEntityDAO<T> entityDAO = getEntityDAO();
        List<T> list = entityDAO.findAll();
        entityDAO.closeConnection();
        return list;
    }

    public abstract List<T> searchEntityByName(String name) throws SQLException, NamingException;

    public T getEntity(int id) throws Exception {
        AbstractEntityDAO<T> entityDAO = getEntityDAO();
        T entity = entityDAO.findEntityById(id);
        entityDAO.closeConnection();
        return entity;

    }

    public T getEntityByPK(T entity) throws SQLException, NamingException {
        AbstractEntityDAO<T> entityDAO = getEntityDAO();
        entity = entityDAO.findEntityByPK(entity);
        entityDAO.closeConnection();
        return entity;
    }

    public boolean updateEntity(T entity) throws SQLException, NamingException {
        AbstractEntityDAO<T> entityDAO = getEntityDAO();
        boolean result = entityDAO.update(entity);
        entityDAO.closeConnection();
        return result;

    }

    public T insertEntity(T entity) throws SQLException, NamingException {
        AbstractEntityDAO<T> entityDAO = getEntityDAO();
        entity = entityDAO.insert(entity);
        entityDAO.closeConnection();
        return entity;

    }

    public boolean deleteEntity(int id) throws SQLException, NamingException {
        AbstractEntityDAO<T> entityDAO = getEntityDAO();
        boolean result = entityDAO.delete(id);
        entityDAO.closeConnection();
        return result;

    }

    public abstract AbstractEntityDAO<T> getEntityDAO() throws NamingException, SQLException;

}
