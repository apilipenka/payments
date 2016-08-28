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
public class CardServiceTest
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
    private static CardService cardService;
    private static Card card1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        bankService = new BankService();
        userRoleService = new UserRoleService();
        userService = new UserService();
        agreementService = new AgreementService();
        accountService = new AccountService();
        currencyService = new CurrencyService();
        cardService = new CardService();
    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, ClassNotFoundException, NamingException {
        accountService.deleteEntity(account1.getId());
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
        cardService = null;
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

        Card card = new Card();
        card.setNumber("122333444555");
        card.setName("Aliaksandr Pilipenka");
        try {
            card.setValidToDate(format.parse("10.01.2017"));
        } catch (ParseException e) {
            //it is not possible
            ;
        }
        card.setAccount(account1);

        card1 = cardService.insertEntity(card);

        Card card2 = cardService.getEntity(card1.getId());
        assertEquals(card1, card2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<Card> cardList1 = new ArrayList<>();
        cardList1.add(card1);


        List<Card> cardList2 = cardService.searchEntityByName(card1.getNumber());
        assertEquals(cardList1, cardList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Card card2 = cardService.getEntityByPK(card1);
        assertEquals(card1, card2);


    }


    @Test
    public void test7Update() throws Exception {

        card1.setNumber("010203");
        cardService.updateEntity(card1);
        Card card2 = cardService.getEntity(card1.getId());

        assertEquals(card1, card2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        cardService.deleteEntity(card1.getId());


        Card card2 = cardService.getEntity(card1.getId());

        assertNull(card2);


    }


}
