package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Command;
import by.pwt.pilipenko.payments.model.entities.UserRole;
import by.pwt.pilipenko.payments.model.entities.UserRoleCommand;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRoleCommandDaoTest
        extends Assert {

    private static BaseDAO userRoleCommandDAO;
    private static BaseDAO userRoleDAO;
    private static BaseDAO commandDAO;
    private static UserRole userRole1;
    private static UserRole userRole3;
    private static Command command1;
    private static UserRoleCommand userRoleCommand1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("jdbc");
        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();
        commandDAO = DaoFactoryFactory.getInstance().createCommandDAO();
        userRoleCommandDAO = DaoFactoryFactory.getInstance().createUserRoleCommandDAO();

    }

    @AfterClass
    public static void close() throws SQLException, NamingException {

        userRoleDAO.delete(userRole1);
        userRoleDAO.delete(userRole3);
        commandDAO.delete(command1);


    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        UserRole userRole = new UserRole();
        userRole.setName("Test");
        userRole.setDescription("Test userRole");


        userRole1 = (UserRole) userRoleDAO.insert(userRole);

        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        command1 = (Command) commandDAO.insert(command);

        UserRoleCommand userRoleCommand = new UserRoleCommand();
        userRoleCommand.setUserRole(userRole1);
        userRoleCommand.setCommand(command1);

        userRoleCommand1 = (UserRoleCommand) userRoleCommandDAO.insert(userRoleCommand);
        UserRoleCommand userRoleCommand2 = (UserRoleCommand) userRoleCommandDAO.findEntityById(userRoleCommand1.getId());
        assertEquals(userRoleCommand1, userRoleCommand2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<UserRoleCommand> userRoleCommandList1 = new ArrayList<>();
        userRoleCommandList1.add(userRoleCommand1);

        List<UserRoleCommand> userRoleCommandList2 = userRoleCommandDAO.findEntityByEntity(userRoleCommand1);
        assertEquals(userRoleCommandList1, userRoleCommandList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {
        UserRoleCommand userRoleCommand2 = (UserRoleCommand) userRoleCommandDAO.findEntityByPK(userRoleCommand1);
        assertEquals(userRoleCommand1, userRoleCommand2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {

        userRole3 = new UserRole();
        userRole3.setName("Test3");
        userRole3.setDescription("Test userRole3");

        userRole3 = (UserRole) userRoleDAO.insert(userRole3);

        userRoleCommand1.setUserRole(userRole3);

        userRoleCommandDAO.update(userRoleCommand1);
        UserRoleCommand userRoleCommand2 = (UserRoleCommand) userRoleCommandDAO.findEntityById(userRoleCommand1.getId());


        assertEquals(userRoleCommand1, userRoleCommand2);

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        userRoleCommandDAO.delete(userRoleCommand1.getId());


        UserRoleCommand userRoleCommand2 = (UserRoleCommand) userRoleCommandDAO.findEntityById(userRoleCommand1.getId());
        assertNull(userRoleCommand2);

    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        UserRoleCommand userRoleCommand = new UserRoleCommand();
        userRoleCommand.setUserRole(userRole1);
        userRoleCommand.setCommand(command1);

        userRoleCommand1 = (UserRoleCommand) userRoleCommandDAO.insert(userRoleCommand);

        userRoleCommandDAO.delete(userRoleCommand1);

        List<UserRoleCommand> userRoleCommandList2 = userRoleCommandDAO.findEntityByEntity(userRoleCommand);

        assertEquals(userRoleCommandList2.size(), 0);


    }


}
