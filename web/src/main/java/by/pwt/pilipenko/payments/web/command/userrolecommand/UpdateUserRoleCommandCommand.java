package by.pwt.pilipenko.payments.web.command.userrolecommand;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.services.UserRoleCommandService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.model.entities.Command;
import by.pwt.pilipenko.payments.model.entities.UserRole;
import by.pwt.pilipenko.payments.model.entities.UserRoleCommand;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserRoleCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        UserRoleCommand userRoleCommand = new UserRoleCommand();
        UserRole userRole1 = null;
        Command command1 = null;
        String page = null;
        try {
            int id = new Integer(request.getParameter("id"));
            String userRole = request.getParameter("role");
            String command = request.getParameter("commandf");

            CommandService commandService = new CommandService();
            command1 = commandService.getEntity(new Integer(command));

            UserRoleService userRoleService = new UserRoleService();
            userRole1 = userRoleService.getEntity(new Integer(userRole));

            userRoleCommand.setId(id);
            userRoleCommand.setUserRole(userRole1);
            userRoleCommand.setCommand(command1);

            UserRoleCommandService userRoleCommandService = new UserRoleCommandService();

            boolean flag = userRoleCommandService.updateEntity(userRoleCommand);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.userrolecommandlist");

                request.getSession().setAttribute("message", "User role command has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("userRoleCommand", userRoleCommand);

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATEUSERROLECOMMAND");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newuserrolecommand");

        }
        return page;
    }


}