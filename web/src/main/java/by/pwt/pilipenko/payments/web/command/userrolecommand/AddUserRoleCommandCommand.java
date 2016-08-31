package by.pwt.pilipenko.payments.web.command.userrolecommand;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.model.entities.Command;
import by.pwt.pilipenko.payments.model.entities.UserRole;
import by.pwt.pilipenko.payments.model.entities.UserRoleCommand;
import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.services.UserRoleCommandService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;


public class AddUserRoleCommandCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String error = null;
        UserRoleCommand userRoleCommand = new UserRoleCommand();
        UserRole userRole1 = null;
        Command command1 = null;
        String page = null;
        try {
            String userRole = request.getParameter("role");
            String command = request.getParameter("commandf");

            CommandService commandService = new CommandService();
            command1 = commandService.getEntity(new Integer(command));

            UserRoleService userRoleService = new UserRoleService();
            userRole1 = userRoleService.getEntity(new Integer(userRole));

            userRoleCommand.setUserRole(userRole1);
            userRoleCommand.setCommand(command1);

            UserRoleCommandService userRoleCommandService = new UserRoleCommandService();
            userRoleCommandService.insertEntity(userRoleCommand);

            page = ConfigurationManager.getProperty("path.page.userrolecommandlist");

            request.getSession().setAttribute("success", "true");
            request.getSession().setAttribute("message", "New user role command has been successfully created.");

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("userRoleCommand", userRoleCommand);

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newuserrolecommand");

        }
        return page;
    }
}