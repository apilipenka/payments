package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAO;
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
public class AccountDaoTest
        extends Assert {

    private static BaseDAO accountDAO;
    private static BaseDAO agreementDAO;
    private static BaseDAO bankDAO;
    private static BaseDAO userDAO;
    private static BaseDAO userRoleDAO;
    private static BaseDAO currencyDAO;
    private static Bank bank1;
    private static User user1;
    private static UserRole userRole1;
    private static Agreement agreement1;
    private static Account account1;
    private static Currency currency1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        DaoFactoryFactory.setDaoType("jdbc");

        bankDAO = DaoFactoryFactory.getInstance().createBankDAO();
        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();
        userDAO = DaoFactoryFactory.getInstance().createUserDAO();
        agreementDAO = DaoFactoryFactory.getInstance().createAgreementDAO();
        currencyDAO = DaoFactoryFactory.getInstance().createCurrencyDAO();
        accountDAO = DaoFactoryFactory.getInstance().createAccountDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, NamingException {

        agreementDAO.delete(agreement1);
        bankDAO.delete(bank1);
        userDAO.delete(user1);
        userRoleDAO.delete(userRole1);
        currencyDAO.delete(currency1);

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        Bank bank = new Bank();
        bank.setName("Test bank");
        bank.setUNN("19777791");
        bank.setName("Tests bank");
        bank1 = (Bank) bankDAO.insert(bank);

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

        userRole1 = (UserRole) userRoleDAO.insert(userRole);

        user.setUserRole(userRole1);

        user1 = (User) userDAO.insert(user);


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

        agreement1 = (Agreement) agreementDAO.insert(agreement);

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = (Currency) currencyDAO.insert(currency);

        Account account = new Account();
        account.setNumber("197777719");
        account.setAmount(19999);
        account.setAgreement(agreement1);
        account.setCurrency(currency1);

        account1 = (Account) accountDAO.insert(account);

        Account account2 = (Account) accountDAO.findEntityById(account1.getId());
        assertEquals(account1, account2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Account> accountList1 = new ArrayList<>();
        accountList1.add(account1);

        List<Account> accountList2 = accountDAO.findEntityByEntity(account1);
        assertEquals(accountList1, accountList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {
        Account account2 = (Account) accountDAO.findEntityByPK(account1);
        assertEquals(account1, account2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        account1.setNumber("19777911977989");

        accountDAO.update(account1);
        Account account2 = (Account) accountDAO.findEntityById(account1.getId());


        assertEquals(account1, account2);

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        accountDAO.delete(account1.getId());


        Account account2 = (Account) accountDAO.findEntityById(account1.getId());
        assertNull(account2);

    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        Account account = new Account();
        account.setNumber("197777719");
        account.setAmount(19999);
        account.setAgreement(agreement1);
        account.setCurrency(currency1);

        account1 = (Account) accountDAO.insert(account);

        accountDAO.delete(account1);

        List<Account> accountList2 = accountDAO.findEntityByEntity(account1);

        assertEquals(accountList2.size(), 0);


    }


}
