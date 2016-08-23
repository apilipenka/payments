package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.CommandDAO;
import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserRoleCommandDAO;
import by.pwt.pilipenko.payments.dao.jdbc.UserRoleDAO;
import by.pwt.plipenko.payments.model.entities.Command;
import by.pwt.plipenko.payments.model.entities.UserRole;
import by.pwt.plipenko.payments.model.entities.UserRoleCommand;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRoleCommandDaoTest
        extends Assert {

    private static UserRoleCommandDAO userRoleCommandDAO;
    private static UserRoleDAO userRoleDAO;
    private static CommandDAO commandDAO;
    private static UserRole userRole1;
    private static UserRole userRole3;
    private static Command command1;
    private static UserRoleCommand userRoleCommand1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();
        commandDAO = DaoFactoryFactory.getInstance().createCommandDAO();
        userRoleCommandDAO = DaoFactoryFactory.getInstance().createUserRoleCommandDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, NamingException {
        try {
            userRoleDAO.delete(userRole1);
            userRoleDAO.delete(userRole3);
            commandDAO.delete(command1);



        } finally {
            if (userRoleDAO != null) {
                userRoleDAO.closeConnection();

            }
            if (commandDAO != null) {
                commandDAO.closeConnection();
            }
            if (userRoleCommandDAO != null) {
                userRoleCommandDAO.closeConnection();
            }
        }

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

            UserRole userRole = new UserRole();
            userRole = new UserRole();
            userRole.setName("Test");
            userRole.setDescription("Test userRole");


            userRole1 = userRoleDAO.insert(userRole);

            Command command = new Command();
            command = new Command();
            command.setCommand("TESTLIST");
            command.setUrl("/jsp/test-list.jsp");
            command.setLabel("Edit tests");
            command.setComment("Edit tests");
            command1 = commandDAO.insert(command);

            UserRoleCommand userRoleCommand = new UserRoleCommand();
            userRoleCommand.setUserRole(userRole1);
            userRoleCommand.setCommand(command1);

            userRoleCommand1 = userRoleCommandDAO.insert(userRoleCommand);
            UserRoleCommand userRoleCommand2 = userRoleCommandDAO.findEntityById(userRoleCommand1.getId());
            assertEquals(userRoleCommand1, userRoleCommand2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

            List<UserRoleCommand> userRoleCommandList1 = new ArrayList<UserRoleCommand>();
            userRoleCommandList1.add(userRoleCommand1);

            List<UserRoleCommand> userRoleCommandList2 = userRoleCommandDAO.findEntityByEntity(userRoleCommand1);
            assertEquals(userRoleCommandList1, userRoleCommandList2);



    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {
            UserRoleCommand userRoleCommand2 = userRoleCommandDAO.findEntityByPK(userRoleCommand1);
            assertEquals(userRoleCommand1, userRoleCommand2);

    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {

            userRole3 = new UserRole();
            userRole3.setName("Test3");
            userRole3.setDescription("Test userRole3");

            userRole3 = userRoleDAO.insert(userRole3);

            userRoleCommand1.setUserRole(userRole3);

            userRoleCommandDAO.update(userRoleCommand1);
            UserRoleCommand userRoleCommand2 = userRoleCommandDAO.findEntityById(userRoleCommand1.getId());


            assertEquals(userRoleCommand1, userRoleCommand2);

    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
            userRoleCommandDAO.delete(userRoleCommand1.getId());


            UserRoleCommand userRoleCommand2 = userRoleCommandDAO.findEntityById(userRoleCommand1.getId());
            assertNull(userRoleCommand2);

    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        UserRoleCommand userRoleCommand = new UserRoleCommand();
        userRoleCommand.setUserRole(userRole1);
        userRoleCommand.setCommand(command1);

        userRoleCommand1 = userRoleCommandDAO.insert(userRoleCommand);

        userRoleCommandDAO.delete(userRoleCommand1);

        List<UserRoleCommand> userRoleCommandList1 = new ArrayList<UserRoleCommand>();
        userRoleCommandList1.add(userRoleCommand1);


        List<UserRoleCommand> userRoleCommandList2 = userRoleCommandDAO.findEntityByEntity(userRoleCommand);

        assertEquals(userRoleCommandList2.size(), 0);


    }


}
