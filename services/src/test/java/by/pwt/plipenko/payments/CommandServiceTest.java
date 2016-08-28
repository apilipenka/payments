package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.plipenko.payments.model.entities.Command;
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
public class CommandServiceTest
        extends Assert {

    private static CommandService commandService;
    private static Command command1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        commandService = new CommandService();

    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException {
        commandService = null;

    }

    @Test
    public void test1GetEntity() throws Exception {

        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        command1 = commandService.insertEntity(command);


        Command command2 = commandService.getEntity(command1.getId());
        assertEquals(command1, command2);


    }

    @Test
    public void test2FindByEntity() throws SQLException, NamingException, ClassNotFoundException {

        List<Command> commandList1 = new ArrayList<Command>();
        commandList1.add(command1);


        List<Command> commandList2 = commandService.searchEntityByName(command1.getCommand());
        assertEquals(commandList1, commandList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        Command command2 = commandService.getEntityByPK(command1);
        assertEquals(command1, command2);


    }


    @Test
    public void test7Update() throws Exception {


        command1.setComment("Russian rubble test");
        commandService.updateEntity(command1);
        Command command2 = commandService.getEntity(command1.getId());

        assertEquals(command1, command2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        commandService.deleteEntity(command1.getId());


        Command command2 = commandService.getEntity(command1.getId());

        assertNull(command2);


    }


}
