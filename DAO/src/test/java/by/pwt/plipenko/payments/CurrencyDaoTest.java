package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.dao.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.JDBCDAOFactory;
import by.pwt.plipenko.payments.model.entities.Currency;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyDaoTest
        extends Assert {

    private static PoolingDataSource<PoolableConnection> ds;
    private static JDBCDAOFactory df;
    private static CurrencyDAO currencyDAO;
    private static Currency currency;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/payments?autoReconnect=true&useSSL=false", "root", "awp1977");
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
                null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        ds = new PoolingDataSource<>(connectionPool);

        df = new JDBCDAOFactory((DataSource) ds);

        currencyDAO = df.createCurrencyDAO();


    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        currencyDAO = null;

        df = null;
        ds = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException {

        currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        Currency currency1 = currencyDAO.insert(currency);


        Currency currency2 = currencyDAO.findEntityById(currency.getId());
        assertEquals(currency, currency2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException {

        List<Currency> currencyList1 = new ArrayList<Currency>();
        currencyList1.add(currency);


        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency);
        assertEquals(currencyList1, currencyList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException {


        Currency currency2 = currencyDAO.findEntityByPK(currency);
        assertEquals(currency, currency2);

        assertEquals(currency, currency2);


    }


    @Test
    public void test7Update() throws SQLException, NamingException {


        currency.setName("Russian rubble test");
        currencyDAO.update(currency);
        Currency currency2 = currencyDAO.findEntityById(currency.getId());

        assertEquals(currency, currency2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException {


        currencyDAO.delete(currency.getId());

        Currency currency2 = currencyDAO.findEntityById(currency.getId());

        assertNull(currency2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException {

        currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        Currency currency1 = currencyDAO.insert(currency);

        currencyDAO.delete(currency);

        List<Currency> currencyList1 = new ArrayList<Currency>();
        currencyList1.add(currency);


        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency);

        assertEquals(currencyList2.size(),0);


    }


}
