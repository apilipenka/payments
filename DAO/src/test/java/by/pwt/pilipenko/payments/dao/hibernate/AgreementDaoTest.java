package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Agreement;
import by.pwt.pilipenko.payments.model.entities.Bank;
import by.pwt.pilipenko.payments.model.entities.User;
import by.pwt.pilipenko.payments.model.entities.UserRole;
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
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("hibernate");
        bankDAO = (BankDAO) DaoFactoryFactory.getInstance().createBankDAO();
        userRoleDAO = (UserRoleDAO) DaoFactoryFactory.getInstance().createUserRoleDAO();
        userDAO = (UserDAO) DaoFactoryFactory.getInstance().createUserDAO();
        agreementDAO = (AgreementDAO) DaoFactoryFactory.getInstance().createAgreementDAO();

    }

    @AfterClass
    public static void close() throws SQLException, NamingException, ClassNotFoundException {
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            bankDAO.delete(bank1);
            userDAO.delete(user1);
            userRoleDAO.delete(userRole1);
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

        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            agreement1 = agreementDAO.insert(agreement);
            Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());
            assertEquals(agreement1, agreement2);
            DaoFactoryFactory.getInstance().commit();
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Agreement> agreementList1 = new ArrayList<>();
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
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            agreementDAO.update(agreement1);
            DaoFactoryFactory.getInstance().commit();
            Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());


            assertEquals(agreement1, agreement2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            agreementDAO.delete(agreement1.getId());
            DaoFactoryFactory.getInstance().commit();

            Agreement agreement2 = agreementDAO.findEntityById(agreement1.getId());
            assertNull(agreement2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

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

        }
        try {
            agreement.setValidToDate(format.parse("10.01.1917"));
        } catch (ParseException e) {
            //it is not possible

        }
        agreement.setBank(bank1);
        agreement.setClient(user1);

        try {

            DaoFactoryFactory.getInstance().beginTransaction();
            agreement1 = agreementDAO.insert(agreement);

            agreementDAO.delete(agreement1);
            DaoFactoryFactory.getInstance().commit();


            List<Agreement> agreementList2 = agreementDAO.findEntityByEntity(agreement);

            assertEquals(agreementList2.size(), 0);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


}
