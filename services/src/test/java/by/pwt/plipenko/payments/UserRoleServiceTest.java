package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.plipenko.payments.model.entities.UserRole;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darrko on 23.08.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRoleServiceTest
        extends Assert {

    private static UserRoleService userRoleService;
    private static UserRole userRole1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        userRoleService = new UserRoleService();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {
        userRoleService = null;

    }

    @Test
    public void test1GetEntity() throws Exception {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleService.insertEntity(userRole);


        UserRole userRole2 = userRoleService.getEntity(userRole1.getId());
        assertEquals(userRole1, userRole2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<UserRole> userRoleList1 = new ArrayList<UserRole>();
        userRoleList1.add(userRole1);


        List<UserRole> userRoleList2 = userRoleService.searchEntityByName(userRole1.getName());
        assertEquals(userRoleList1, userRoleList2);


    }


    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException, ClassNotFoundException {

        UserRole userRole2 = userRoleService.getEntityByPK(userRole1);
        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test7Update() throws Exception {


        userRole1.setDescription("Russian rubble test");
        userRoleService.updateEntity(userRole1);
        UserRole userRole2 = userRoleService.getEntity(userRole1.getId());

        assertEquals(userRole1, userRole2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        userRoleService.deleteEntity(userRole1.getId());


        UserRole userRole2 = userRoleService.getEntity(userRole1.getId());

        assertNull(userRole2);


    }


}
