package by.pwt.pilipenko.payments;

import by.pwt.pilipenko.payments.model.entities.Bank;
import by.pwt.pilipenko.payments.services.BankService;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankServiceTest
        extends Assert {

    private static BankService bankService;
    private static Bank bank1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        bankService = new BankService();

    }

    @AfterClass
    public static void close() throws SQLException {
        bankService = null;

    }

    @Test
    public void test1GetEntity() throws Exception {

        Bank bank = new Bank();
        bank.setName("Agroprom");
        bank.setUNN("123456123");
        bank1 = bankService.insertEntity(bank);


        Bank bank2 = bankService.getEntity(bank1.getId());
        assertEquals(bank1, bank2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Bank> bankList1 = new ArrayList<Bank>();
        bankList1.add(bank1);


        List<Bank> bankList2 = bankService.searchEntityByName(bank1.getName());
        assertEquals(bankList1, bankList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Bank bank2 = bankService.getEntityByPK(bank1);
        assertEquals(bank1, bank2);


    }


    @Test
    public void test7Update() throws Exception {


        bank1.setName("Russian rubble test");
        bankService.updateEntity(bank1);
        Bank bank2 = bankService.getEntity(bank1.getId());

        assertEquals(bank1, bank2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        bankService.deleteEntity(bank1.getId());


        Bank bank2 = bankService.getEntity(bank1.getId());

        assertNull(bank2);


    }


}
