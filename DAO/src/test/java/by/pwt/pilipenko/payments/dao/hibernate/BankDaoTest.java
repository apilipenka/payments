package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Bank;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankDaoTest
        extends Assert {

    private static BankDAO bankDAO;
    private static Bank bank1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("hibernate");
        bankDAO = (BankDAO) DaoFactoryFactory.getInstance().createBankDAO();

    }

    @AfterClass
    public static void close() throws SQLException {

        bankDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        Bank bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("121313");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            bank1 = bankDAO.insert(bank);
            DaoFactoryFactory.getInstance().commit();


            Bank bank2 = bankDAO.findEntityById(bank.getId());
            assertEquals(bank1, bank2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Bank> bankList1 = new ArrayList<>();
        bankList1.add(bank1);


        List<Bank> bankList2 = bankDAO.findEntityByEntity(bank1);
        assertEquals(bankList1, bankList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        Bank bank2 = bankDAO.findEntityByPK(bank1);
        assertEquals(bank1, bank2);

        assertEquals(bank1, bank1);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        bank1.setName("AgropromBank");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            bankDAO.update(bank1);
            DaoFactoryFactory.getInstance().commit();
            Bank bank2 = bankDAO.findEntityById(bank1.getId());

            assertEquals(bank1, bank2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {

        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            bankDAO.delete(bank1.getId());
            DaoFactoryFactory.getInstance().commit();
            Bank bank2 = bankDAO.findEntityById(bank1.getId());

            assertNull(bank2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        Bank bank = new Bank();
        bank.setName("agroprom");
        bank.setUNN("121313");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            bank1 = bankDAO.insert(bank);

            bankDAO.delete(bank);
            DaoFactoryFactory.getInstance().commit();
            List<Bank> bankList2 = bankDAO.findEntityByEntity(bank);

            assertEquals(0, bankList2.size());
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


}
