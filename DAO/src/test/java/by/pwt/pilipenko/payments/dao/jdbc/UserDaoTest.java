package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.User;
import by.pwt.pilipenko.payments.model.entities.UserRole;
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

    private static BaseDAO userDAO;
    private static BaseDAO userRoleDAO;
    //private static User user;
    private static User user1;
    private static UserRole userRole1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        userDAO = DaoFactoryFactory.getInstance().createUserDAO();
        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();


    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, NamingException {

        userRoleDAO.delete(userRole1);


    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        User user = new User();

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

        userRole1 = (UserRole) userRoleDAO.insert(userRole);

        user.setUserRole(userRole1);

        user1 = (User) userDAO.insert(user);


        User user2 = (User) userDAO.findEntityById(user.getId());
        assertEquals(user1, user2);

    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<User> userList1 = new ArrayList<>();
        userList1.add(user1);


        List<User> userList2 = userDAO.findEntityByEntity(user1);
        assertEquals(userList1, userList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {


        User user2 = (User) userDAO.findEntityByPK(user1);
        assertEquals(user1, user2);


    }


    @Test
    public void test7Update() throws SQLException, NamingException, ClassNotFoundException {


        user1.setLogin("TestUserTest");
        userDAO.update(user1);
        User user2 = (User) userDAO.findEntityById(user1.getId());

        assertEquals(user1, user2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        userDAO.delete(user1.getId());

        User user2 = (User) userDAO.findEntityById(user1.getId());

        assertNull(user2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException, ClassNotFoundException {

        User user = new User();

        user.setPersonalNumber("1234567890");
        user.setFirstName("Alexander");
        user.setLastName("Pilipenko");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        user.setBirthDate(format.parse("10.01.1977"));
        user.setLogin("apilipenka");
        user.setPassword("123456");

        user.setUserRole(userRole1);

        user1 = (User) userDAO.insert(user);

        userDAO.delete(user1);

        List<User> userList2 = userDAO.findEntityByEntity(user1);

        assertEquals(userList2.size(), 0);


    }


}
