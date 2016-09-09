package by.pwt.pilipenko.payments.services;


import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.AbstractEntity;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractEntityService<T extends AbstractEntity> {

    public AbstractEntityService() {
        super();
    }

    public List<T> getAllEntities() throws SQLException, NamingException, ClassNotFoundException {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            BaseDAO<T> entityDAO = getEntityDAO();
            return entityDAO.findAll();
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public abstract List<T> searchEntityByName(String name) throws Exception;

    public T getEntity(int id) throws Exception {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            BaseDAO<T> entityDAO = getEntityDAO();
            return entityDAO.findEntityById(id);
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public T loadEntity(int id) throws Exception {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            BaseDAO<T> entityDAO = getEntityDAO();
            return entityDAO.loadEntity(id);
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public T getEntityByPK(T entity) throws Exception {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            BaseDAO<T> entityDAO = getEntityDAO();
            entity = entityDAO.findEntityByPK(entity);
            return entity;
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public boolean updateEntity(T entity) throws ClassNotFoundException, NamingException, SQLException {

        BaseDAO<T> entityDAO = getEntityDAO();
        boolean result;
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            result = entityDAO.update(entity);
            DaoFactoryFactory.getInstance();
            if (!flag)
                DaoFactoryFactory.getInstance().commit();

        } catch (SQLException | NamingException e) {
            if (!flag)
                DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

        return result;

    }

    public T insertEntity(T entity) throws SQLException, NamingException, ClassNotFoundException {
        BaseDAO<T> entityDAO = getEntityDAO();
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            entity = entityDAO.insert(entity);
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        } catch (SQLException | NamingException e) {
            if (!flag)
                DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
        return entity;

    }

    public boolean deleteEntity(int id) throws SQLException, NamingException, ClassNotFoundException {
        BaseDAO<T> entityDAO = getEntityDAO();
        boolean result;
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            result = entityDAO.delete(id);
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        } catch (SQLException | NamingException e) {
            if (!flag)
                DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
        return result;

    }

    public abstract BaseDAO<T> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException;


}
