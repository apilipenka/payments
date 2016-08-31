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

    private static DAOFactory factory;
    private static UserRoleDAO userRoleDAO;
    private static CommandDAO commandDAO;
    private static UserRole userRole1;


    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {
        DaoFactoryFactory.setDaoType("hibernate");
        factory = (DAOFactory) DaoFactoryFactory.getInstance();
        userRoleDAO = (UserRoleDAO) factory.createUserRoleDAO();
        commandDAO = (CommandDAO) factory.createCommandDAO();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        userRoleDAO = null;
        factory = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");

        Command command = commandDAO.findEntityById(26);
        userRole.addCommand(command);

        factory.beginTransaction();
        userRole1 = userRoleDAO.insert(userRole);
        factory.commit();


        UserRole userRole2 = userRoleDAO.findEntityById(userRole.getId());
        assertEquals(userRole, userRole2);


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
         factory.beginTransaction();
         userRoleDAO.update(userRole1);
         factory.commit();
         UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

         assertEquals(userRole1, userRole2);



    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {

        factory.beginTransaction();
        userRoleDAO.delete(userRole1.getId());
        factory.commit();

        UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

        assertNull(userRole2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        UserRole userRole = new UserRole();
        userRole.setName("agroprom");
        userRole.setDescription("Test role");

        Command command = commandDAO.findEntityById(26);
        userRole.addCommand(command);


        Command command11 = new Command();
        command11.setCommand("TESTLIST");
        command11.setUrl("/jsp/test-list.jsp");
        command11.setLabel("Edit tests");
        command11.setComment("Edit tests");
        userRole.addCommand(command11);

        factory.beginTransaction();



        //commandDAO.insert(command11);





        UserRole userRole2 = userRoleDAO.insert(userRole);
        //factory.commit();
        //factory.beginTransaction();
        userRoleDAO.delete(userRole2);
        factory.commit();

        List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole);

        assertEquals(userRoleList2.size(), 0);


    }


}
