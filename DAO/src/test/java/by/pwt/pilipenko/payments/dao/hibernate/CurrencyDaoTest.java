package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Currency;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyDaoTest
        extends Assert {

    private static CurrencyDAO currencyDAO;
    private static ExchangeRateDAO exchangeRateDAO;
    private static Currency currency1;
    private static ExchangeRate exchangeRate1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("hibernate");
        currencyDAO = (CurrencyDAO) DaoFactoryFactory.getInstance().createCurrencyDAO();
        exchangeRateDAO = (ExchangeRateDAO) DaoFactoryFactory.getInstance().createExchangeRateDAO();
    }

    @AfterClass
    public static void close() throws SQLException {

        currencyDAO = null;
        exchangeRateDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException, ParseException {

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");


        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            currency1 = currencyDAO.insert(currency);

            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setRate(100);
            exchangeRate.setCurrency(currency1);
            exchangeRate.setTargetCurrency(currency1);
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            exchangeRate.setRateDate(format.parse("10.01.1977"));

            currency1.addExchangeRate(exchangeRate);
            exchangeRate1 = exchangeRateDAO.insert(exchangeRate);

            DaoFactoryFactory.getInstance().commit();


            Currency currency2 = currencyDAO.findEntityById(currency1.getId());
            assertEquals(currency1, currency2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }


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

        Currency currency2 = currencyDAO.findEntityByPK(currency1);
        assertEquals(currency1, currency2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException, ParseException {

        currency1.setName("Russian rubble test");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            currencyDAO.update(currency1);
            DaoFactoryFactory.getInstance().commit();

            Currency currency2 = currencyDAO.findEntityById(currency1.getId());

            assertEquals(currency1, currency2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            currencyDAO.delete(currency1.getId());
            DaoFactoryFactory.getInstance().commit();

            Currency currency2 = currencyDAO.findEntityById(currency1.getId());

            assertNull(currency2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException, ParseException {
        try {
            Currency currency = new Currency();
            currency.setCode("643");
            currency.setMnemoCode("RUR");
            currency.setName("Russian rubble");

            DaoFactoryFactory.getInstance().beginTransaction();
            currency1 = currencyDAO.insert(currency);

            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setRate(100);
            exchangeRate.setCurrency(currency1);
            exchangeRate.setTargetCurrency(currency1);
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            exchangeRate.setRateDate(format.parse("10.01.1977"));

            currency1.addExchangeRate(exchangeRate);
            exchangeRate1 = exchangeRateDAO.insert(exchangeRate);

            ((DAOFactory) DaoFactoryFactory.getInstance()).getSession().flush();

            currencyDAO.delete(currency1);
            DaoFactoryFactory.getInstance().commit();


            List<Currency> currencyList2 = currencyDAO.findEntityByEntity(currency);

            assertEquals(currencyList2.size(), 0);
        } catch (SQLException | NamingException | ClassNotFoundException | ParseException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


}
