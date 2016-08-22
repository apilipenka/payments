package by.pwt.pilipenko.payments.web.command.userrolecommand;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class NewUserRoleCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newuserrolecommand");

        UserRoleService userRoleService = new UserRoleService();
        request.setAttribute("roles", userRoleService.getAllEntities());

        CommandService commandService = new CommandService();
        request.setAttribute("commands", commandService.getAllEntities());

        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}