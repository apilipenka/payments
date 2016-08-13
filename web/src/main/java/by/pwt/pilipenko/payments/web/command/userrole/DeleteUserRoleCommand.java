package by.pwt.pilipenko.payments.web.command.userrole;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserRoleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        UserRoleService userRoleService = new UserRoleService();
        Object name = request.getAttribute("userRoleID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                userRoleService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("userRoleID");
                if (name != null) {
                    userRoleService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.userrolelist");

            request.getSession().setAttribute("message", "The user role has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.userrolelist");
            request.setAttribute("error", error);
            request.setAttribute("command", "USERROLELIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}