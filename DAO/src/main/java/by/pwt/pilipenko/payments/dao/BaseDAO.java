package by.pwt.pilipenko.payments.dao;

import by.pwt.plipenko.payments.model.entities.Entity;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;


public interface BaseDAO<E extends Entity> {

    public boolean update(E entity) throws SQLException, NamingException;

    public boolean delete(int id) throws SQLException, NamingException;

    public boolean delete(E entity) throws SQLException, NamingException;

    public E findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException;

    public List<E> findEntityByEntity(E entity) throws SQLException, NamingException, ClassNotFoundException;

    public E findEntityByPK(E entity) throws SQLException, NamingException, ClassNotFoundException;

    public List<E> findAll() throws SQLException, NamingException, ClassNotFoundException;

    public E insert(E entity) throws SQLException, NamingException;

}