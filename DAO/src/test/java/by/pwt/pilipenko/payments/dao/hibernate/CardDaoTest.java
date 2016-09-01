package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.*;
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
public class CardDaoTest
        extends Assert {


    private static CardDAO cardDAO;
    private static AccountDAO accountDAO;
    private static CurrencyDAO currencyDAO;
    private static AgreementDAO agreementDAO;
    private static BankDAO bankDAO;
    private static UserDAO userDAO;
    private static UserRoleDAO userRoleDAO;

    private static Bank bank1;
    private static User user1;
    private static UserRole userRole1;
    private static Agreement agreement1;
    private static Account account1;
    private static Currency currency1;
    private static Card card1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("hibernate");
        bankDAO = (BankDAO) DaoFactoryFactory.getInstance().createBankDAO();
        userRoleDAO = (UserRoleDAO) DaoFactoryFactory.getInstance().createUserRoleDAO();
        userDAO = (UserDAO) DaoFactoryFactory.getInstance().createUserDAO();
        agreementDAO = (AgreementDAO) DaoFactoryFactory.getInstance().createAgreementDAO();
        currencyDAO = (CurrencyDAO) DaoFactoryFactory.getInstance().createCurrencyDAO();
        accountDAO = (AccountDAO) DaoFactoryFactory.getInstance().createAccountDAO();
        cardDAO = (CardDAO) DaoFactoryFactory.getInstance().createCardDAO();
    }

    @AfterClass
    public static void close() throws SQLException, NamingException, ClassNotFoundException {
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            accountDAO.delete(account1);
            agreementDAO.delete(agreement1);
            bankDAO.delete(bank1);
            userDAO.delete(user1);
            userRoleDAO.delete(userRole1);
            currencyDAO.delete(currency1);
            DaoFactoryFactory.getInstance().commit();
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        Bank bank = new Bank();
        bank.setName("Test bank");
        bank.setUNN("19777791");
        bank.setName("Tests bank");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            bank1 = bankDAO.insert(bank);

            User user = new User();
            user.setPersonalNumber("1234567890");
            user.setFirstName("Alexander");
            user.setLastName("Pilipenko");
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            user.setBirthDate(format.parse("10.01.1977"));
            user.setLogin("apilipenka");
            user.setPassword("123456");

            UserRole userRole = new UserRole();
            userRole.setName("Test");
            userRole.setName("Test user");

            userRole1 = userRoleDAO.insert(userRole);

            user.setUserRole(userRole1);

            user1 = userDAO.insert(user);


            Agreement agreement = new Agreement();

            agreement.setNumber("19777791");

            try {
                agreement.setValidFromDate(format.parse("10.01.1907"));
            } catch (ParseException e) {
                //it is not possible

            }
            try {
                agreement.setValidToDate(format.parse("10.01.1917"));
            } catch (ParseException e) {
                //it is not possible

            }
            agreement.setBank(bank1);
            agreement.setClient(user1);

            agreement1 = agreementDAO.insert(agreement);

            Currency currency = new Currency();
            currency.setCode("643");
            currency.setMnemoCode("RUR");
            currency.setName("Russian rubble");
            currency1 = currencyDAO.insert(currency);

            Account account = new Account();
            account.setNumber("197777719");
            account.setAmount(19999);
            account.setAgreement(agreement1);
            account.setCurrency(currency1);

            account1 = accountDAO.insert(account);

            Card card = new Card();
            card.setNumber("122333444555");
            card.setName("Aliaksandr Pilipenka");
            try {
                card.setValidToDate(format.parse("10.01.2017"));
            } catch (ParseException e) {
                //it is not possible

            }
            card.setAccount(account1);
            card1 = cardDAO.insert(card);
            DaoFactoryFactory.getInstance().commit();
            Card card2 = cardDAO.findEntityById(card1.getId());
            assertEquals(card1, card2);

        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(card1);

        List<Card> cardList2 = cardDAO.findEntityByEntity(card1);
        assertEquals(cardList1, cardList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {
        Card card2 = cardDAO.findEntityByPK(card1);
        assertEquals(card1, card2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        card1.setNumber("19777911977989");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            cardDAO.update(card1);
            DaoFactoryFactory.getInstance().commit();
            Card card2 = cardDAO.findEntityById(card1.getId());


            assertEquals(card1, card2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            cardDAO.delete(card1.getId());
            DaoFactoryFactory.getInstance().commit();

            Card card2 = cardDAO.findEntityById(card1.getId());
            assertNull(card2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {


        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Card card = new Card();
        card.setNumber("122333444555");
        card.setName("Aliaksandr Pilipenka");
        try {
            card.setValidToDate(format.parse("10.01.2017"));
        } catch (ParseException e) {
            //it is not possible

        }
        card.setAccount(account1);
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            card1 = cardDAO.insert(card);

            cardDAO.delete(card1);
            DaoFactoryFactory.getInstance().commit();

            List<Card> accountList2 = cardDAO.findEntityByEntity(card1);

            assertEquals(accountList2.size(), 0);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


}
