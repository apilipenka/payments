package by.pwt.pilipenko.payments.web.command.userrole;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserRoleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        UserRole userRole = null;
        String page = null;
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            userRole = new UserRole();
            userRole.setId(Integer.parseInt(id));
            userRole.setName(name);
            userRole.setDescription(description);

            UserRoleService userRoleService = new UserRoleService();
            boolean flag = userRoleService.updateEntity(userRole);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.userrolelist");

                request.getSession().setAttribute("message", "The user role has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("userRole", userRole);

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATEUSERROLE");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newuserrole");

        }
        return page;
    }
}