package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.servlet.http.HttpServletRequest;


public class AddUserRoleCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String error = null;
        UserRole userRoleRole1 = null;
        UserRole userRole = null;
        String page = null;
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            userRole = new UserRole();

            userRole.setName(name);
            userRole.setDescription(description);

            UserRoleService userRoleService = new UserRoleService();

            userRoleRole1 = userRoleService.insertEntity(userRole);
            page = ConfigurationManager.getProperty("path.page.userrolelist");

            request.getSession().setAttribute("success", "true");

            request.getSession().setAttribute("message", "The new user role has been successfully created.");

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("userRole", userRole);

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newuserrole");

        }
        return page;
    }
}