package by.pwt.pilipenko.payments.dao;

import by.pwt.plipenko.payments.model.entities.Entity;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface BaseDAO<E extends Entity> {

    boolean update(E entity) throws SQLException, NamingException;

    boolean delete(int id) throws SQLException, NamingException;

    boolean delete(E entity) throws SQLException, NamingException;

    E findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException;

    List<E> findEntityByEntity(E entity) throws SQLException, NamingException, ClassNotFoundException;

    E findEntityByPK(E entity) throws SQLException, NamingException, ClassNotFoundException;

    List<E> findAll() throws SQLException, NamingException, ClassNotFoundException;

    E insert(E entity) throws SQLException, NamingException;

    void closeConnection() throws SQLException;

    void setConnection(Connection connection) throws SQLException;
    Connection getConnection() throws SQLException;

}