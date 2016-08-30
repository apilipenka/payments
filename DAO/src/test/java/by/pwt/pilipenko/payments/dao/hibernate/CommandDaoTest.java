package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Command;
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
public class CommandDaoTest
        extends Assert {

    private static DAOFactory factory;
    private static CommandDAO commandDAO;
    private static Command command1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        factory =  new DAOFactory();
        commandDAO = (CommandDAO)factory.createCommandDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        commandDAO = null;
        factory = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");

        factory.beginTransaction();
        command1 = (Command) commandDAO.insert(command);
        factory.commit();



        Command command2 = (Command) commandDAO.findEntityById(command1.getId());
        assertEquals(command, command2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Command> commandList1 = new ArrayList<>();
        commandList1.add(command1);


        List<Command> commandList2 = commandDAO.findEntityByEntity(command1);
        assertEquals(commandList1, commandList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        Command command2 = (Command) commandDAO.findEntityByPK(command1);
        assertEquals(command1, command2);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        command1.setComment("Test role for update");
        factory.beginTransaction();
        commandDAO.update(command1);
        factory.commit();
        Command command2 = (Command) commandDAO.findEntityById(command1.getId());

        assertEquals(command1, command2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {

        factory.beginTransaction();
        commandDAO.delete(command1.getId());
        factory.commit();

        Command command2 = (Command) commandDAO.findEntityById(command1.getId());

        assertNull(command2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        factory.beginTransaction();
        command1 = (Command) commandDAO.insert(command);

        commandDAO.delete(command);
        factory.commit();

        List<Command> commandList2 = commandDAO.findEntityByEntity(command);

        assertEquals(commandList2.size(), 0);


    }


}
