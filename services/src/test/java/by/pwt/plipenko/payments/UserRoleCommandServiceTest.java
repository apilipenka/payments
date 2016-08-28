package by.pwt.plipenko.payments;

import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.services.UserRoleCommandService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.plipenko.payments.model.entities.Command;
import by.pwt.plipenko.payments.model.entities.UserRole;
import by.pwt.plipenko.payments.model.entities.UserRoleCommand;
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
public class UserRoleCommandServiceTest
        extends Assert {

    protected static UserRoleCommandService userRoleCommandService;
    private static UserRoleService userRoleService;
    private static UserRole userRole1;
    private static UserRole userRole2;
    private static CommandService commandService;
    private static Command command1;
    private static UserRoleCommand userRoleCommand1;

    @BeforeClass
    public static void init() throws NamingException, ClassNotFoundException, SQLException {

        userRoleService = new UserRoleService();
        commandService = new CommandService();
        userRoleCommandService = new UserRoleCommandService();
    }

    @AfterClass
    public static void tearDownToHexStringData() throws SQLException, ClassNotFoundException, NamingException {
        userRoleService.deleteEntity(userRole1.getId());
        userRoleService.deleteEntity(userRole2.getId());
        commandService.deleteEntity(command1.getId());

        userRoleService = null;
        commandService = null;
        userRoleCommandService = null;
    }

    @Test
    public void test1GetEntity() throws Exception {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom");
        userRole.setDescription("Test role");
        userRole1 = userRoleService.insertEntity(userRole);

        Command command = new Command();
        command.setCommand("TESTLIST");
        command.setUrl("/jsp/test-list.jsp");
        command.setLabel("Edit tests");
        command.setComment("Edit tests");
        command1 = commandService.insertEntity(command);


        UserRoleCommand userRoleCommand = new UserRoleCommand();
        userRoleCommand.setUserRole(userRole1);
        userRoleCommand.setCommand(command1);


        userRoleCommand1 = userRoleCommandService.insertEntity(userRoleCommand);

        UserRoleCommand userRoleCommand2 = userRoleCommandService.getEntity(userRoleCommand1.getId());
        assertEquals(userRoleCommand1, userRoleCommand2);


    }

    @Test
    public void test2FindByEntity() throws Exception {

        List<UserRoleCommand> userRoleCommandList1 = new ArrayList<UserRoleCommand>();
        userRoleCommandList1.add(userRoleCommand1);


        List<UserRoleCommand> userRoleCommandList2 = userRoleCommandService.searchEntityByName(userRoleCommand1.getUserRole().getName());
        assertEquals(userRoleCommandList1, userRoleCommandList2);


    }


    @Test
    public void test6FindEntityByPK() throws Exception {

        UserRoleCommand userRoleCommand2 = userRoleCommandService.getEntityByPK(userRoleCommand1);
        assertEquals(userRoleCommand1, userRoleCommand2);


    }


    @Test
    public void test7Update() throws Exception {

        UserRole userRole = new UserRole();
        userRole.setName("Agroprom12");
        userRole.setDescription("Test role12");
        userRole2 = userRoleService.insertEntity(userRole);

        userRoleCommand1.setUserRole(userRole2);
        userRoleCommandService.updateEntity(userRoleCommand1);
        UserRoleCommand userRoleCommand2 = userRoleCommandService.getEntity(userRoleCommand1.getId());

        assertEquals(userRoleCommand1, userRoleCommand2);


    }


    @Test
    public void test8DeleteById() throws Exception {

        userRoleCommandService.deleteEntity(userRoleCommand1.getId());


        UserRoleCommand userRoleCommand2 = userRoleCommandService.getEntity(userRoleCommand1.getId());

        assertNull(userRoleCommand2);


    }


}
