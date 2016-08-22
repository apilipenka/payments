package by.pwt.pilipenko.payments.web.command.userrolecommand;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleCommandService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserRoleCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        UserRoleCommandService userRoleCommandService = new UserRoleCommandService();
        Object name = request.getAttribute("userRoleCommandID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                userRoleCommandService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("userRoleCommandID");
                if (name != null) {
                    userRoleCommandService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.userrolecommandlist");

            request.getSession().setAttribute("message", "User role command has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.userrolecommandlist");
            request.setAttribute("error", error);
            request.setAttribute("command", "USERROLECOMMANDLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}