package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.AgreementDAO;
import by.pwt.pilipenko.payments.dao.jdbc.BankDAO;
import by.pwt.pilipenko.payments.dao.jdbc.UserDAO;
import by.pwt.pilipenko.payments.dao.jdbc.UserRoleDAO;
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
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgreementDaoTest
        extends Assert {

    private static AgreementDAO agreementDAO;
    private static BankDAO bankDAO;
    private static UserDAO userDAO;
    private static UserRoleDAO userRoleDAO;
    private static Bank bank1;
    private static User user1;
    private static UserRole userRole1;
    private static Agreement agreement1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        bankDAO = DaoFactoryFactory.getInstance().createBankDAO();
        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();
        userDAO = DaoFactoryFactory.getInstance().createUserDAO();
        agreementDAO = DaoFactoryFactory.getInstance().createAgreementDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, NamingException {
        try {
            bankDAO.delete(bank1);
            userDAO.delete(user1);
            userRoleDAO.delete(userRole1);


        } finally {
            if (bankDAO != null) {
                bankDAO.closeConnection();
            }
            if (userRoleDAO != null) {
                userRoleDAO.closeConnection();
            }
            if (userDAO != null) {
                userDAO.closeConnection();
            }
            if (agreementDAO != null) {
                agreementDAO.closeConnection();
            }
        }

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        Bank bank = new Bank();
        bank.setName("Test bank");
        bank.setUNN("19777791");
        bank.setName("Tests bank");
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


        agreement1 = agreementDAO.insert(agreement);
        Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());
        assertEquals(agreement1, agreement2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Agreement> agreementList1 = new ArrayList<Agreement>();
        agreementList1.add(agreement1);

        List<Agreement> agreementList2 = agreementDAO.findEntityByEntity(agreement1);
        assertEquals(agreementList1, agreementList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {
        Agreement agreement2 = agreementDAO.findEntityByPK(agreement1);
        assertEquals(agreement1, agreement2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        agreement1.setNumber("19777911977");

        agreementDAO.update(agreement1);
        Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());


        assertEquals(agreement1, agreement2);

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        agreementDAO.delete(agreement1.getId());


        Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());
        assertNull(agreement2);

    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        Agreement agreement = new Agreement();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
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

        agreement1 = agreementDAO.insert(agreement);

        agreementDAO.delete(agreement1);

        List<Agreement> agreementList1 = new ArrayList<Agreement>();
        agreementList1.add(agreement1);


        List<Agreement> agreementList2 = agreementDAO.findEntityByEntity(agreement);

        assertEquals(agreementList2.size(), 0);


    }


}
