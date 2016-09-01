package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAOFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by apilipenka on 8/19/2016.
 */
abstract class AbstractDAOFactory implements BaseDAOFactory {
    private DataSource dataSource;
    private Connection connection;

    private static ThreadLocal<Boolean> flags = new ThreadLocal<>();

    DataSource getDataSource() {
        return dataSource;
    }

    void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    Connection getConnection() throws SQLException {
        Connection localInstance = connection;

        if (localInstance == null) {

            synchronized (AbstractDAOFactory.class) {
                localInstance = connection;
                if (localInstance == null) {
                    connection = dataSource.getConnection();
                    localInstance = connection;
                    flags.set(new Boolean(false));
                }

            }

        }

        return localInstance;

    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void commit() throws SQLException {
        connection.commit();
        flags.set(new Boolean(false));
    }

    public void rollback() throws SQLException {
        connection.rollback();
        flags.set(new Boolean(false));
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
        flags.set(new Boolean(true));
    }

    public void endTransaction() throws SQLException {
        connection.setAutoCommit(true);
        flags.set(new Boolean(false));
    }


    public boolean isParentTransactionStarted() {
        Boolean flag = flags.get();
        if (flag!=null)
            return flags.get().booleanValue();
        flags.set(new Boolean(false));
        return false;
    }



}


