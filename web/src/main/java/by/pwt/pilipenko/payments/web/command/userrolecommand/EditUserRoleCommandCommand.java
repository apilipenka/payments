package by.pwt.pilipenko.payments.web.command.userrolecommand;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.services.UserRoleCommandService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.UserRoleCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditUserRoleCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newuserrolecommand");

        UserRoleCommandService userRoleCommandService = new UserRoleCommandService();
        Object name = request.getAttribute("userRoleCommandId");
        UserRoleCommand userRoleCommand = null;

        try {
            if (name != null) {

                userRoleCommand = userRoleCommandService.getEntity(Integer.parseInt(name.toString()));

            } else {
                name = request.getParameter("userRoleCommandId");
                if (name != null) {
                    userRoleCommand = userRoleCommandService.getEntity(Integer.parseInt(name.toString()));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userRoleCommand != null) {
            request.setAttribute("userRoleCommand", userRoleCommand.createUserRoleCommandVO());
        }

        UserRoleService userRoleService = new UserRoleService();
        request.setAttribute("roles", userRoleService.getAllEntities());

        CommandService commandService = new CommandService();
        request.setAttribute("commands", commandService.getAllEntities());

        request.setAttribute("command", "UPDATEUSERROLECOMMAND");
        return page;
    }
}