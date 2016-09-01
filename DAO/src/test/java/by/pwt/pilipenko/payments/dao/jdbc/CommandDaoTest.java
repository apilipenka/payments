package by.pwt.pilipenko.payments.dao.jdbc;

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

    private static BaseDAO commandDAO;
    private static Command command1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("jdbc");
        commandDAO = DaoFactoryFactory.getInstance().createCommandDAO();

    }

    @AfterClass
    public static void close() throws SQLException {

        commandDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        command1 = (Command) commandDAO.insert(command);


        Command command2 = (Command) commandDAO.findEntityById(command.getId());
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
        commandDAO.update(command1);
        Command command2 = (Command) commandDAO.findEntityById(command1.getId());

        assertEquals(command1, command2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        commandDAO.delete(command1.getId());

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
        command1 = (Command) commandDAO.insert(command);

        commandDAO.delete(command);

        List<Command> commandList2 = commandDAO.findEntityByEntity(command);

        assertEquals(commandList2.size(), 0);


    }


}
