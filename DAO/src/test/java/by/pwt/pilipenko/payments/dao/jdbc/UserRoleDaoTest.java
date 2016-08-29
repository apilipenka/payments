package by.pwt.pilipenko.payments.dao.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
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
    private static UserRole userRole1;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();


    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        userRoleDAO = null;

    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ClassNotFoundException {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleDAO.insert(userRole);


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
        userRoleDAO.update(userRole1);
        UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException, ClassNotFoundException {


        userRoleDAO.delete(userRole1.getId());

        UserRole userRole2 = userRoleDAO.findEntityById(userRole1.getId());

        assertNull(userRole2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ClassNotFoundException {

        UserRole userRole = new UserRole();
        userRole.setName("agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleDAO.insert(userRole);

        userRoleDAO.delete(userRole1);

        List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole);

        assertEquals(userRoleList2.size(), 0);


    }


}
