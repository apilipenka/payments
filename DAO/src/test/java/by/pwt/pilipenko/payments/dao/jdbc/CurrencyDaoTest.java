package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Currency;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyDaoTest
        extends Assert {

    private static BaseDAO currencyDAO;
    private static Currency currency1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        currencyDAO = DaoFactoryFactory.getInstance().createCurrencyDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        currencyDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = (Currency) currencyDAO.insert(currency);


        Currency currency2 = (Currency) currencyDAO.findEntityById(currency.getId());
        assertEquals(currency1, currency2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Currency> currencyList1 = new ArrayList<>();
        currencyList1.add(currency1);


        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency1);
        assertEquals(currencyList1, currencyList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        Currency currency2 = (Currency) currencyDAO.findEntityByPK(currency1);
        assertEquals(currency1, currency2);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        currency1.setName("Russian rubble test");
        currencyDAO.update(currency1);
        Currency currency2 = (Currency) currencyDAO.findEntityById(currency1.getId());

        assertEquals(currency1, currency2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        currencyDAO.delete(currency1.getId());

        Currency currency2 = (Currency) currencyDAO.findEntityById(currency1.getId());

        assertNull(currency2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = (Currency) currencyDAO.insert(currency);

        currencyDAO.delete(currency1);

        List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency);

        assertEquals(currencyList2.size(), 0);


    }


}
