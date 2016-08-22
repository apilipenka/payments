package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserDAO;
import by.pwt.pilipenko.payments.dao.jdbc.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserRoleDAO;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.Currency;
import by.pwt.plipenko.payments.model.entities.UserRole;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest
        extends Assert {

    private static PoolingDataSource<PoolableConnection> ds;
    private static DAOFactory df;
    private static UserDAO userDAO;
    private static UserRoleDAO userRoleDAO;
    private static User user;
    private static User user1;
    private static UserRole userRole1;
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
        dff = null;
        df = null;
        ds = null;


    }

    @Test
    public void test1FindById() throws SQLException, NamingException, ParseException {

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
    public void test2FindByEntity() throws SQLException, NamingException {

        List<User> userList1 = new ArrayList<User>();
        userList1.add(user);


        List<User> userList2 = userDAO.findEntityByEntity(user);
        assertEquals(userList1, userList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException {


        User user2 = userDAO.findEntityByPK(user);
        assertEquals(user, user2);

        assertEquals(user, user);


    }


    @Test
    public void test7Update() throws SQLException, NamingException {


        user.setLogin("TestUserTest");
        userDAO.update(user);
        User user2 = userDAO.findEntityById(user.getId());

        assertEquals(user, user2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException {


        userDAO.delete(user.getId());

        User user2 = userDAO.findEntityById(user1.getId());

        assertNull(user2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException, ParseException {

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
