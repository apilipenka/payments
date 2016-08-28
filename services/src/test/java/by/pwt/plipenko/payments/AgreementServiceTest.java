package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.entities.Agreement;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;
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
public class AgreementServiceTest
        extends Assert {

    private static BankService bankService;
    private static Bank bank1;
    private static UserService userService;
    private static User user1;
    private static AgreementService agreementService;
    private static Agreement agreement1;
    private static UserRoleService userRoleService;
    private static UserRole userRole1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        bankService = new BankService();
        userRoleService = new UserRoleService();
        userService = new UserService();
        agreementService = new AgreementService();
    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, ClassNotFoundException, NamingException {
        bankService.deleteEntity(bank1.getId());
        userService.deleteEntity(user1.getId());
        userRoleService.deleteEntity(userRole1.getId());


        bankService = null;
        userService = null;
        userRoleService = null;
        agreementService = null;
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

        Agreement agreement2 = agreementService.getEntity(agreement1.getId());
        assertEquals(agreement1, agreement2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<Agreement> agreementList1 = new ArrayList<Agreement>();
        agreementList1.add(agreement1);


        List<Agreement> agreementList2 = agreementService.searchEntityByName(agreement1.getNumber());
        assertEquals(agreementList1, agreementList2);


    }


    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {

        Agreement agreement2 = agreementService.getEntityByPK(agreement1);
        assertEquals(agreement1, agreement2);


    }


    @Test
    public void test7Update() throws Exception {

        agreement1.setNumber("010203");
        agreementService.updateEntity(agreement1);
        Agreement agreement2 = agreementService.getEntity(agreement1.getId());

        assertEquals(agreement1, agreement2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        agreementService.deleteEntity(agreement1.getId());


        Agreement agreement2 = agreementService.getEntity(agreement1.getId());

        assertNull(agreement2);


    }


}
