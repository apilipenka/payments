package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.dao.AbstractDAOFactory;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
import by.pwt.pilipenko.payments.services.CurrencyService;
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
public class CurrencyServiceTest
        extends Assert {

    //private static PoolingDataSource<PoolableConnection> ds;
    //private static AbstractDAOFactory df;
    //private static DaoFactoryFactory dff;
    //private static CurrencyDAO currencyDAO;
    private static CurrencyService currencyService;
    private static Currency currency;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        currencyService = new CurrencyService();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {
        currencyService = null;

    }

    @Test
    public void test1GetEntity() throws Exception {

        currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        Currency currency1 = currencyService.insertEntity(currency);


        Currency currency2 = currencyService.getEntity(currency.getId());
        assertEquals(currency, currency2);


    }

   @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Currency> currencyList1 = new ArrayList<Currency>();
        currencyList1.add(currency);


        List<Currency> currencyList2 = currencyService.searchEntityByName("RUR");
        assertEquals(currencyList1, currencyList2);


    }

     @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {

        Currency currency2 = currencyService.getEntityByPK(currency);
        assertEquals(currency, currency2);


    }


    @Test
    public void test7Update() throws Exception {


        currency.setName("Russian rubble test");
        currencyService.updateEntity(currency);
        Currency currency2 = currencyService.getEntity(currency.getId());

        assertEquals(currency, currency2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        currencyService.deleteEntity(currency.getId());



        Currency currency2 = currencyService.getEntity(currency.getId());

        assertNull(currency2);


    }




}
