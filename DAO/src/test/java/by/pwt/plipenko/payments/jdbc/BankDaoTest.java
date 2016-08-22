package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.BankDAO;
import by.pwt.pilipenko.payments.dao.jdbc.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.Currency;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankDaoTest
        extends Assert {

    private static PoolingDataSource<PoolableConnection> ds;
    private static DAOFactory df;
    private static BankDAO bankDAO;
    private static Bank bank;
    private static DaoFactoryFactory dff;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/payments?autoReconnect=true&useSSL=false&autocommit=1", "root", "awp1977");
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
                null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        ds = new PoolingDataSource<>(connectionPool);

        df = new DAOFactory((DataSource) ds);
        dff= new DaoFactoryFactory(df);

        bankDAO = DaoFactoryFactory.getInstance().createBankDAO();


    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        bankDAO.closeConnection();
        bankDAO = null;
        dff = null;
        df = null;
        ds = null;


    }

    @Test
    public void test1FindById() throws SQLException, NamingException {

        bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("121313");
        Bank bank1 = bankDAO.insert(bank);


        Bank bank2 = bankDAO.findEntityById(bank.getId());
        assertEquals(bank, bank2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException {

        List<Bank> bankList1 = new ArrayList<Bank>();
        bankList1.add(bank);


        List<Bank> bankList2 = bankDAO.findEntityByEntity(bank);
        assertEquals(bankList1, bankList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException {


        Bank bank2 = bankDAO.findEntityByPK(bank);
        assertEquals(bank, bank2);

        assertEquals(bank, bank);


    }


    @Test
    public void test7Update() throws SQLException, NamingException {


        bank.setName("AgropromBank");
        bankDAO.update(bank);
        Bank bank2 = bankDAO.findEntityById(bank.getId());

        assertEquals(bank, bank2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException {


        bankDAO.delete(bank.getId());

        Bank bank2 = bankDAO.findEntityById(bank.getId());

        assertNull(bank2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException {

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
