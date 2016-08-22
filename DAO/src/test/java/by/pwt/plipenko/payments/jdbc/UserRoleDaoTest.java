package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserRoleDAO;
import by.pwt.pilipenko.payments.dao.jdbc.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
import by.pwt.plipenko.payments.model.entities.UserRole;
import by.pwt.plipenko.payments.model.entities.Currency;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRoleDaoTest
        extends Assert {

    private static PoolingDataSource<PoolableConnection> ds;
    private static DAOFactory df;
    private static UserRoleDAO userRoleDAO;
    private static UserRole userRole;
    private static DaoFactoryFactory dff;

    @BeforeClass
    public static void intit() throws NamingException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/payments?autoReconnect=true&useSSL=false&autocommit=1", "root", "awp1977");
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
                null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        ds = new PoolingDataSource<>(connectionPool);

        df = new DAOFactory((DataSource) ds);
        dff= new DaoFactoryFactory(df);

        userRoleDAO = DaoFactoryFactory.getInstance().createUserRoleDAO();


    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        userRoleDAO.closeConnection();
        userRoleDAO = null;
        dff = null;
        df = null;
        ds = null;


    }

    @Test
    public void test1FindById() throws SQLException, NamingException {

        userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        UserRole userRole1 = userRoleDAO.insert(userRole);


        UserRole userRole2 = userRoleDAO.findEntityById(userRole.getId());
        assertEquals(userRole, userRole2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException {

        List<UserRole> userRoleList1 = new ArrayList<UserRole>();
        userRoleList1.add(userRole);


        List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole);
        assertEquals(userRoleList1, userRoleList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException {


        UserRole userRole2 = userRoleDAO.findEntityByPK(userRole);
        assertEquals(userRole, userRole2);

        assertEquals(userRole, userRole);


    }


    @Test
    public void test7Update() throws SQLException, NamingException {


        userRole.setDescription("Test role for update");
        userRoleDAO.update(userRole);
        UserRole userRole2 = userRoleDAO.findEntityById(userRole.getId());

        assertEquals(userRole, userRole2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException {


        userRoleDAO.delete(userRole.getId());

        UserRole userRole2 = userRoleDAO.findEntityById(userRole.getId());

        assertNull(userRole2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException {

        userRole = new UserRole();
        userRole.setName("agroprom");
        userRole.setDescription("Test role");
        UserRole userRole1 = userRoleDAO.insert(userRole);

        userRoleDAO.delete(userRole);

        List<UserRole> userRoleList1 = new ArrayList<UserRole>();
        userRoleList1.add(userRole);


        List<UserRole> userRoleList2 = userRoleDAO.findEntityByEntity(userRole);

        assertEquals(userRoleList2.size(), 0);


    }


}
