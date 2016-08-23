package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserDAO;
import by.pwt.pilipenko.payments.dao.jdbc.UserRoleDAO;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest
        extends Assert {

    private static UserDAO userDAO;
    private static UserRoleDAO userRoleDAO;
    private static User user;
    private static User user1;
    private static UserRole userRole1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        userDAO = DaoFactoryFactory.getInstance().createUserDAO();
        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();


    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        userRoleDAO.delete(userRole1);

        userDAO.closeConnection();
        userDAO = null;

        userRoleDAO.closeConnection();
        userRoleDAO = null;


    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        user = new User();

        user.setPersonalNumber("1234567890");
        user.setFirstName("Alexander");
        user.setLastName("Pilipenko");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        user.setBirthDate(format.parse("10.01.1977"));
        user.setLogin("apilipenka");
        user.setPassword("123456");

        UserRole userRole = new UserRole();
        userRole.setName("Test");
        userRole.setName("Test user");

        userRole1 = userRoleDAO.insert(userRole);

        user.setUserRole(userRole1);

        user1 = userDAO.insert(user);


        User user2 = userDAO.findEntityById(user.getId());
        assertEquals(user, user2);

    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<User> userList1 = new ArrayList<User>();
        userList1.add(user);


        List<User> userList2 = userDAO.findEntityByEntity(user);
        assertEquals(userList1, userList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        User user2 = userDAO.findEntityByPK(user);
        assertEquals(user, user2);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        user.setLogin("TestUserTest");
        userDAO.update(user);
        User user2 = userDAO.findEntityById(user.getId());

        assertEquals(user, user2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        userDAO.delete(user.getId());

        User user2 = userDAO.findEntityById(user1.getId());

        assertNull(user2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        user = new User();

        user.setPersonalNumber("1234567890");
        user.setFirstName("Alexander");
        user.setLastName("Pilipenko");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        user.setBirthDate(format.parse("10.01.1977"));
        user.setLogin("apilipenka");
        user.setPassword("123456");

        user.setUserRole(userRole1);

        user1 = userDAO.insert(user);

        userDAO.delete(user);

        List<User> userList1 = new ArrayList<User>();
        userList1.add(user);


        List<User> userList2 = userDAO.findEntityByEntity(user);

        assertEquals(userList2.size(), 0);


    }


}
