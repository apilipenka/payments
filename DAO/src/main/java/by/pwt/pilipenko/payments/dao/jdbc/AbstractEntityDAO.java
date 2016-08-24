package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.resources.QueriesManager;
import by.pwt.plipenko.payments.model.entities.Entity;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractEntityDAO<T extends Entity> implements BaseDAO<T> {

    private Connection connection;
    private String selectStatement = null;
    private String selectByIdStatement = null;
    private String selectByPKStatement = null;
    private String selectByEntityStatement = null;
    private String deleteStatement = null;
    private String deleteByPKStatement = null;
    private String updateStatement = null;
    private String insertStatement = null;

    public AbstractEntityDAO(Connection connection, String entityName, String tableName) {
        super();
        this.connection = connection;

        this.selectStatement = QueriesManager.getProperty(entityName + "." + "selectStatement").replace("#TABLE_NAME#",
                tableName);
        this.selectByIdStatement = QueriesManager.getProperty(entityName + "." + "selectByIdStatement")
                .replace("#TABLE_NAME#", tableName);
        this.selectByIdStatement = QueriesManager.getProperty(entityName + "." + "selectByIdStatement")
                .replace("#TABLE_NAME#", tableName);
        this.selectByPKStatement = QueriesManager.getProperty(entityName + "." + "selectByPkStatement")
                .replace("#TABLE_NAME#", tableName);
        this.selectByEntityStatement = QueriesManager.getProperty(entityName + "." + "selectByEntityStatement")
                .replace("#TABLE_NAME#", tableName);
        this.deleteStatement = QueriesManager.getProperty(entityName + "." + "deleteStatement").replace("#TABLE_NAME#",
                tableName);
        this.deleteByPKStatement = QueriesManager.getProperty(entityName + "." + "deleteByPKStatement")
                .replace("#TABLE_NAME#", tableName);
        this.updateStatement = QueriesManager.getProperty(entityName + "." + "updateStatement").replace("#TABLE_NAME#",
                tableName);
        this.insertStatement = QueriesManager.getProperty(entityName + "." + "insertStatement").replace("#TABLE_NAME#",
                tableName);
    }

    public T insert(T entity) throws SQLException {

        PreparedStatement statement = null;
        ResultSet rs;

        try {
            statement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
            statement = prepareInsertStatement(entity, statement);
            statement.executeUpdate();

            rs = statement.getGeneratedKeys();

            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                entity.setId(last_inserted_id);
            }

        } finally {
            closeStatement(statement);
        }

        return entity;
    }

    public boolean update(T entity) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(updateStatement);
            statement = prepareUpdateStatement(entity, statement);
            statement.executeUpdate();

        } finally {
            closeStatement(statement);
        }
        return true;
    }

    public boolean delete(T entity) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(deleteByPKStatement);
            statement = prepareSelectByPKStatement(entity, statement);
            statement.executeUpdate();

            return true;

        } finally {
            closeStatement(statement);
        }
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(deleteStatement);
            statement.setLong(1, id);
            statement.executeUpdate();

            return true;

        } finally {
            closeStatement(statement);
        }
    }

    public T findEntityById(int id) throws SQLException, NamingException, ClassNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T entity = null;
        try {
            statement = connection.prepareStatement(selectByIdStatement);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                entity = getEntity(resultSet);
            }

        }
        finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return entity;
    }

    public T findEntityByPK(T entity) throws SQLException, NamingException, ClassNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T entity1 = null;
        try {
            statement = connection.prepareStatement(selectByPKStatement);
            statement = prepareSelectByPKStatement(entity, statement);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                entity1 = getEntity(resultSet);
            }

        }
        finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return entity1;
    }

    public List<T> findEntityByEntity(T entity) throws SQLException, NamingException, ClassNotFoundException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> entities = null;
        try {
            statement = connection.prepareStatement(selectByEntityStatement);
            statement = prepareSelectByEntityStatement(entity, statement);
            resultSet = statement.executeQuery();
            entities = getEntities(resultSet);

        }
        finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return entities;
    }

    public List<T> findAll() throws SQLException, NamingException, ClassNotFoundException {
        Statement statement = null;
        ResultSet resultSet = null;
        List<T> entities = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectStatement);
            entities = getEntities(resultSet);

        }
        finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return entities;

    }

    void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public void closePreparedStatement(PreparedStatement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    protected abstract T getEntity(ResultSet resultSet) throws SQLException, NamingException, ClassNotFoundException;

    List<T> getEntities(ResultSet resultSet) throws SQLException, NamingException, ClassNotFoundException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            T entity = getEntity(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected abstract PreparedStatement prepareSelectByPKStatement(T entity, PreparedStatement statement)
            throws SQLException;

    protected abstract PreparedStatement prepareSelectByEntityStatement(T entity, PreparedStatement statement)
            throws SQLException;

    protected abstract PreparedStatement prepareInsertStatement(T entity, PreparedStatement statement)
            throws SQLException;

    protected abstract PreparedStatement prepareUpdateStatement(T entity, PreparedStatement statement)
            throws SQLException;

}
