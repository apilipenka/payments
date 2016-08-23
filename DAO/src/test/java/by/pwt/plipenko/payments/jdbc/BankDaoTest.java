package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.BankDAO;
import by.pwt.plipenko.payments.model.entities.Bank;
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
    private static Bank bank;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        bankDAO = DaoFactoryFactory.getInstance().createBankDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        bankDAO.closeConnection();
        bankDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("121313");
        Bank bank1 = bankDAO.insert(bank);


        Bank bank2 = bankDAO.findEntityById(bank.getId());
        assertEquals(bank, bank2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Bank> bankList1 = new ArrayList<Bank>();
        bankList1.add(bank);


        List<Bank> bankList2 = bankDAO.findEntityByEntity(bank);
        assertEquals(bankList1, bankList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        Bank bank2 = bankDAO.findEntityByPK(bank);
        assertEquals(bank, bank2);

        assertEquals(bank, bank);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        bank.setName("AgropromBank");
        bankDAO.update(bank);
        Bank bank2 = bankDAO.findEntityById(bank.getId());

        assertEquals(bank, bank2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        bankDAO.delete(bank.getId());

        Bank bank2 = bankDAO.findEntityById(bank.getId());

        assertNull(bank2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        bank = new Bank();
        bank.setName("agroprom");
        bank.setUNN("121313");
        Bank bank1 = bankDAO.insert(bank);

        bankDAO.delete(bank);

        List<Bank> bankList1 = new ArrayList<Bank>();
        bankList1.add(bank);


        List<Bank> bankList2 = bankDAO.findEntityByEntity(bank);

        assertEquals(bankList2.size(), 0);


    }


}
