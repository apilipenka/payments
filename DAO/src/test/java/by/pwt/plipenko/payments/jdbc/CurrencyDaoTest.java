package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
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

    private static CurrencyDAO currencyDAO;
    private static Currency currency;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        currencyDAO = DaoFactoryFactory.getInstance().createCurrencyDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        currencyDAO.closeConnection();
        currencyDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        Currency currency1 = currencyDAO.insert(currency);


        Currency currency2 = currencyDAO.findEntityById(currency.getId());
        assertEquals(currency, currency2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Currency> currencyList1 = new ArrayList<Currency>();
        currencyList1.add(currency);


        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency);
        assertEquals(currencyList1, currencyList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        Currency currency2 = currencyDAO.findEntityByPK(currency);
        assertEquals(currency, currency2);

        assertEquals(currency, currency2);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        currency.setName("Russian rubble test");
        currencyDAO.update(currency);
        Currency currency2 = currencyDAO.findEntityById(currency.getId());

        assertEquals(currency, currency2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        currencyDAO.delete(currency.getId());

        Currency currency2 = currencyDAO.findEntityById(currency.getId());

        assertNull(currency2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        Currency currency1 = currencyDAO.insert(currency);

        currencyDAO.delete(currency);

        List<Currency> currencyList1 = new ArrayList<Currency>();
        currencyList1.add(currency);


        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency);

        assertEquals(currencyList2.size(), 0);


    }


}
