package by.pwt.plipenko.payments.jdbc;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.CommandDAO;
import by.pwt.pilipenko.payments.dao.jdbc.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.jdbc.DAOFactory;
import by.pwt.plipenko.payments.model.entities.Command;
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
public class CommandDaoTest
        extends Assert {

    private static PoolingDataSource<PoolableConnection> ds;
    private static DAOFactory df;
    private static CommandDAO commandDAO;
    private static Command command;
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

        commandDAO = DaoFactoryFactory.getInstance().createCommandDAO();


    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {

        commandDAO.closeConnection();
        commandDAO = null;
        dff = null;
        df = null;
        ds = null;


    }

    @Test
    public void test1FindById() throws SQLException, NamingException {

        command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        Command command1 = commandDAO.insert(command);


        Command command2 = commandDAO.findEntityById(command.getId());
        assertEquals(command, command2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException {

        List<Command> commandList1 = new ArrayList<Command>();
        commandList1.add(command);


        List<Command> commandList2 = commandDAO.findEntityByEntity(command);
        assertEquals(commandList1, commandList2);


    }

    @Test
    public void test6FindEntityByPK() throws SQLException, NamingException {


        Command command2 = commandDAO.findEntityByPK(command);
        assertEquals(command, command2);

        assertEquals(command, command);


    }


    @Test
    public void test7Update() throws SQLException, NamingException {


        command.setComment("Test role for update");
        commandDAO.update(command);
        Command command2 = commandDAO.findEntityById(command.getId());

        assertEquals(command, command2);


    }


    @Test
    public void test8DeleteById() throws SQLException, NamingException {


        commandDAO.delete(command.getId());

        Command command2 = commandDAO.findEntityById(command.getId());

        assertNull(command2);


    }

    @Test
    public void test9DeleteByEntity() throws SQLException, NamingException {

        command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        Command command1 = commandDAO.insert(command);

        commandDAO.delete(command);

        List<Command> commandList1 = new ArrayList<Command>();
        commandList1.add(command);


        List<Command> commandList2 = commandDAO.findEntityByEntity(command);

        assertEquals(commandList2.size(), 0);


    }


}
