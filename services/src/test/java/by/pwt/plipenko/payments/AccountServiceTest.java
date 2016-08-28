package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.services.*;
import by.pwt.plipenko.payments.model.entities.*;
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
public class AccountServiceTest
        extends Assert {

    private static BankService bankService;
    private static Bank bank1;
    private static UserService userService;
    private static User user1;
    private static AgreementService agreementService;
    private static Agreement agreement1;
    private static UserRoleService userRoleService;
    private static UserRole userRole1;
    private static CurrencyService currencyService;
    private static Currency currency1;
    private static AccountService accountService;
    private static Account account1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        bankService = new BankService();
        userRoleService = new UserRoleService();
        userService = new UserService();
        agreementService = new AgreementService();
        accountService = new AccountService();
        currencyService = new CurrencyService();
    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, ClassNotFoundException, NamingException {
        agreementService.deleteEntity(agreement1.getId());
        bankService.deleteEntity(bank1.getId());
        userService.deleteEntity(user1.getId());
        userRoleService.deleteEntity(userRole1.getId());
        currencyService.deleteEntity(currency1.getId());

        bankService = null;
        userService = null;
        userRoleService = null;
        agreementService = null;
        accountService = null;
        currencyService = null;
    }

    @Test
    public void test1GetEntity() throws Exception {

        Bank bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("1977779911");
        bank1 = bankService.insertEntity(bank);

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleService.insertEntity(userRole);

        User user = new User();
        user.setPersonalNumber("1234567890");
        user.setFirstName("Alexander");
        user.setLastName("Pilipenko");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        user.setBirthDate(format.parse("10.01.1977"));
        user.setLogin("apilipenka");
        user.setPassword("123456");

        user.setUserRole(userRole1);

        user1 = userService.insertEntity(user);


        Agreement agreement = new Agreement();
        agreement.setNumber("19777791");

        try {
            agreement.setValidFromDate(format.parse("10.01.1907"));
        } catch (ParseException e) {
            //it is not possible
            ;
        }
        try {
            agreement.setValidToDate(format.parse("10.01.1917"));
        } catch (ParseException e) {
            //it is not possible
            ;
        }
        agreement.setBank(bank1);
        agreement.setClient(user1);

        agreement1 = agreementService.insertEntity(agreement);

        Currency currency = new Currency();
        currency.setCode("643");
        currency.setMnemoCode("RUR");
        currency.setName("Russian rubble");
        currency1 = currencyService.insertEntity(currency);

        Account account = new Account();
        account.setNumber("197777719");
        account.setAmount(19999);
        account.setAgreement(agreement1);
        account.setCurrency(currency1);
        account1 = accountService.insertEntity(account);

        Account account2 = accountService.getEntity(account1.getId());
        assertEquals(account1, account2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<Account> accountList1 = new ArrayList<Account>();
        accountList1.add(account1);


        List<Account> accountList2 = accountService.searchEntityByName(account1.getNumber());
        assertEquals(accountList1, accountList2);


    }


    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {

        Account account2 = accountService.getEntityByPK(account1);
        assertEquals(account1, account2);


    }


    @Test
    public void test7Update() throws Exception {

        account1.setNumber("010203");
        accountService.updateEntity(account1);
        Account account2 = accountService.getEntity(account1.getId());

        assertEquals(account1, account2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        accountService.deleteEntity(account1.getId());


        Account account2 = accountService.getEntity(account1.getId());

        assertNull(account2);


    }


}