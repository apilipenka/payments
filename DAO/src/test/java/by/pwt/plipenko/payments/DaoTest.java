package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.dao.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.plipenko.payments.model.entities.Currency;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class DaoTest
        extends Assert {

    PoolingDataSource<PoolableConnection> ds;
    private DAOFactory df;

    @Before
    public void intit() throws NamingException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/payments?autoReconnect=true&useSSL=false", "root", "awp1977");
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
                null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        ds = new PoolingDataSource<>(connectionPool);

        df = new DAOFactory((DataSource)ds);
    }

    @After
    public void tearDownToHexStringData() {
        df = null;
        ds = null;
    }

    @Test
    public void testFindEntityById() throws SQLException, NamingException {
        CurrencyDAO currencyDAO = df.createCurrencyDAO();
        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        Currency currency1 = currencyDAO.insert(currency);
        Currency currency2 = currencyDAO.findEntityById(currency1.getId());
        assertEquals(currency1, currency2);
        currencyDAO.delete(currency1.getId());




    }


}
