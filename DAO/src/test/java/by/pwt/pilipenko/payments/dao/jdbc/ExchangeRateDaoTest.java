package by.pwt.pilipenko.payments.dao.jdbc;

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
public class ExchangeRateDaoTest
        extends Assert {

    private static ExchangeRateDAO exchangeRateDAO;
    private static CurrencyDAO currencyDAO;
    private static Currency currency1;
    private static ExchangeRate exchangeRate1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        currencyDAO = DaoFactoryFactory.getInstance().createCurrencyDAO();
        exchangeRateDAO = DaoFactoryFactory.getInstance().createExchangeRateDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, NamingException {

            currencyDAO.delete(currency1);




    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        Currency currency = new Currency();
        currency.setCode("977");
        currency.setMnemoCode("AWP");
        currency.setName("Tests currency");
        currency1 = currencyDAO.insert(currency);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(currency1);
        exchangeRate.setTargetCurrency(currency1);
        exchangeRate.setRate(197);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            exchangeRate.setRateDate(format.parse("10.01.1907"));
        } catch (ParseException e) {
            //it is not possible

        }


        exchangeRate1 = exchangeRateDAO.insert(exchangeRate);
        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityById(exchangeRate1.getId());
        assertEquals(exchangeRate1, exchangeRate2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateDAO.findEntityByEntity(exchangeRate1);
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {
        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityByPK(exchangeRate1);
        assertEquals(exchangeRate1, exchangeRate2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        exchangeRate1.setRate(300);

        exchangeRateDAO.update(exchangeRate1);
        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityById(exchangeRate1.getId());


        assertEquals(exchangeRate1, exchangeRate2);

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        exchangeRateDAO.delete(exchangeRate1.getId());


        ExchangeRate exchangeRate2 = exchangeRateDAO.findEntityById(exchangeRate1.getId());
        assertNull(exchangeRate2);

    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(currency1);
        exchangeRate.setTargetCurrency(currency1);
        exchangeRate.setRate(197);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            exchangeRate.setRateDate(format.parse("10.01.1907"));
        } catch (ParseException e) {
            //it is not possible

        }

        exchangeRate1 = exchangeRateDAO.insert(exchangeRate);

        exchangeRateDAO.delete(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateDAO.findEntityByEntity(exchangeRate);

        assertEquals(exchangeRateList2.size(), 0);


    }


}
