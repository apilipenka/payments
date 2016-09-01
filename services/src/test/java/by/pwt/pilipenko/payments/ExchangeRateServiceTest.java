package by.pwt.pilipenko.payments;

import by.pwt.pilipenko.payments.model.entities.Currency;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.pilipenko.payments.services.ExchangeRateService;
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
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExchangeRateServiceTest
        extends Assert {

    private static CurrencyService currencyService;
    private static Currency currency1;
    private static ExchangeRateService exchangeRateService;
    private static ExchangeRate exchangeRate1;


    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        currencyService = new CurrencyService();
        exchangeRateService = new ExchangeRateService();

    }

    @AfterClass
    public static void close() throws SQLException, ClassNotFoundException, NamingException {
        currencyService.deleteEntity(currency1.getId());


        currencyService = null;
        exchangeRateService = null;
    }

    @Test
    public void test1GetEntity() throws Exception {

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = currencyService.insertEntity(currency);

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

        exchangeRate.setCurrency(currency1);

        exchangeRate1 = exchangeRateService.insertEntity(exchangeRate);

        ExchangeRate exchangeRate2 = exchangeRateService.getEntity(exchangeRate1.getId());
        assertEquals(exchangeRate1, exchangeRate2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<ExchangeRate>();
        exchangeRateList1.add(exchangeRate1);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityByName(format.format(exchangeRate1.getRateDate()));
        assertEquals(exchangeRateList1, exchangeRateList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        ExchangeRate exchangeRate2 = exchangeRateService.getEntityByPK(exchangeRate1);
        assertEquals(exchangeRate1, exchangeRate2);


    }

    @Test
    public void test61FindEntityByParent() throws Exception {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityParent(exchangeRate1.getCurrency().getId(), exchangeRate1.getTargetCurrency().getId());
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test62FindEntityByParent() throws Exception {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityParent(exchangeRate1.getCurrency().getId(), null);
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test63FindEntityByParent() throws Exception {

        List<ExchangeRate> exchangeRateList1 = new ArrayList<>();
        exchangeRateList1.add(exchangeRate1);

        List<ExchangeRate> exchangeRateList2 = exchangeRateService.searchEntityParent(null, exchangeRate1.getTargetCurrency().getId());
        assertEquals(exchangeRateList1, exchangeRateList2);


    }

    @Test
    public void test7Update() throws Exception {


        exchangeRate1.setRate(1);
        exchangeRateService.updateEntity(exchangeRate1);
        ExchangeRate exchangeRate2 = exchangeRateService.getEntity(exchangeRate1.getId());

        assertEquals(exchangeRate1, exchangeRate2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        exchangeRateService.deleteEntity(exchangeRate1.getId());


        ExchangeRate exchangeRate2 = exchangeRateService.getEntity(exchangeRate1.getId());

        assertNull(exchangeRate2);


    }


}
