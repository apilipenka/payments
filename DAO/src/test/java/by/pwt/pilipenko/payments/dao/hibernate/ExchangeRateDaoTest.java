package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
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


    private static DAOFactory factory;
    private static BaseDAO exchangeRateDAO;
    private static BaseDAO currencyDAO;
    private static Currency currency1;
    private static ExchangeRate exchangeRate1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        DaoFactoryFactory.setDaoType("hibernate");
        //factory = (DAOFactory) DaoFactoryFactory.getInstance();


        currencyDAO = DaoFactoryFactory.getInstance().createCurrencyDAO();
        exchangeRateDAO = DaoFactoryFactory.getInstance().createExchangeRateDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, NamingException, ClassNotFoundException {

        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            currencyDAO.delete(currency1);
            DaoFactoryFactory.getInstance().commit();
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
        finally {
            currencyDAO = null;
            exchangeRateDAO = null;
        }



    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        Currency currency = new Currency();
        currency.setCode("977");
        currency.setMnemoCode("AWP");
        currency.setName("Tests currency");


        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setRate(197);

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try {
            exchangeRate.setRateDate(format.parse("10.01.1907"));
        } catch (ParseException e) {
            //it is not possible

        }

        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            currency1 = (Currency) currencyDAO.insert(currency);
            ((DAOFactory) DaoFactoryFactory.getInstance()).getSession().flush();
            exchangeRate.setCurrency(currency1);
            exchangeRate.setTargetCurrency(currency1);
            exchangeRate1 = (ExchangeRate) exchangeRateDAO.insert(exchangeRate);

            DaoFactoryFactory.getInstance().commit();

            ExchangeRate exchangeRate2 = (ExchangeRate) exchangeRateDAO.findEntityById(exchangeRate1.getId());
            assertEquals(exchangeRate1, exchangeRate2);

        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

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
        ExchangeRate exchangeRate2 = (ExchangeRate) exchangeRateDAO.findEntityByPK(exchangeRate1);
        assertEquals(exchangeRate1, exchangeRate2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        exchangeRate1.setRate(300);
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            exchangeRateDAO.update(exchangeRate1);
            DaoFactoryFactory.getInstance().commit();
            ExchangeRate exchangeRate2 = (ExchangeRate) exchangeRateDAO.findEntityById(exchangeRate1.getId());


            assertEquals(exchangeRate1, exchangeRate2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {

        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            exchangeRateDAO.delete(exchangeRate1.getId());
            DaoFactoryFactory.getInstance().commit();
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

        ExchangeRate exchangeRate2 = (ExchangeRate) exchangeRateDAO.findEntityById(exchangeRate1.getId());
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
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            exchangeRate1 = (ExchangeRate) exchangeRateDAO.insert(exchangeRate);

            exchangeRateDAO.delete(exchangeRate1);
            DaoFactoryFactory.getInstance().commit();

            List<ExchangeRate> exchangeRateList2 = exchangeRateDAO.findEntityByEntity(exchangeRate);

            assertEquals(exchangeRateList2.size(), 0);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


}
