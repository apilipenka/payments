package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAO;
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

    private static BaseDAO bankDAO;
    private static Bank bank1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("jdbc");
        bankDAO = DaoFactoryFactory.getInstance().createBankDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        bankDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        Bank bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("121313");
        bank1 = (Bank) bankDAO.insert(bank);


        Bank bank2 = (Bank) bankDAO.findEntityById(bank.getId());
        assertEquals(bank1, bank2);


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


        Bank bank2 = (Bank) bankDAO.findEntityByPK(bank1);
        assertEquals(bank1, bank2);

        assertEquals(bank1, bank1);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        bank1.setName("AgropromBank");
        bankDAO.update(bank1);
        Bank bank2 = (Bank) bankDAO.findEntityById(bank1.getId());

        assertEquals(bank1, bank2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        bankDAO.delete(bank1.getId());

        Bank bank2 = (Bank) bankDAO.findEntityById(bank1.getId());

        assertNull(bank2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        Bank bank = new Bank();
        bank.setName("agroprom");
        bank.setUNN("121313");
        bank1 = (Bank) bankDAO.insert(bank);

        bankDAO.delete(bank);

        List<Bank> bankList2 = bankDAO.findEntityByEntity(bank);

        assertEquals(bankList2.size(), 0);


    }


}
