package by.pwt.pilipenko.payments;

import by.pwt.pilipenko.payments.model.entities.User;
import by.pwt.pilipenko.payments.model.entities.UserRole;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest
        extends Assert {

    private static UserRoleService userRoleService;
    private static UserRole userRole1;
    private static UserService userService;
    private static User user1;


    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        userRoleService = new UserRoleService();
        userService = new UserService();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, ClassNotFoundException, NamingException {
        userRoleService.deleteEntity(userRole1.getId());


        userRoleService = null;
        userService = null;
    }

    @Test
    public void test1GetEntity() throws Exception {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleService.insertEntity(userRole);

        User user = new User();
        user.setPersonalNumber("1234567890");
        user.setFirstName("Alexander");
        user.setLastName("Pilipenko");
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        user.setBirthDate(format.parse("10.01.1977"));
        user.setLogin("apilipenka");
        user.setPassword("123456");

        user.setUserRole(userRole1);

        user1 = userService.insertEntity(user);

        User user2 = userService.getEntity(user1.getId());
        assertEquals(user1, user2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<User> userList1 = new ArrayList<User>();
        userList1.add(user1);


        List<User> userList2 = userService.searchEntityByName(user1.getPersonalNumber());
        assertEquals(userList1, userList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        User user2 = userService.getEntityByPK(user1);
        assertEquals(user1, user2);


    }


    @Test
    public void test7Update() throws Exception {


        user1.setFirstName("Russian rubble test");
        userService.updateEntity(user1);
        User user2 = userService.getEntity(user1.getId());

        assertEquals(user1, user2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        userService.deleteEntity(user1.getId());


        User user2 = userService.getEntity(user1.getId());

        assertNull(user2);


    }


}
