package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Command;
import by.pwt.pilipenko.payments.model.entities.UserRole;
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
public class UserRoleDaoTest
        extends Assert {

    private static UserRoleDAO userRoleDAO;
    private static CommandDAO commandDAO;
    private static UserRole userRole1;
    private static Command command1;


    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("hibernate");
        userRoleDAO = (UserRoleDAO) DaoFactoryFactory.getInstance().createUserRoleDAO();
        commandDAO = (CommandDAO) DaoFactoryFactory.getInstance().createCommandDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, NamingException, ClassNotFoundException {
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            commandDAO.delete(command1);
            DaoFactoryFactory.getInstance().commit();
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
        finally {
            userRoleDAO = null;
            commandDAO = null;
        }

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");


        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");

        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            command1 = commandDAO.insert(command);
            userRole.addCommand(command1);
            userRole1 = userRoleDAO.insert(userRole);
            DaoFactoryFactory.getInstance().commit();


            UserRole userRole2 = userRoleDAO.findEntityById(userRole.getId());
            assertEquals(userRole, userRole2);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<UserRole> userRoleList1 = new ArrayList<>();
        userRoleList1.add(userRole1);


        List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole1);
        assertEquals(userRoleList1, userRoleList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        UserRole userRole2 = userRoleDAO.findEntityByPK(userRole1);
        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        userRole1.setDescription("Test role for update");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            userRoleDAO.update(userRole1);
            DaoFactoryFactory.getInstance().commit();
            UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

            assertEquals(userRole1, userRole2);

        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            userRoleDAO.delete(userRole1.getId());
            DaoFactoryFactory.getInstance().commit();

            UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

            assertNull(userRole2);

        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        UserRole userRole = new UserRole();
        userRole.setName("agroprom");
        userRole.setDescription("Test role");

        userRole.addCommand(command1);

        try {
            DaoFactoryFactory.getInstance().beginTransaction();


            commandDAO.insert(command1);
            UserRole userRole2 = userRoleDAO.insert(userRole);

            ((DAOFactory) DaoFactoryFactory.getInstance()).getSession().flush();

            userRoleDAO.delete(userRole2);

            DaoFactoryFactory.getInstance().commit();

            List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole);

            assertEquals(userRoleList2.size(), 0);
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


}
